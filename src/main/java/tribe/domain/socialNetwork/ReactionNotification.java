package tribe.domain.socialNetwork;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class ReactionNotification extends Notification {

	@OneToOne(cascade = CascadeType.ALL)
	protected Publication publication;

	public ReactionNotification() {
		super();
	}

	public ReactionNotification(NotificationStatus notificationStatus, Member member, Publication publication) {
		super(notificationStatus, member);
		this.publication = publication;
	}
	
	public String getTitle() {
		return this.publication.author.firstName;
	}

	public Publication getPublication() {
		return publication;
	}

	public void setPublication(Publication publication) {
		this.publication = publication;
	}
}
