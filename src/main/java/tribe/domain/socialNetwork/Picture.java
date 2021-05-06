package tribe.domain.socialNetwork;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Picture {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	protected String id;

	protected String imageName;

	protected String imageType;

	@Lob
	protected byte[] imageFile;

	@CreationTimestamp
	protected LocalDateTime createdAt;
    
    @ManyToOne
    protected Pictures pictures;
    
    protected Boolean isHeadlinePicture;
    
    public Picture() {}

	public Picture(String imageName, String imageType, byte[] imageFile, Pictures pictures,
			Boolean isHeadlinePicture) {
		super();
		this.imageName = imageName;
		this.imageType = imageType;
		this.imageFile = imageFile;
		this.pictures = pictures;
		this.isHeadlinePicture = isHeadlinePicture;
	}

	public Picture(String imageName, String imageType, byte[] imageFile, Pictures pictures) {
		this.imageName = imageName;
		this.imageType = imageType;
		this.imageFile = imageFile;
		this.pictures = pictures;
		this.isHeadlinePicture = false;
	}

	public Picture(String imageName, String imageType, byte[] imageFile) {
		this.imageName = imageName;
		this.imageType = imageType;
		this.imageFile = imageFile;
		this.isHeadlinePicture = false;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	public byte[] getImageFile() {
		return imageFile;
	}

	public void setImageFile(byte[] imageFile) {
		this.imageFile = imageFile;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public Pictures getPictures() {
		return pictures;
	}

	public void setPictures(Pictures pictures) {
		this.pictures = pictures;
	}

	public Boolean getIsHeadlinePicture() {
		return isHeadlinePicture;
	}

	public void setIsHeadlinePicture(Boolean isHeadlinePicture) {
		this.isHeadlinePicture = isHeadlinePicture;
	}

}
