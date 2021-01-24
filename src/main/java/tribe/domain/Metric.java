package tribe.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;


@Entity
public class Metric {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	protected String id;
	
	protected String name;
	
	protected String unit;
	
	protected Boolean isPrincipal;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn
	protected MetricValue metricValue;
    
    @ManyToOne(cascade = CascadeType.ALL)
    protected Progression progression;

    public Metric() {}

	public Metric(String name, String unit, Boolean isPrincipal, MetricValue metricValue, Progression progression) {
		this.name = name;
		this.unit = unit;
		this.isPrincipal = isPrincipal;
		this.metricValue = metricValue;
		this.progression = progression;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Boolean getIsPrincipal() {
		return isPrincipal;
	}

	public void setIsPrincipal(Boolean isPrincipal) {
		this.isPrincipal = isPrincipal;
	}

	public MetricValue getMetricValue() {
		return metricValue;
	}

	public void setMetricValue(MetricValue metricValue) {
		this.metricValue = metricValue;
	}

	public Progression getProgression() {
		return progression;
	}

	public void setProgression(Progression progression) {
		this.progression = progression;
	}
}
