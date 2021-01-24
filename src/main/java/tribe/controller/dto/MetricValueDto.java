package tribe.controller.dto;

import tribe.domain.MetricValue;

public class MetricValueDto {

	protected String id;

	protected String metricName;
	
	protected String metricUnit;
	
	protected Boolean isPrincipal;
	
	protected Float value;
	
	public MetricValueDto() {}
	
	public MetricValueDto(MetricValue metricValue) {
		this.id = metricValue.getId();
		this.metricName = metricValue.getMetric().getName();
		this.metricUnit = metricValue.getMetric().getUnit();
		this.isPrincipal = metricValue.getMetric().getIsPrincipal();
		this.value = metricValue.getValue();
	}

	public MetricValueDto(String id, String metricName, String metricUnit, Boolean isPrincipal, Float value) {
		this.id = id;
		this.metricName = metricName;
		this.metricUnit = metricUnit;
		this.isPrincipal = isPrincipal;
		this.value = value;
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

	public Float getValue() {
		return value;
	}

	public void setValue(Float value) {
		this.value = value;
	}
}
