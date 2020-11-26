package tribe.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Pictures {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
	
	@OneToOne
    protected Picture headlinePicture;
    
    @OneToMany(mappedBy = "pictures", cascade = CascadeType.ALL)
    protected List<Picture> pictures;

	public Pictures(Picture headlinePicture, List<Picture> pictures) {
		this.headlinePicture = headlinePicture;
		this.pictures = pictures;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Picture getHeadlinePicture() {
		return headlinePicture;
	}

	public void setHeadlinePicture(Picture headlinePicture) {
		this.headlinePicture = headlinePicture;
	}

	public List<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}
}
