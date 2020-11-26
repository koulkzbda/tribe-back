package tribe.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Picture {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected Byte[] imageFile;
    
    protected String imageName;
    
    @ManyToOne
    protected Pictures pictures;

	public Picture(Byte[] imageFile, String imageName) {
		this.imageFile = imageFile;
		this.imageName = imageName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Byte[] getImageFile() {
		return imageFile;
	}

	public void setImageFile(Byte[] imageFile) {
		this.imageFile = imageFile;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Pictures getPictures() {
		return pictures;
	}

	public void setPictures(Pictures pictures) {
		this.pictures = pictures;
	}
}
