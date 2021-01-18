package tribe.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class TribeWallNotification extends Notification {

	@OneToOne(cascade = CascadeType.ALL)
	protected TribeWall tribeWall;

	public TribeWallNotification() {
		super();
	}
	
	public TribeWallNotification(NotificationStatus notificationStatus, Member member, TribeWall tribeWall) {
		super(notificationStatus, member);
		this.tribeWall = tribeWall;
	}

	public String getTitle() {
		return "Coucou";
	}

	public TribeWall getTribeWall() {
		return tribeWall;
	}

	public void setTribeWall(TribeWall tribeWall) {
		this.tribeWall = tribeWall;
	}
}
