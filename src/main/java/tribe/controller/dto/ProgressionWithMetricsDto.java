package tribe.controller.dto;

import java.util.Set;
import java.util.stream.Collectors;

import tribe.domain.habitTracking.Progression;

public class ProgressionWithMetricsDto extends ProgressionDto {
	
	protected Set<MetricDto> metrics;
	
	public ProgressionWithMetricsDto() {}
	
	public ProgressionWithMetricsDto(Progression progression) {
		super(progression);
	
		if (progression.getMetrics().size() > 0) {
			metrics = progression.getMetrics().stream().map(MetricDto::new).collect(Collectors.toSet());
		}
		
	}

	public Set<MetricDto> getMetrics() {
		return metrics;
	}

	public void setMetrics(Set<MetricDto> metrics) {
		this.metrics = metrics;
	}
	
}
