package tribe.domain.socialNetwork;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class PublicationPictures extends Pictures {

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn
    protected Publication publication;
	
	public PublicationPictures() {
		super();
	}

	public PublicationPictures(Set<Picture> pictures, Publication publication) {
		super(pictures);
		this.publication = publication;
	}

	public Publication getPublication() {
		return publication;
	}

	public void setPublication(Publication publication) {
		this.publication = publication;
	}

}
