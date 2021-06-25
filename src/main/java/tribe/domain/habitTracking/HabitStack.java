package tribe.domain.habitTracking;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.GenericGenerator;

import tribe.controller.dto.HabitStackCreationDto;


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
    @OrderBy("execution_order ASC")
    protected SortedSet<Progression> progressions = new TreeSet<>();
    
    @OneToMany(mappedBy = "habitStack", cascade = CascadeType.ALL)
    protected Set<Weekday> weekdays = new HashSet<>();

    public HabitStack() {}
    
    public HabitStack(HabitStackCreationDto hs) {
    	name = hs.getHabitStackName();
    	SortedSet<Progression> progressions = new TreeSet<>();
    	progressions.addAll(hs.getProgressions().stream().map(Progression::new).collect(Collectors.toSet()));
    	weekdays.addAll(hs.getWeekdays().stream().map(Weekday::new).collect(Collectors.toSet()));
    }

	public HabitStack(String name, System system, SortedSet<Progression> progressions) {
		this.name = name;
		this.system = system;
		this.progressions = progressions;
	}

	public HabitStack(String name, System system, SortedSet<Progression> progressions, Set<Weekday> weekdays) {
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

	public SortedSet<Progression> getProgressions() {
		return progressions;
	}

	public void setProgressions(SortedSet<Progression> progressions) {
		this.progressions = progressions;
	}

	public Set<Weekday> getWeekdays() {
		return weekdays;
	}

	public void setWeekdays(Set<Weekday> weekdays) {
		this.weekdays = weekdays;
	}
}
