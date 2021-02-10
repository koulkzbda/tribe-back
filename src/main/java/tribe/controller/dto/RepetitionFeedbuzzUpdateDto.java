package tribe.controller.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tribe.domain.Repetition;

public class RepetitionFeedbuzzUpdateDto {

	protected String id;

	protected String content;
	
	protected String repetitionStatus;
	
	protected List<MetricValueFeedbuzzUpdateDto> metricValues = new ArrayList<>();

	public RepetitionFeedbuzzUpdateDto() {}
	
	public RepetitionFeedbuzzUpdateDto(Repetition repetition) {
		this.id = repetition.getId();
		this.content = repetition.getContent();
		this.repetitionStatus = repetition.getRepetitionStatus().getRepetitionStatus().name();
		if (  repetition.getMetricValues().size() > 0) {
			this.metricValues = repetition.getMetricValues().stream().map(MetricValueFeedbuzzUpdateDto::new).collect(Collectors.toList());
		} else {
			this.metricValues = repetition.getProgression().getMetrics().stream().map(MetricValueFeedbuzzUpdateDto::new).collect(Collectors.toList());
		}
		
	}

	public String getRepetitionStatus() {
		return repetitionStatus;
	}

	public void setRepetitionStatus(String repetitionStatus) {
		this.repetitionStatus = repetitionStatus;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<MetricValueFeedbuzzUpdateDto> getMetricValues() {
		return metricValues;
	}

	public void setMetricValues(List<MetricValueFeedbuzzUpdateDto> metricValues) {
		this.metricValues = metricValues;
	}
}
