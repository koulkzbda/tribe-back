package tribe.controller.dto;

import java.util.ArrayList;
import java.util.List;

public class PublicationDto {

	protected String id;

	protected String content;
	
	protected Integer nbLikes;
	
	protected Integer nbComments;
	
	protected List<PictureDto> publicationPictures = new ArrayList<>();

	public PublicationDto() {}

	public PublicationDto(String id, String content, Integer nbLikes, Integer nbComments,
			List<PictureDto> publicationPictures) {
		this.id = id;
		this.content = content;
		this.nbLikes = nbLikes;
		this.nbComments = nbComments;
		this.publicationPictures = publicationPictures;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getNbLikes() {
		return nbLikes;
	}

	public void setNbLikes(Integer nbLikes) {
		this.nbLikes = nbLikes;
	}

	public Integer getNbComments() {
		return nbComments;
	}

	public void setNbComments(Integer nbComments) {
		this.nbComments = nbComments;
	}

	public List<PictureDto> getPublicationPictures() {
		return publicationPictures;
	}

	public void setPublicationPictures(List<PictureDto> publicationPictures) {
		this.publicationPictures = publicationPictures;
	}
}
