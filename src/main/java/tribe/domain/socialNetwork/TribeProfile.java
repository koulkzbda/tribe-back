package tribe.domain.socialNetwork;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class TribeProfile extends Profile {

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn
	protected TribeProfilePictures tribeProfilePictures;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tribe_id")
	protected Tribe tribe;

	public TribeProfile() {
		super();
	}

	public TribeProfile(String bio, TribeProfilePictures tribeProfilePictures, Tribe tribe) {
		super(bio);
		this.tribeProfilePictures = tribeProfilePictures;
		this.tribe = tribe;
	}

	public TribeProfilePictures getTribeProfilePictures() {
		return tribeProfilePictures;
	}

	public void setTribeProfilePictures(TribeProfilePictures tribeProfilePictures) {
		this.tribeProfilePictures = tribeProfilePictures;
	}

	public Tribe getTribe() {
		return tribe;
	}

	public void setTribe(Tribe tribe) {
		this.tribe = tribe;
	}
}
