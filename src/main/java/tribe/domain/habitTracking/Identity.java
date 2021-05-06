package tribe.domain.habitTracking;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name="`identity`")
public class Identity {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	protected String id;
	
    protected String name;
    
    protected Integer vote;
    
    @ManyToOne(cascade = CascadeType.ALL)
    protected System system;
    
    @OneToMany(mappedBy = "identity")
	protected List<Weighting> weightings = new ArrayList<>();
    
    @ManyToMany(mappedBy = "identities", cascade = CascadeType.ALL)
	protected List<Habit> habits = new ArrayList<>();

    public Identity() {}

	public Identity(String name, Integer vote, System system) {
		this.name = name;
		this.vote = vote;
		this.system = system;
	}

	public Identity(String name, Integer vote, System system, List<Weighting> weightings) {
		super();
		this.name = name;
		this.vote = vote;
		this.system = system;
		this.weightings = weightings;
	}

	public Identity(String name, Integer vote, System system, List<Weighting> weightings, List<Habit> habits) {
		super();
		this.name = name;
		this.vote = vote;
		this.system = system;
		this.weightings = weightings;
		this.habits = habits;
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

	public Integer getVote() {
		return vote;
	}

	public void setVote(Integer vote) {
		this.vote = vote;
	}

	public System getSystem() {
		return system;
	}

	public void setSystem(System system) {
		this.system = system;
	}

	public List<Weighting> getWeightings() {
		return weightings;
	}

	public void setWeightings(List<Weighting> weightings) {
		this.weightings = weightings;
	}

	public List<Habit> getHabits() {
		return habits;
	}

	public void setHabits(List<Habit> habits) {
		this.habits = habits;
	}
}
