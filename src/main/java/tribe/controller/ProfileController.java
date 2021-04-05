package tribe.controller;

import java.io.IOException;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import tribe.controller.dto.ErrorCode;
import tribe.controller.dto.ErrorMessageDto;
import tribe.controller.dto.PictureDto;
import tribe.controller.dto.ProfileDto;
import tribe.exception.InvalidPictureException;
import tribe.exception.InvalidProfileException;
import tribe.service.ProfilePicturesService;
import tribe.service.ProfileService;

@RestController
@RequestMapping("/profile")
public class ProfileController {

	protected ProfileService profileService;
	protected ProfilePicturesService profilePicturesService;

	public ProfileController(ProfileService profileService, ProfilePicturesService profilePicturesService) {
		this.profileService = profileService;
		this.profilePicturesService = profilePicturesService;
	}

	@GetMapping(produces = "application/tribe-back-v1+json")
	public ResponseEntity<ProfileDto> findByConnectedMember() {
		return ResponseEntity.status(HttpStatus.OK).body(profileService.findByConnectedMember());
	}

	@PostMapping(value = "/pictures", produces = "application/tribe-back-v1+json")
	public ResponseEntity<?> uploadFile(@RequestParam("files[]") MultipartFile[] files,
			@RequestParam("profileId") String profileId, @RequestParam(defaultValue = "-1") String profilePictureName)
			throws IOException, NoSuchElementException {

		return ResponseEntity.status(HttpStatus.OK)
				.body(profilePicturesService.addProfilePictures(files, profileId, profilePictureName));

	}

	@PostMapping(value = "/bio", produces = "application/tribe-back-v1+json")
	public ResponseEntity<?> updateBio(@RequestBody ProfileDto profileDto, BindingResult result) {
		if (result.hasErrors()) {
			throw new InvalidProfileException(new ErrorMessageDto(ErrorCode.VALIDATION, "Profil invalide"));
		}

		return ResponseEntity.status(HttpStatus.OK).body(profileService.updateBio(profileDto));
	}

	@PatchMapping(value = "/profile-picture", produces = "application/tribe-back-v1+json")
	public ResponseEntity<?> setProfilePicture(@RequestBody @Valid PictureDto pictureDto, BindingResult result) {
		if (result.hasErrors()) {
			throw new InvalidPictureException(new ErrorMessageDto(ErrorCode.VALIDATION, "Photo de profil invalide"));
		}

		return ResponseEntity.status(HttpStatus.OK).body(profilePicturesService.setProfilePicture(pictureDto));
	}

	@DeleteMapping(value = "/picture/{id}", produces = "application/tribe-back-v1+json")
	public ResponseEntity<?> deleteProfilePicture(@PathVariable String id) {

		return ResponseEntity.status(HttpStatus.OK).body(profilePicturesService.deleteProfilePicture(id));
	}

}
