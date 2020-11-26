package tribe.controller.dto;

import tribe.domain.Profile;

public class ProfileDto {

    protected String bio;

	public ProfileDto(String bio) {
		this.bio = bio;
	}
	
	public ProfileDto(Profile profile) {
		this.bio = profile.getBio();
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}
    
}
