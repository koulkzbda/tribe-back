package tribe.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;


@Entity
public class HabitStack {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	protected String id;
	
	protected String name;
    
    @ManyToOne(cascade = CascadeType.ALL)
    protected System system;
    
    @OneToMany(mappedBy = "habitStack", cascade = CascadeType.ALL)
    protected List<Progression> progressions = new ArrayList<>();
    
    @OneToMany(mappedBy = "habitStack", cascade = CascadeType.ALL)
    protected List<Weekday> weekdays = new ArrayList<>();

    public HabitStack() {}

	public HabitStack(String name, System system, List<Progression> progressions) {
		this.name = name;
		this.system = system;
		this.progressions = progressions;
	}

	public HabitStack(String name, System system, List<Progression> progressions, List<Weekday> weekdays) {
		this.name = name;
		this.system = system;
		this.progressions = progressions;
		this.weekdays = weekdays;
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

	public System getSystem() {
		return system;
	}

	public void setSystem(System system) {
		this.system = system;
	}

	public List<Progression> getProgressions() {
		return progressions;
	}

	public void setProgressions(List<Progression> progressions) {
		this.progressions = progressions;
	}

	public List<Weekday> getWeekdays() {
		return weekdays;
	}

	public void setWeekdays(List<Weekday> weekdays) {
		this.weekdays = weekdays;
	}
}
