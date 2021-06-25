package tribe.domain.habitTracking;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;


@Entity
public class MetricValue {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	protected String id;
	
	protected Float value;
	
	@OneToOne(cascade = CascadeType.ALL)
	protected Metric metric;
    
    @ManyToOne(cascade = CascadeType.ALL)
    protected Repetition repetition;
    
    public MetricValue() {}

	public MetricValue(Float value, Metric metric, Repetition repetition) {
		this.value = value;
		this.metric = metric;
		this.repetition = repetition;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Float getValue() {
		return value;
	}

	public void setValue(Float value) {
		this.value = value;
	}

	public Metric getMetric() {
		return metric;
	}

	public void setMetric(Metric metric) {
		this.metric = metric;
	}

	public Repetition getRepetition() {
		return repetition;
	}

	public void setRepetition(Repetition repetition) {
		this.repetition = repetition;
	}
}
