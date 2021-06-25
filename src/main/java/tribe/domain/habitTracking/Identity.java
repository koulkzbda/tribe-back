package tribe.domain.habitTracking;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import tribe.controller.dto.IdentityDto;
import tribe.domain.socialNetwork.Member;


@Entity
@Table(name="`identity`")
public class Identity {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	protected String id;
	
    protected String name;
    
    protected Integer votes;
    
    @ManyToOne(cascade = CascadeType.ALL)
	protected Member member;
    
    @ManyToOne(cascade = CascadeType.ALL)
    protected System system;
    
    @OneToMany(mappedBy = "identity", cascade = CascadeType.ALL)
    @NotFound(action = NotFoundAction.IGNORE)
	protected Set<Weighting> weightings = new HashSet<>();
    
    @ManyToMany(mappedBy = "identities", cascade = CascadeType.ALL)
	protected Set<Habit> habits = new HashSet<>();

    public Identity() {}

	public Identity(String name, Integer votes, System system) {
		this.name = name;
		this.votes = votes;
		this.system = system;
	}

//	public Identity(IdentityDto identity) {
//		this.name = identity.getName();
//		this.votes = identity.getVotes();
//		this.weightings = identity.getWeightings().stream().map(Weighting::new).collect(Collectors.toSet());
//	}
	
	public Identity(IdentityDto identity) {
		if (identity.getId() != null) {
			this.id = identity.getId();
		}
		this.name = identity.getName();
		this.votes = identity.getVotes();
		this.weightings = identity.getWeightings().stream().map(Weighting::new).peek(w -> w.setIdentity(this)).collect(Collectors.toSet());
	}
	
	public Identity(String name, Integer votes, System system, Set<Weighting> weightings) {
		this.name = name;
		this.votes = votes;
		this.system = system;
		this.weightings = weightings;
	}

	public Identity(String name, Integer votes, System system, Set<Weighting> weightings, Set<Habit> habits) {
		this.name = name;
		this.votes = votes;
		this.system = system;
		this.weightings = weightings;
		this.habits = habits;
	}
	
	public void addHabit(Habit habit) {
		habits.add(habit);
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

	public Integer getVotes() {
		return votes;
	}

	public void setVotes(Integer votes) {
		this.votes = votes;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public System getSystem() {
		return system;
	}

	public void setSystem(System system) {
		this.system = system;
	}

	public Set<Weighting> getWeightings() {
		return weightings;
	}

	public void setWeightings(Set<Weighting> weightings) {
		this.weightings = weightings;
	}

	public Set<Habit> getHabits() {
		return habits;
	}

	public void setHabits(Set<Habit> habits) {
		this.habits = habits;
	}
}
