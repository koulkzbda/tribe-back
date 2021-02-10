package tribe.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import tribe.controller.dto.FeedbuzzDto;
import tribe.controller.dto.MetricValueFeedbuzzUpdateDto;
import tribe.controller.dto.RepetitionFeedbuzzUpdateDto;
import tribe.domain.HabitStack;
import tribe.domain.Metric;
import tribe.domain.MetricValue;
import tribe.domain.Repetition;
import tribe.domain.RepetitionStatus;
import tribe.domain.enumaration.RepetitionStatusEnum;
import tribe.domain.enumaration.WeekdayEnum;
import tribe.repository.HabitStackRepo;
import tribe.repository.MemberRepo;
import tribe.repository.MetricRepo;
import tribe.repository.RepetitionRepo;

@Service
public class HabitStackService {

	protected MemberRepo memberRepo;
	protected HabitStackRepo habitStackRepo;
	protected SystemService systemService;
	protected RepetitionRepo repetitionRepo;
	protected MetricRepo metricRepo;
	protected SecurityServiceImpl securityService;
	protected WeekdayUtil weekdayUtil;

	public HabitStackService(MemberRepo memberRepo, HabitStackRepo habitStackRepo, SystemService systemService,
			RepetitionRepo repetitionRepo, MetricRepo metricRepo, SecurityServiceImpl securityService, WeekdayUtil weekdayUtil) {
		this.memberRepo = memberRepo;
		this.habitStackRepo = habitStackRepo;
		this.systemService = systemService;
		this.repetitionRepo = repetitionRepo;
		this.metricRepo = metricRepo;
		this.securityService = securityService;
		this.weekdayUtil = weekdayUtil;
	}

