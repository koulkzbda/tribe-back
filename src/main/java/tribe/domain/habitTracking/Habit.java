package tribe.domain.habitTracking;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

import tribe.controller.dto.ProgressionWithMetricsDto;
import tribe.domain.socialNetwork.Member;


@Entity
public class Habit {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	protected String id;
	
	protected String name;
	
    protected String description;
    
    protected String gatewayHabit;
    
    @ManyToOne(cascade = CascadeType.ALL)
    protected Member member;
    
    @ManyToMany
    @JoinColumn
    protected Set<Identity> identities = new HashSet<>();
    
    @OneToMany(mappedBy = "habit", cascade = CascadeType.ALL)
    protected Set<Progression> progressions = new HashSet<>();

    public Habit() {}
    
    public Habit(ProgressionWithMetricsDto p) {
    	name = p.getHabitName();
    	gatewayHabit = p.getGatewayHabit();
    }

	public Habit(String name, String description, String gatewayHabit, Member member, Set<Identity> identities) {
		this.name = name;
		this.description = description;
		this.gatewayHabit = gatewayHabit;
		this.member = member;
		this.identities = identities;
	}

	public Habit(String name, String description, String gatewayHabit, Member member, Set<Identity> identities,
			Set<Progression> progressions) {
		this.name = name;
		this.description = description;
		this.gatewayHabit = gatewayHabit;
		this.member = member;
		this.identities = identities;
		this.progressions = progressions;
	}
	
	public void addProgression(Progression progression) {
		if (progression.getIsActive()) {
			progressions.stream().forEach(p -> p.setIsActive(false));
		}
		progressions.add(progression);
	}
	
	public void updateProgression(Progression progression) {
		progressions.remove(progressions.stream().filter(p -> p.getId().equals(progression.getId())).findAny().orElse(null));
		progressions.add(progression);
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGatewayHabit() {
		return gatewayHabit;
	}

	public void setGatewayHabit(String gatewayHabit) {
		this.gatewayHabit = gatewayHabit;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Set<Identity> getIdentities() {
		return identities;
	}

	public void setIdentities(Set<Identity> identities) {
		this.identities = identities;
	}

	public Set<Progression> getProgressions() {
		return progressions;
	}

	public void setProgressions(Set<Progression> progressions) {
		this.progressions = progressions;
	}
}
