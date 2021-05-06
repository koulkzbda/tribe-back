package tribe.domain.socialNetwork;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class MemberWall extends Wall {

    @OneToMany(mappedBy = "memberWall", cascade = CascadeType.ALL)
    protected List<Publication> publications;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "member_id")
	protected Member member;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn
	protected MemberWallNotification memberWallNotification;

	public MemberWall() {
		super();
	}

	public MemberWall(List<Publication> publications, Member member) {
		super();
		this.publications = publications;
		this.member = member;
	}

	public List<Publication> getPublications() {
		return publications;
	}

	public void setPublications(List<Publication> publications) {
		this.publications = publications;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public MemberWallNotification getMemberWallNotification() {
		return memberWallNotification;
	}

	public void setMemberWallNotification(MemberWallNotification memberWallNotification) {
		this.memberWallNotification = memberWallNotification;
	}
}
