package tribe.controller.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tribe.domain.Profile;

public class ProfileDto {

	protected Long id;

	protected String bio;
	
	protected List<PictureDto> profilePictures = new ArrayList<>();

	public ProfileDto() {}
	
	public ProfileDto(Long id, String bio) {
		this.id = id;
		this.bio = bio;
	}

	public ProfileDto(Long id, String bio, List<PictureDto> profilePictures) {
		super();
		this.id = id;
		this.bio = bio;
		this.profilePictures = profilePictures;
	}

	public ProfileDto(Profile profile) {
		this.id = profile.getId();
		this.bio = profile.getBio();
		this.profilePictures = profile.getProfilePictures().getPictures().stream().map(PictureDto::new).collect(Collectors.toList());
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<PictureDto> getProfilePictures() {
		return profilePictures;
	}

	public void setProfilePictures(List<PictureDto> profilePictures) {
		this.profilePictures = profilePictures;
	}

}
