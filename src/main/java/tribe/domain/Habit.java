package tribe.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;


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
    protected List<Identity> identities = new ArrayList<>();
    
    @OneToMany(mappedBy = "habit", cascade = CascadeType.ALL)
    protected List<Progression> progressions;

    public Habit() {}

	public Habit(String name, String description, String gatewayHabit, Member member, List<Identity> identities) {
		this.name = name;
		this.description = description;
		this.gatewayHabit = gatewayHabit;
		this.member = member;
		this.identities = identities;
	}

	public Habit(String name, String description, String gatewayHabit, Member member, List<Identity> identities,
			List<Progression> progressions) {
		this.name = name;
		this.description = description;
		this.gatewayHabit = gatewayHabit;
		this.member = member;
		this.identities = identities;
		this.progressions = progressions;
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

	public List<Identity> getIdentities() {
		return identities;
	}

	public void setIdentities(List<Identity> identities) {
		this.identities = identities;
	}

	public List<Progression> getProgressions() {
		return progressions;
	}

	public void setProgressions(List<Progression> progressions) {
		this.progressions = progressions;
	}
}
