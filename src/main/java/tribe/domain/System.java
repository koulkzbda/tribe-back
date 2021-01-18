package tribe.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


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
    protected List<Identity> identities  = new ArrayList<>();
    
    @OneToMany(mappedBy = "system")
	protected List<HabitStack> habitStacks = new ArrayList<>();
    
    @OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tribe_id")
	protected Tribe tribe;

    public System() {}

	public System(String name, String description, Boolean isActive, Member member, List<Identity> identities) {
		this.name = name;
		this.description = description;
		this.isActive = isActive;
		this.member = member;
		this.identities = identities;
	}

	public System(String name, String description, Boolean isActive, Member member, List<Identity> identities,
			List<HabitStack> habitStacks) {
		super();
		this.name = name;
		this.description = description;
		this.isActive = isActive;
		this.member = member;
		this.identities = identities;
		this.habitStacks = habitStacks;
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

	public List<Identity> getIdentities() {
		return identities;
	}

	public void setIdentities(List<Identity> identities) {
		this.identities = identities;
	}

	public List<HabitStack> getHabitStacks() {
		return habitStacks;
	}

	public void setHabitStacks(List<HabitStack> habitStacks) {
		this.habitStacks = habitStacks;
	}

	public Tribe getTribe() {
		return tribe;
	}

	public void setTribe(Tribe tribe) {
		this.tribe = tribe;
	}
}
