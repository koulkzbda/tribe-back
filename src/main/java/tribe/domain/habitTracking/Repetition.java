package tribe.domain.habitTracking;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import tribe.domain.socialNetwork.Like;
import tribe.domain.socialNetwork.Member;
import tribe.domain.socialNetwork.Publication;
import tribe.domain.socialNetwork.PublicationPictures;


@Entity
public class Repetition extends Publication {
	
	@OneToOne(cascade = CascadeType.ALL)
	protected RepetitionStatus repetitionStatus;
    
	@ManyToOne(cascade = CascadeType.ALL)
    protected Progression progression;
    
    @OneToMany(mappedBy = "repetition", cascade = CascadeType.ALL)
    protected List<MetricValue> metricValues;

    public Repetition() {
    	super();
    }

	public Repetition(String content, PublicationPictures publicationPictures, Member author, List<Like> likes,
			RepetitionStatus repetitionStatus, Progression progression,
			List<MetricValue> metricValues
			) {
		super(content, publicationPictures, author, likes);
		this.repetitionStatus = repetitionStatus;
		this.progression = progression;
		this.metricValues = metricValues;
	}

	public Repetition(RepetitionStatus repetitionStatus, Progression progression,
			List<MetricValue> metricValues) {
		super();
		this.repetitionStatus = repetitionStatus;
		this.progression = progression;
		this.metricValues = metricValues;
	}

	public RepetitionStatus getRepetitionStatus() {
		return repetitionStatus;
	}

	public void setRepetitionStatus(RepetitionStatus repetitionStatus) {
		this.repetitionStatus = repetitionStatus;
	}

	public Progression getProgression() {
		return progression;
	}

	public void setProgression(Progression progression) {
		this.progression = progression;
	}

	public List<MetricValue> getMetricValues() {
		return metricValues;
	}

	public void setMetricValues(List<MetricValue> metricValues) {
		this.metricValues = metricValues;
	}
}
