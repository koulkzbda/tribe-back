package tribe.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class TribeWall extends Wall {

    @OneToMany(mappedBy = "tribeWall", cascade = CascadeType.ALL)
    protected List<Publication> publications;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tribe_id")
	protected Tribe tribe;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn
	protected TribeWallNotification tribeWallNotification;

	public TribeWall() {
		super();
	}

	public TribeWall(List<Publication> publications, Tribe tribe) {
		super();
		this.publications = publications;
		this.tribe = tribe;
	}

	public List<Publication> getPublications() {
		return publications;
	}

	public void setPublications(List<Publication> publications) {
		this.publications = publications;
	}

	public Tribe getTribe() {
		return tribe;
	}

	public void setTribe(Tribe tribe) {
		this.tribe = tribe;
	}

	public TribeWallNotification getTribeWallNotification() {
		return tribeWallNotification;
	}

	public void setTribeWallNotification(TribeWallNotification tribeWallNotification) {
		this.tribeWallNotification = tribeWallNotification;
	}
}
