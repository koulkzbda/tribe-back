package tribe.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import tribe.controller.dto.ErrorCode;
import tribe.controller.dto.ErrorMessageDto;
import tribe.controller.dto.FeedbuzzDto;
import tribe.controller.dto.HabitStackCreationDto;
import tribe.controller.dto.HabitStackDto;
import tribe.controller.dto.ProgressionWithMetricsDto;
import tribe.domain.enumaration.RepetitionStatusEnum;
import tribe.domain.enumaration.WeekdayEnum;
import tribe.domain.habitTracking.Habit;
import tribe.domain.habitTracking.HabitStack;
import tribe.domain.habitTracking.Identity;
import tribe.domain.habitTracking.Progression;
import tribe.domain.habitTracking.Repetition;
import tribe.domain.habitTracking.System;
import tribe.domain.habitTracking.Weekday;
import tribe.domain.socialNetwork.Member;
import tribe.exception.NoHabitFoundException;
import tribe.exception.NoHabitStackFoundException;
import tribe.exception.NoIdentityFoundException;
import tribe.exception.NoMemberFoundException;
import tribe.exception.NoSystemFoundException;
import tribe.repository.HabitRepo;
import tribe.repository.HabitStackRepo;
import tribe.repository.IdentityRepo;
import tribe.repository.MemberRepo;
import tribe.repository.ProgressionRepo;
import tribe.repository.SystemRepo;
import tribe.repository.TribeRepo;
import tribe.repository.WeekdayRepo;

@Service
public class HabitStackService {

	protected MemberRepo memberRepo;
	protected IdentityRepo identityRepo;
	protected HabitRepo habitRepo;
	protected HabitStackRepo habitStackRepo;
	protected SystemRepo systemRepo;
	protected TribeRepo tribeRepo;
	protected WeekdayRepo weekdayRepo;
	protected ProgressionRepo progressionRepo;
	protected SecurityServiceImpl securityService;
	protected RepetitionService repetitionService;
	protected IdentityService identityService;
	protected WeekdayUtil weekdayUtil;

	public HabitStackService(MemberRepo memberRepo, HabitStackRepo habitStackRepo,
			HabitRepo habitRepo, TribeRepo tribeRepo, IdentityRepo identityRepo,
			SystemRepo systemRepo, WeekdayRepo weekdayRepo,
			ProgressionRepo progressionRepo, SecurityServiceImpl securityService,
			RepetitionService repetitionService, IdentityService identityService, WeekdayUtil weekdayUtil) {
		this.memberRepo = memberRepo;
		this.identityRepo = identityRepo;
		this.habitStackRepo = habitStackRepo;
		this.habitRepo = habitRepo;
		this.systemRepo = systemRepo;
		this.tribeRepo = tribeRepo;
		this.weekdayRepo = weekdayRepo;
		this.securityService = securityService;
		this.repetitionService = repetitionService;
		this.progressionRepo = progressionRepo;
		this.identityService = identityService;
		this.weekdayUtil = weekdayUtil;
	}

	@Transactional
	public List<FeedbuzzDto> findByConnectedMember() {
		LocalDateTime today = LocalDate.now().atTime(LocalTime.MIN);
		String day = LocalDate.now().getDayOfWeek().name();
		WeekdayEnum weekdayEnum = weekdayUtil.getWeekdayEnum(day);
		Member member = memberRepo.findByEmail(securityService.getUserEmail())
				.orElseThrow(() -> new NoMemberFoundException(new ErrorMessageDto(ErrorCode.SECURITY,
						"No member connected or inexisting member connected.")));

		if (member.getNextUpdateRepetition() == null || member.getNextUpdateRepetition().isBefore(today)) {
			repetitionService.updateRepetitionsForToday(member, weekdayEnum);
			LocalDateTime tomorrow = today.plus(1, ChronoUnit.DAYS);
			member.setNextUpdateRepetition(tomorrow);

			LocalDateTime yesterday = today.minusDays(1);
			WeekdayEnum yesterdayEnum = weekdayUtil.getWeekdayEnum(yesterday.getDayOfWeek().name());
			repetitionService.updateRepetitionsForYesterday(member, yesterdayEnum);
			memberRepo.save(member);
		}

		List<FeedbuzzDto> habitStacksDto = new ArrayList<>();

		List<HabitStack> habitStacks = habitStackRepo.findWithHabitStacksByMemberId(member.getId(), today, weekdayEnum, true);

		habitStacksDto.addAll(habitStacks.stream().map(FeedbuzzDto::new).collect(Collectors.toList()));

		return habitStacksDto;
	}

	@Transactional
	public HabitStackDto createHabit(HabitStackCreationDto hs) {
		Member member = memberRepo.findByEmail(securityService.getUserEmail())
				.orElseThrow(NoMemberFoundException::new);
		member.setNextUpdateRepetition(null);
		System system = systemRepo.findByIdWithHabitStacksAndIdentities(hs.getSystemId()).orElseThrow(() -> new NoSystemFoundException(hs));
		
		HabitStack habitStack;
		if (hs.getId() == null) {
			habitStack = new HabitStack(hs);
		} else {
			habitStack = habitStackRepo.findByIdWithProgressionsAndWeekdays(hs.getId()).orElseThrow(() -> new NoHabitStackFoundException(hs));
		}
		habitStack = updateHabitStack(habitStack, hs, member, system);
		system.addHabitStack(habitStack);
		habitStack.setSystem(system);
		
		HabitStack hsCreated = habitStackRepo.save(habitStack);

		return new HabitStackDto(hsCreated);
	}
	
