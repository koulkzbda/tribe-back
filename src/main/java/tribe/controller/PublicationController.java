package tribe.controller;

import java.io.IOException;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import tribe.exception.InvalidPictureException;
import tribe.service.ProfileService;

@RestController// TO DO
@RequestMapping("/publication")
public class PublicationController {

	protected ProfileService profileService;

	public PublicationController(ProfileService profileService) {
		this.profileService = profileService;
	}

	@PostMapping("/pictures")
	public ResponseEntity<?> uploadFile(@RequestParam("files[]") MultipartFile[] files,
			@RequestParam("profileId") String profileId, @RequestParam(defaultValue = "-1") String profilePictureName)
			throws IOException, NoSuchElementException {

		return ResponseEntity.status(HttpStatus.OK).body(profileService.addProfilePictures(files, profileId, profilePictureName));

	}
	
	@PatchMapping("/profile-picture")
	public ResponseEntity<?> setProfilePicture(@RequestBody @Valid PictureDto pictureDto, BindingResult result) {
		if (result.hasErrors()) {
			throw new InvalidPictureException(new ErrorMessageDto(ErrorCode.VALIDATION, "Photo de profil invalide"));
		}

		return ResponseEntity.status(HttpStatus.OK).body(profileService.setProfilePicture(pictureDto));
	}
	
	@DeleteMapping("/picture/{id}")
	public ResponseEntity<?> deleteProfilePicture(@PathVariable String id) {

		return ResponseEntity.status(HttpStatus.OK).body(profileService.deleteProfilePicture(id));
	}

}
