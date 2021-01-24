package tribe.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Member {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	protected String id;

    protected String firstName;

    protected String lastName;

    protected String email;

    protected String pass;
    
    protected LocalDateTime registeredAt;
    
    @OneToOne(cascade = CascadeType.ALL)
    protected MemberProfile memberProfile;
    
    @OneToOne(cascade = CascadeType.ALL)
    protected MemberWall memberWall;
    
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    protected List<RoleMember> roles = new ArrayList<>();
    
    @OneToMany(mappedBy = "liker")
	protected List<Like> likes = new ArrayList<>();
    
    @OneToMany(mappedBy = "member")
	protected List<System> systems = new ArrayList<>();
    
    @OneToMany(mappedBy = "accountabilityPartner", cascade = CascadeType.ALL)
    protected List<HabitContract> habitContracts = new ArrayList<>();
    
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    protected List<Step> habitScorecards = new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "member")
	protected List<Membership> memberships = new ArrayList<>();
    
    public Member() {}

	public Member(String firstName, String lastName, String email, String pass, LocalDateTime registeredAt,
			MemberProfile memberProfile, MemberWall memberWall, List<RoleMember> roles, List<Like> likes,
			List<System> systems, List<HabitContract> habitContracts, List<Step> habitScorecards) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.pass = pass;
		this.registeredAt = registeredAt;
		this.memberProfile = memberProfile;
		this.memberWall = memberWall;
		this.roles = roles;
		this.likes = likes;
		this.systems = systems;
		this.habitContracts = habitContracts;
		this.habitScorecards = habitScorecards;
	}

	public Member(String firstName, String lastName, String email, String pass, LocalDateTime registeredAt,
			MemberProfile memberProfile, List<RoleMember> roles, List<Like> likes, List<System> systems,
			List<HabitContract> habitContracts, List<Step> habitScorecards) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.pass = pass;
		this.registeredAt = registeredAt;
		this.memberProfile = memberProfile;
		this.roles = roles;
		this.likes = likes;
		this.systems = systems;
		this.habitContracts = habitContracts;
		this.habitScorecards = habitScorecards;
	}

	public Member(String firstName, String lastName, String email, String pass, LocalDateTime registeredAt,
			MemberProfile memberProfile, List<RoleMember> roles, List<Like> likes, List<System> systems) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.pass = pass;
		this.registeredAt = registeredAt;
		this.memberProfile = memberProfile;
		this.roles = roles;
		this.likes = likes;
		this.systems = systems;
	}

	public Member(String firstName, String lastName, String email, String pass, LocalDateTime registeredAt,
			MemberProfile memberProfile, List<RoleMember> roles) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.pass = pass;
		this.registeredAt = registeredAt;
		this.memberProfile = memberProfile;
		this.roles = roles;
	}

	public Member(String id, String firstName, String lastName, String email, String pass, LocalDateTime registeredAt,
			MemberProfile memberProfile, List<RoleMember> roles, List<Like> likes) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.pass = pass;
		this.registeredAt = registeredAt;
		this.memberProfile = memberProfile;
		this.roles = roles;
		this.likes = likes;
	}

	public Member(String firstName, String lastName, String email, String pass, LocalDateTime registeredAt,
			MemberProfile memberProfile, List<RoleMember> roles, List<Like> likes, List<System> systems,
			List<HabitContract> habitContracts) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.pass = pass;
		this.registeredAt = registeredAt;
		this.memberProfile = memberProfile;
		this.roles = roles;
		this.likes = likes;
		this.systems = systems;
		this.habitContracts = habitContracts;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public LocalDateTime getRegisteredAt() {
		return registeredAt;
	}

	public void setRegisteredAt(LocalDateTime registeredAt) {
		this.registeredAt = registeredAt;
	}

	public MemberProfile getMemberProfile() {
		return memberProfile;
	}

	public void setMemberProfile(MemberProfile memberProfile) {
		this.memberProfile = memberProfile;
	}

	public MemberWall getMemberWall() {
		return memberWall;
	}

	public void setMemberWall(MemberWall memberWall) {
		this.memberWall = memberWall;
	}

	public List<RoleMember> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleMember> roles) {
		this.roles = roles;
	}

	public List<Like> getLikes() {
		return likes;
	}

	public void setLikes(List<Like> likes) {
		this.likes = likes;
	}

	public List<System> getSystems() {
		return systems;
	}

	public void setSystems(List<System> systems) {
		this.systems = systems;
	}

	public List<HabitContract> getHabitContracts() {
		return habitContracts;
	}

	public void setHabitContracts(List<HabitContract> habitContracts) {
		this.habitContracts = habitContracts;
	}

	public List<Step> getHabitScorecards() {
		return habitScorecards;
	}

	public void setHabitScorecards(List<Step> habitScorecards) {
		this.habitScorecards = habitScorecards;
	}

	public List<Membership> getMemberships() {
		return memberships;
	}

	public void setMemberships(List<Membership> memberships) {
		this.memberships = memberships;
	}
}
