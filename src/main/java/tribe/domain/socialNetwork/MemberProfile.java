package tribe.domain.socialNetwork;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class MemberProfile extends Profile {

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn
	protected MemberProfilePictures memberProfilePictures;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "member_id")
	protected Member member;

	public MemberProfile() {
		super();
	}

	public MemberProfile(String bio, MemberProfilePictures memberProfilePictures, Member member) {
		super(bio);
		this.memberProfilePictures = memberProfilePictures;
		this.member = member;
	}

	public MemberProfilePictures getProfilePictures() {
		return memberProfilePictures;
	}

	public void setProfilePictures(MemberProfilePictures memberProfilePictures) {
		this.memberProfilePictures = memberProfilePictures;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
}
