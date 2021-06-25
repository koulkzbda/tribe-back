package tribe.controller.dto;

import tribe.domain.habitTracking.Metric;
import tribe.domain.habitTracking.MetricValue;

public class MetricValueFeedbuzzUpdateDto {

	protected String metricId;

	protected String metricName;
	
	protected String metricUnit;
	
	protected Float value;
	
	public MetricValueFeedbuzzUpdateDto() {}
	
	public MetricValueFeedbuzzUpdateDto(MetricValue metricValue) {
		this.metricId = metricValue.getId();
		this.metricName = metricValue.getMetric().getName();
		this.metricUnit = metricValue.getMetric().getUnit();
		this.value = metricValue.getValue();
	}
	
	public MetricValueFeedbuzzUpdateDto(Metric metric) {
		this.metricId = metric.getId();
		this.metricName = metric.getName();
		this.metricUnit = metric.getUnit();
	}

	public String getMetricId() {
		return metricId;
	}

	public void setMetricId(String metricId) {
		this.metricId = metricId;
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

	public Float getValue() {
		return value;
	}

	public void setValue(Float value) {
		this.value = value;
	}
}
