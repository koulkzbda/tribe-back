package tribe.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import tribe.controller.dto.ErrorCode;
import tribe.controller.dto.ErrorMessageDto;
import tribe.controller.dto.FeedbuzzDto;
import tribe.domain.HabitStack;
import tribe.domain.Member;
import tribe.domain.enumaration.WeekdayEnum;
import tribe.exception.NoMemberFoundException;
import tribe.repository.HabitStackRepo;
import tribe.repository.MemberRepo;

@Service
public class HabitStackService {

	protected MemberRepo memberRepo;
	protected HabitStackRepo habitStackRepo;
	protected SecurityServiceImpl securityService;
	protected RepetitionService repetitionService;
	protected WeekdayUtil weekdayUtil;

	public HabitStackService(MemberRepo memberRepo, HabitStackRepo habitStackRepo,
			SecurityServiceImpl securityService, RepetitionService repetitionService, WeekdayUtil weekdayUtil) {
		this.memberRepo = memberRepo;
		this.habitStackRepo = habitStackRepo;
		this.securityService = securityService;
		this.repetitionService = repetitionService;
		this.weekdayUtil = weekdayUtil;
	}

	@Transactional
	public List<FeedbuzzDto> findByConnectedMember() {
		LocalDateTime today = LocalDate.now().atTime(0, 0, 0, 1);
		String day = LocalDate.now().getDayOfWeek().name();
		WeekdayEnum weekdayEnum = weekdayUtil.getWeekdayEnum(day);
		Member member = memberRepo.findByEmail(securityService.getUserEmail()).orElseThrow(() -> new NoMemberFoundException(new ErrorMessageDto(ErrorCode.SECURITY, "No member connected or inexisting member connected.")));
		
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

		List<HabitStack> habitStacks = habitStackRepo.findWithHabitStacksByMemberId(
				member.getId(), today, weekdayEnum);
		
		habitStacksDto.addAll(habitStacks.stream().map(FeedbuzzDto::new).collect(Collectors.toList()));

		return habitStacksDto;
	}

}
