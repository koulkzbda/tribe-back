package tribe.domain.habitTracking;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import tribe.controller.dto.SystemCreatedDto;
import tribe.domain.socialNetwork.Member;
import tribe.domain.socialNetwork.Tribe;


@Entity
@Table(name="`system`")
public class System {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	protected String id;
	
	protected String name;
	
    protected String description;
    
    protected Boolean isActive;
    
    @ManyToOne(cascade = CascadeType.ALL)
    protected Member member;
    
    @OneToMany(mappedBy = "system", cascade = CascadeType.ALL)
    protected Set<Identity> identities  = new HashSet<>();
    
    @OneToMany(mappedBy = "system", cascade = CascadeType.ALL)
	protected Set<HabitStack> habitStacks = new HashSet<>();
    
    @ManyToOne(cascade = CascadeType.ALL)
	protected Tribe tribe;

    public System() {}

	public System(String name, String description, Boolean isActive, Member member, Set<Identity> identities) {
		this.name = name;
		this.description = description;
		this.isActive = isActive;
		this.member = member;
		this.identities = identities;
	}
	
	public System(SystemCreatedDto systemCreated, Member member, Set<Identity> identities) {
		name = systemCreated.getName();
		isActive = true;
		this.member = member;
		this.identities = identities.stream().peek(id -> id.setSystem(this)).collect(Collectors.toSet());;
	}

	public System(String name, String description, Boolean isActive, Member member, Set<Identity> identities,
			Set<HabitStack> habitStacks) {
		super();
		this.name = name;
		this.description = description;
		this.isActive = isActive;
		this.member = member;
		this.identities = identities;
		this.habitStacks = habitStacks;
	}
	
	public void addIdentity(Identity identity) {
		identities.add(identity);
	}
	
	public void addHabitStack(HabitStack hs) {
		habitStacks.add(hs);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
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

	public Set<HabitStack> getHabitStacks() {
		return habitStacks;
	}

	public void setHabitStacks(Set<HabitStack> habitStacks) {
		this.habitStacks = habitStacks;
	}

	public Tribe getTribe() {
		return tribe;
	}

	public void setTribe(Tribe tribe) {
		this.tribe = tribe;
	}
}
