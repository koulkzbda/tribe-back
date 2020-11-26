package tribe.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import tribe.controller.dto.ProfileDto;
import tribe.service.ProfileService;

@RestController
public class ProfileController {

	protected ProfileService profileService;

	public ProfileController(ProfileService profileService) {
		this.profileService = profileService;
	}

	@GetMapping("/profile")
	public ResponseEntity<ProfileDto> findByPassagerConnecte() {
		return ResponseEntity.status(HttpStatus.OK).body(profileService.findByConnectedUser());
	}

}