	// TO DO Create HabitStackDto
	public List<FeedbuzzDto> findByConnectedMember() {
		LocalDateTime today = LocalDate.now().atTime(0, 0);
		String day = LocalDate.now().getDayOfWeek().name();
		WeekdayEnum weekdayEnum = weekdayUtil.getWeekdayEnum(day);

		List<FeedbuzzDto> habitStacksDto = new ArrayList<>();

		List<HabitStack> habitStacks = habitStackRepo.findWithHabitStacksByMemberId(
				memberRepo.findByEmail(securityService.getUserEmail()).get().getId(), today, weekdayEnum);
		
		habitStacksDto.addAll(habitStacks.stream().map(FeedbuzzDto::new).collect(Collectors.toList()));

		return habitStacksDto;
	}

//	@Transactional
//	public List<PictureDto> addProfilePictures(MultipartFile[] files, String profileId, String profilePictureName)
//			throws IOException, NoSuchElementException {
//
//		MemberProfilePictures pictures = this.memberProfilePicturesRepo.findByMemberProfileId(profileId).get();
//		MemberProfile profile = this.memberProfileRepo.findById(profileId).get();
//		List<Picture> picturesList = new ArrayList<Picture>();
//
//		for (int i = 0; i < files.length; i++) {
//			String fileName = StringUtils.cleanPath(files[i].getOriginalFilename());
//			Picture picture = new Picture(fileName, files[i].getContentType(), files[i].getBytes());
//			picture.setPictures(pictures);
//			if (fileName.equals(profilePictureName)) {
//				profile.getProfilePictures().getPictures().stream().forEach(pict -> pict.setIsHeadlinePicture(false));
//				picture.setIsHeadlinePicture(true);
//			}
//			picturesList.add(picture);
//		}
//
//		pictures.addPictures(picturesList);
//		profile.setProfilePictures(pictures);
//
//		this.memberProfileRepo.save(profile);
//		
//		return getProfilePictures();
//
//	}
//
//	@Transactional
//	public List<PictureDto> setProfilePicture(PictureDto pictureDto) throws InvalidPictureException {
//		MemberProfile profile = memberProfileRepo.findEagerByMemberId(
//				memberRepo.findByEmail(securityService.getUserEmail()
//						)
//				.get().getId()).get();
//		MemberProfilePictures memberProfilePictures = profile.getProfilePictures();
//		List<Picture> pictures = memberProfilePictures.getPictures();
//		
//		if ( pictures.stream().anyMatch(pict -> pict.getId().equals(pictureDto.getId())) ) {
//			pictures.stream().forEach(pict -> {
//				
//				pict.setIsHeadlinePicture(false);
//				if (pict.getId().equals(pictureDto.getId())) {
//					pict.setIsHeadlinePicture(true);
//				}
//			});
//		} else {
//			throw new InvalidPictureException(new ErrorMessageDto(ErrorCode.PICTURE, "Photo inexistante"));
//		}
//		
//		memberProfilePictures.setPictures(pictures);
//		profile.setProfilePictures(memberProfilePictures);
//		this.memberProfileRepo.save(profile);
//		
//		return pictures.stream().map(PictureDto::new).collect(Collectors.toList());
//	}
//	
//	public List<PictureDto> getProfilePictures() {
//		MemberProfile profile = memberProfileRepo.findEagerByMemberId(
//				memberRepo.findByEmail(securityService.getUserEmail()
//						)
//				.get().getId()).get();
//		MemberProfilePictures memberProfilePictures = profile.getProfilePictures();
//		List<Picture> pictures = memberProfilePictures.getPictures();
//		
//		return pictures.stream().map(PictureDto::new).collect(Collectors.toList());
//	}
//	
//	@Transactional
//	public List<PictureDto> deleteProfilePicture(String id) throws InvalidPictureException {
//		MemberProfile profile = memberProfileRepo.findEagerByMemberId(
//				memberRepo.findByEmail(securityService.getUserEmail()
//						)
//				.get().getId()).get();
//		MemberProfilePictures memberProfilePictures = profile.getProfilePictures();
//		List<Picture> pictures = memberProfilePictures.getPictures();
//		
//		if ( pictures.stream().anyMatch(pict -> pict.getId().equals(id)) ) {
//			this.pictureRepo.deleteById(id);
//			pictures = pictures.stream().filter(pict -> !pict.getId().equals(id)).collect(Collectors.toList());
//		} else {
//			throw new InvalidPictureException(new ErrorMessageDto(ErrorCode.PICTURE, "Photo inexistante"));
//		}
//		
//		memberProfilePictures.setPictures(pictures);
//		profile.setProfilePictures(memberProfilePictures);
//		this.memberProfileRepo.save(profile);
//		
//		return pictures.stream().map(PictureDto::new).collect(Collectors.toList());
//	}
//	
	
	@Transactional
	public RepetitionFeedbuzzUpdateDto updateRepetition(RepetitionFeedbuzzUpdateDto repetitionDto) {
		Repetition repetition = repetitionRepo.findByIdWithMetrics(repetitionDto.getId())
				.get();
		
		RepetitionStatus repetitionStatus = repetition.getRepetitionStatus();
		repetitionStatus.setRepetitionStatus(RepetitionStatusEnum.valueOfLabel(repetitionDto.getRepetitionStatus()));
		repetition.setRepetitionStatus(repetitionStatus);
		
		repetition.setContent(repetitionDto.getContent());
		
		repetitionRepo.save(repetition);
		
		List<Metric> metrics = new ArrayList<>(repetition.getProgression().getMetrics());
		List<MetricValueFeedbuzzUpdateDto> metricValuesDto = repetitionDto.getMetricValues();
		metrics.stream().forEach(metric -> {
			List<MetricValueFeedbuzzUpdateDto> metricValueDtos = metricValuesDto.stream().filter(metricValueDto -> 
			metricValueDto.getMetricId().equals(metric.getId())
					).collect(Collectors.toList());
			if (metricValueDtos.size() > 0) {
				MetricValue metricValue = new MetricValue(metricValueDtos.get(0).getValue(), metric, repetition);
				metric.setMetricValue(metricValue);
			}
		});
		
		metricRepo.saveAll(metrics);
		
		return repetitionDto;
	}

}
