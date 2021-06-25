package tribe.domain.socialNetwork;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class MemberWallNotification extends Notification {

	@OneToOne(cascade = CascadeType.ALL)
	protected MemberWall memberWall;

	public MemberWallNotification() {
		super();
	}
	
	public MemberWallNotification(NotificationStatus notificationStatus, Member member, MemberWall memberWall) {
		super(notificationStatus, member);
		this.memberWall = memberWall;
	}

	public String getTitle() {
		return "Coucou";
	}

	public MemberWall getMemberWall() {
		return memberWall;
	}

	public void setMemberWall(MemberWall memberWall) {
		this.memberWall = memberWall;
	}
}
