package tribe.controller.dto;

import tribe.domain.habitTracking.Metric;

public class MetricDto {

	protected String id;
	
	protected String metricName;
	
	protected String metricUnit;
	
	protected Boolean isPrincipal;
	
	public MetricDto() {}
	
	public MetricDto(Metric metric) {
		this.id = metric.getId();
		this.metricName = metric.getName();
		this.metricUnit = metric.getUnit();
		this.isPrincipal = metric.getIsPrincipal();
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMetricName() {
		return metricName;
	}

	public void setMetricName(String metricName) {
		this.metricName = metricName;
	}

	public String getMetricUnit() {
		return metricUnit;
	}

	public void setMetricUnit(String metricUnit) {
		this.metricUnit = metricUnit;
	}

	public Boolean getIsPrincipal() {
		return isPrincipal;
	}

	public void setIsPrincipal(Boolean isPrincipal) {
		this.isPrincipal = isPrincipal;
	}

}
