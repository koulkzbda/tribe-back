package tribe.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import tribe.controller.dto.MetricValueFeedbuzzUpdateDto;
import tribe.controller.dto.RepetitionFeedbuzzUpdateDto;
import tribe.domain.enumaration.RepetitionStatusEnum;
import tribe.domain.enumaration.WeekdayEnum;
import tribe.domain.habitTracking.Metric;
import tribe.domain.habitTracking.MetricValue;
import tribe.domain.habitTracking.Progression;
import tribe.domain.habitTracking.Repetition;
import tribe.domain.habitTracking.RepetitionStatus;
import tribe.domain.socialNetwork.Member;
import tribe.domain.socialNetwork.PublicationPictures;
import tribe.repository.MetricRepo;
import tribe.repository.PictureRepo;
import tribe.repository.ProgressionRepo;
import tribe.repository.PublicationPicturesRepo;
import tribe.repository.RepetitionRepo;

@Service
public class RepetitionService {

	protected RepetitionRepo repetitionRepo;
	protected PublicationPicturesRepo publicationPicturesRepo;
	protected MetricRepo metricRepo;
	protected PictureRepo pictureRepo;
	protected ProgressionRepo progressionRepo;

	public RepetitionService(RepetitionRepo repetitionRepo, PublicationPicturesRepo publicationPicturesRepo,
			PictureRepo pictureRepo, MetricRepo metricRepo, ProgressionRepo progressionRepo) {
		this.repetitionRepo = repetitionRepo;
		this.publicationPicturesRepo = publicationPicturesRepo;
		this.pictureRepo = pictureRepo;
		this.metricRepo = metricRepo;
		this.progressionRepo = progressionRepo;
	}
	
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
		
		Set<Float> metricsSet = new HashSet<>(metrics.stream().map(metric -> {
			if (metric.getMetricValue() != null && metric.getMetricValue().getValue() != null) {
				return  metric.getMetricValue().getValue();
			}
			
			return 0f;
		}).collect(Collectors.toList()));
		Set<Float> metricValuesDtoSet = new HashSet<>(metricValuesDto.stream().map(metricValueDto -> metricValueDto.getValue()).collect(Collectors.toList()));
		
		if ( !metricsSet.equals(metricValuesDtoSet) ) {
			metrics.stream().forEach(metric -> {
				List<MetricValueFeedbuzzUpdateDto> metricValueDtos = metricValuesDto.stream().filter(metricValueDto -> 
				metricValueDto.getMetricId().equals(metric.getId())
						).filter(distinctByKey(m -> m.getMetricName())).collect(Collectors.toList());
				if (metricValueDtos.size() > 0) {
					MetricValue metricValue = new MetricValue(metricValueDtos.get(0).getValue(), metric, repetition);
					metric.setMetricValue(metricValue);
				}
			});
			
			metricRepo.saveAll(metrics);
		}
		
		return repetitionDto;
	}
	
	@Transactional
	public void updateRepetitionsForToday(Member member, WeekdayEnum weekdayEnum) {
		LocalDateTime today = LocalDate.now().atTime(0, 0, 0, 1);
		List<Progression> progressions = progressionRepo.findByMemberIdAndWeekday(member.getId(), weekdayEnum);
		progressions.stream().forEach(progression -> {
			if (!progression.getRepetitions().stream().anyMatch(r -> r.getPostedAt().compareTo(today) >= 0)) {
				RepetitionStatus repetitionStatus = new RepetitionStatus(RepetitionStatusEnum.TO_DO);
				
				Repetition repetition = new Repetition(repetitionStatus, progression, null);
				PublicationPictures publicationPictures = new PublicationPictures(new HashSet<>(), repetition);
				repetition.setPublicationPictures(publicationPictures);
				repetition.setAuthor(member);
				progression.addRepetition(repetition);
				
				progressionRepo.save(progression);
			}
			
		});
		
		
	}
	
	@Transactional
	public void updateRepetitionsForYesterday(Member member, WeekdayEnum yesterdayEnum) {
		LocalDateTime today = LocalDate.now().atTime(0, 0);
		LocalDateTime yesterday = today.minusDays(1);
		
		List<Repetition> repetitions = repetitionRepo.findNotDoneYesterday(member.getId(), yesterdayEnum, RepetitionStatusEnum.TO_DO, today, yesterday);
		repetitions.forEach(repetition -> {
			RepetitionStatus repetitionStatus = repetition.getRepetitionStatus();
			repetitionStatus.setRepetitionStatus(RepetitionStatusEnum.NOT_DONE);
			repetition.setRepetitionStatus(repetitionStatus);
			repetitionRepo.save(repetition);
		});
	}
	
	public static <T> Predicate<T> distinctByKey(
		    Function<? super T, ?> keyExtractor) {
		  
		    Map<Object, Boolean> seen = new ConcurrentHashMap<>(); 
		    return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null; 
		}

}
