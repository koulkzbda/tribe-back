package tribe.controller.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tribe.domain.habitTracking.Repetition;

public class RepetitionDto extends PublicationDto {

	protected String repetitionStatus;
	
	protected String repetitionStatusId;
	
	protected List<MetricValueDto> metrics = new ArrayList<>();

	public RepetitionDto() {}
	
	public RepetitionDto(Repetition repetition) {
		this.id = repetition.getId();
		this.content = repetition.getContent();
		this.nbLikes = repetition.getNbLikes();
		this.nbComments = repetition.getNbComments();
		if ( repetition.getPublicationPictures() != null ) {
			this.publicationPictures = repetition.getPublicationPictures().getPictures().stream().map(PictureDto::new).collect(Collectors.toList());
		}
		this.repetitionStatus = repetition.getRepetitionStatus().getRepetitionStatus().name();
		this.repetitionStatusId = repetition.getRepetitionStatus().getId();
		if (  repetition.getMetricValues() != null && repetition.getMetricValues().size() > 0) {
			this.metrics = repetition.getMetricValues().stream().map(MetricValueDto::new).collect(Collectors.toList());
		} else {
			this.metrics = repetition.getProgression().getMetrics().stream().map(MetricValueDto::new).collect(Collectors.toList());
		}
		
	}

	public RepetitionDto(String id, String content, Integer nbLikes, Integer nbComments,
			List<PictureDto> publicationPictures,
			String repetitionStatus, String repetitionStatusId, List<MetricValueDto> metrics) {
		super(id, content, nbLikes, nbComments, publicationPictures);
		this.repetitionStatus = repetitionStatus;
		this.repetitionStatusId = repetitionStatusId;
		this.metrics = metrics;
	}

	public String getRepetitionStatus() {
		return repetitionStatus;
	}

	public void setRepetitionStatus(String repetitionStatus) {
		this.repetitionStatus = repetitionStatus;
	}

	public String getRepetitionStatusId() {
		return repetitionStatusId;
	}

	public void setRepetitionStatusId(String repetitionStatusId) {
		this.repetitionStatusId = repetitionStatusId;
	}

	public List<MetricValueDto> getMetrics() {
		return metrics;
	}

	public void setMetrics(List<MetricValueDto> metrics) {
		this.metrics = metrics;
	}
}
