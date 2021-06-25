package tribe.controller.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tribe.domain.socialNetwork.TribeProfile;

public class TribeProfileDto {

	protected String id;

	protected String bio;
	
	protected List<PictureDto> profilePictures = new ArrayList<>();
	
	protected String tribeId;

	public TribeProfileDto() {}

	public TribeProfileDto(TribeProfile profile) {
		this.id = profile.getId();
		this.bio = profile.getBio();
		this.profilePictures = profile.getTribeProfilePictures().getPictures().stream().map(PictureDto::new).collect(Collectors.toList());
		this.tribeId = profile.getTribe().getId();
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<PictureDto> getProfilePictures() {
		return profilePictures;
	}

	public void setProfilePictures(List<PictureDto> profilePictures) {
		this.profilePictures = profilePictures;
	}

	public String getTribeId() {
		return tribeId;
	}

	public void setTribeId(String tribeId) {
		this.tribeId = tribeId;
	}
}
