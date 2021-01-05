package tribe.controller.dto;

import java.time.LocalDateTime;

import tribe.domain.Picture;

public class PictureDto {
	
	protected String id;
	
	protected String imageName;

	protected String imageType;

	protected byte[] imageFile;

	protected LocalDateTime createdAt;
    
    protected Boolean isHeadlinePicture;

	public PictureDto(String id, String imageName, String imageType, byte[] imageFile, LocalDateTime createdAt,
			Boolean isHeadlinePicture) {
		this.id = id;
		this.imageName = imageName;
		this.imageType = imageType;
		this.imageFile = imageFile;
		this.createdAt = createdAt;
		this.isHeadlinePicture = isHeadlinePicture;
	}
	
	public PictureDto(Picture picture) {
		this.id = picture.getId();
		this.imageName = picture.getImageName();
		this.imageType = picture.getImageType();
		this.imageFile = picture.getImageFile();
		this.createdAt = picture.getCreatedAt();
		this.isHeadlinePicture = picture.getIsHeadlinePicture();
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

	public Boolean getIsHeadlinePicture() {
		return isHeadlinePicture;
	}

	public void setIsHeadlinePicture(Boolean isHeadlinePicture) {
		this.isHeadlinePicture = isHeadlinePicture;
	}
}
