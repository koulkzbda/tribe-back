package tribe.domain.socialNetwork;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class MemberProfilePictures extends Pictures {

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn
    protected MemberProfile memberProfile;
	
	public MemberProfilePictures() {
		super();
	}

	public MemberProfilePictures(List<Picture> pictures, MemberProfile memberProfile) {
		super(pictures);
		this.memberProfile = memberProfile;
	}

	public MemberProfile getMemberProfile() {
		return memberProfile;
	}

	public void setMemberProfile(MemberProfile memberProfile) {
		this.memberProfile = memberProfile;
	}
}