	@Transactional
	private HabitStack updateHabitStack(HabitStack habitStack, HabitStackCreationDto hsDto, Member member, System system) {
		String habitStackId = habitStack.getId();
		if (habitStackId != null) {
			weekdayRepo.deleteByHabitStackId(habitStackId);
		}
		habitStack.setWeekdays(hsDto.getWeekdays().stream().map(Weekday::new).peek(wd -> wd.setHabitStack(habitStack)).collect(Collectors.toSet()));
		
		SortedSet<Progression> progressions = new TreeSet<Progression>();
		if (habitStack.getProgressions().size() > 1) {
			progressions.addAll(habitStack.getProgressions().stream()
					.map(p -> updateExecutionOrder(p, hsDto.getProgressions()))
					.collect(Collectors.toSet()));
		}
		ProgressionWithMetricsDto newProgressionDto = hsDto.getProgressions().stream().filter(p -> p.getProgressionId() == null).findAny().get();
		Progression newProgression = new Progression(newProgressionDto);
		String habitId = newProgressionDto.getHabitId();
		if (habitId != null) {
			LocalDateTime today = LocalDate.now().atTime(LocalTime.MIN);
			Habit habit = habitRepo.findByIdWithIdentitiesProgressionsAndTodayRepetition(habitId, today)
					.orElseThrow(() -> new NoHabitFoundException(newProgressionDto));
			habit = updateHabit(habit, newProgressionDto, member, system, this.getWeekdayOfToday(habitStack));
			habit.addProgression(newProgression);
			newProgression.setHabit(habit);
			
		} else {
			Habit newHabit = new Habit(newProgressionDto);
			newHabit = updateHabit(newHabit, newProgressionDto, member, system, null);
			newHabit.addProgression(newProgression);
			newProgression.setHabit(newHabit);
		}
		newProgression.setHabitStack(habitStack);
		newProgression.setMemberForSteps(member);
		progressions.add(newProgression);
		habitStack.setProgressions(progressions);
		
		return habitStack;
	}

	@Transactional
	private Habit updateHabit(Habit habit, ProgressionWithMetricsDto p, Member member, System system, Weekday today) {
		habit.setName(p.getHabitName());
		habit.setMember(member);
		habit.setGatewayHabit(p.getGatewayHabit());
		Set<Identity> identities = new HashSet<>();
		p.getIdentities().stream().forEach(idDto -> {
			String identityId = idDto.getId();
			if (identityId != null) {
				Identity identity = identityRepo.findByIdWithHabitsAndWeightings(identityId).orElseThrow(() -> new NoIdentityFoundException(idDto));
				identity = identityService.updateIdentity(identity, idDto);
				identity.addHabit(habit);
				identities.add(identity);
			} else {
				Identity newIdentity = new Identity(idDto);
				newIdentity.addHabit(habit);
				system.addIdentity(newIdentity);
				newIdentity.setMember(member);
				newIdentity.setSystem(system);
				identities.add(newIdentity);
				
			}
		});
		habit.setIdentities(identities);
		if (habit.getProgressions().size() > 0) {
			Progression oldProgression = habit.getProgressions().stream().filter(progression -> progression.getIsActive()).findAny().orElse(null);
			if (oldProgression != null && oldProgression.getRepetitions().size() > 0) {
				Repetition repetition = oldProgression.getRepetitions().get(0);
				java.lang.System.out.println(repetition.getProgression().getDescription());
				if (today != null) {
					java.lang.System.out.println(today.getTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
				}
				
				java.lang.System.out.println(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
				if (!repetition.getRepetitionStatus().getRepetitionStatus().equals(RepetitionStatusEnum.DONE) && 
						today != null && today.getTime().compareTo(LocalTime.now()) <= 0) {
					java.lang.System.out.println(today.getTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
					oldProgression.deleteRepetition(repetition);
					habit.updateProgression(oldProgression);
				}
				
			}
		}
		
		return habit;
	}
	
	private Weekday getWeekdayOfToday(HabitStack hs) {
		String day = LocalDate.now().getDayOfWeek().name();
		WeekdayEnum weekdayEnum = weekdayUtil.getWeekdayEnum(day);
		
		return hs.getWeekdays().stream().filter(w -> w.getWeekday().equals(weekdayEnum)).findAny().orElse(null);
	}

	private Progression updateExecutionOrder(Progression p, List<ProgressionWithMetricsDto> progressions) {
		ProgressionWithMetricsDto progression = progressions.stream()
				.filter(pr -> pr.getProgressionId() != null)
				.filter(pro -> pro.getProgressionId().equals(p.getId())).findAny().orElse(null);
		
		if (progression != null) {
			p.setExecutionOrder(progression.getExecutionOrder());
		}
		
		return p;
	}
	
	

}
