package tribe.controller.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tribe.domain.socialNetwork.MemberProfile;

public class MemberProfileDto {

	protected String id;

	protected String bio;
	
	protected List<PictureDto> profilePictures = new ArrayList<>();
	
	protected String userId;

	public MemberProfileDto() {}
	
	public MemberProfileDto(String id, String bio) {
		this.id = id;
		this.bio = bio;
	}

	public MemberProfileDto(String id, String bio, List<PictureDto> profilePictures) {
		super();
		this.id = id;
		this.bio = bio;
		this.profilePictures = profilePictures;
	}

	public MemberProfileDto(MemberProfile profile) {
		this.id = profile.getId();
		this.bio = profile.getBio();
		this.profilePictures = profile.getProfilePictures().getPictures().stream().map(PictureDto::new).collect(Collectors.toList());
		this.userId = profile.getMember().getId();
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
