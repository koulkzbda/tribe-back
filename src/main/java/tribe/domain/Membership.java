package tribe.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import tribe.domain.key.MembershipKey;

@Entity
public class Membership {

	@EmbeddedId
	private MembershipKey id = new MembershipKey();

	@ManyToOne
	@MapsId(value = "memberId")
	protected Member member;

	@ManyToOne
	@MapsId(value = "tribeId")
	protected Tribe tribe;

	@CreationTimestamp
	protected LocalDateTime joinedAt;

	@OneToMany(mappedBy = "membership", cascade = CascadeType.ALL)
	protected List<RoleTribe> rolesTribe = new ArrayList<>();

	public Membership() {
	}

	public Membership(Member member, Tribe tribe, List<RoleTribe> rolesTribe) {
		this.member = member;
		this.tribe = tribe;
		this.rolesTribe = rolesTribe;
	}

	public MembershipKey getId() {
		return id;
	}

	public void setId(MembershipKey id) {
		this.id = id;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Tribe getTribe() {
		return tribe;
	}

	public void setTribe(Tribe tribe) {
		this.tribe = tribe;
	}

	public LocalDateTime getJoinedAt() {
		return joinedAt;
	}

	public void setJoinedAt(LocalDateTime joinedAt) {
		this.joinedAt = joinedAt;
	}

	public List<RoleTribe> getRolesTribe() {
		return rolesTribe;
	}

	public void setRolesTribe(List<RoleTribe> rolesTribe) {
		this.rolesTribe = rolesTribe;
	}
}
