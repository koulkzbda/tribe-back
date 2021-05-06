package tribe.domain.socialNetwork;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class TribeProfilePictures extends Pictures {

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn
    protected TribeProfile tribeProfile;
	
	public TribeProfilePictures() {
		super();
	}

	public TribeProfilePictures(List<Picture> pictures, TribeProfile tribeProfile) {
		super(pictures);
		this.tribeProfile = tribeProfile;
	}

	public TribeProfile getTribeProfile() {
		return tribeProfile;
	}

	public void setTribeProfile(TribeProfile tribeProfile) {
		this.tribeProfile = tribeProfile;
	}
}
