package tribe.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tribe.controller.dto.FeedbuzzDto;
import tribe.service.HabitStackService;

@RestController
@RequestMapping("/habit-stack")
public class HabitStackController {

	protected HabitStackService habitStackService;

	public HabitStackController(HabitStackService habitStackService) {
		this.habitStackService = habitStackService;
	}

	
	// TO DO Create HabitStackDto
	@GetMapping
	public ResponseEntity<List<FeedbuzzDto>> findByConnectedMember() {
		return ResponseEntity.status(HttpStatus.OK).body(habitStackService.findByConnectedMember());
	}

//	@PostMapping("/pictures")
//	public ResponseEntity<?> uploadFile(@RequestParam("files[]") MultipartFile[] files,
//			@RequestParam("profileId") String profileId, @RequestParam(defaultValue = "-1") String profilePictureName)
//			throws IOException, NoSuchElementException {
//
//		return ResponseEntity.status(HttpStatus.OK).body(habitStackService.addProfilePictures(files, profileId, profilePictureName));
//
//	}
//	
//	@PostMapping("/bio")
//	public ResponseEntity<?> updateBio(@RequestBody ProfileDto profileDto, BindingResult result) {
//		if (result.hasErrors()) {
//			throw new InvalidProfileException(new ErrorMessageDto(ErrorCode.VALIDATION, "Profil invalide"));
//		}
//
//		return ResponseEntity.status(HttpStatus.OK).body(habitStackService.updateBio(profileDto));
//	}
//
//	@PatchMapping("/profile-picture")
//	public ResponseEntity<?> setProfilePicture(@RequestBody @Valid PictureDto pictureDto, BindingResult result) {
//		if (result.hasErrors()) {
//			throw new InvalidPictureException(new ErrorMessageDto(ErrorCode.VALIDATION, "Photo de profil invalide"));
//		}
//
//		return ResponseEntity.status(HttpStatus.OK).body(habitStackService.setProfilePicture(pictureDto));
//	}
//	
//	@DeleteMapping("/picture/{id}")
//	public ResponseEntity<?> deleteProfilePicture(@PathVariable String id) {
//
//		return ResponseEntity.status(HttpStatus.OK).body(habitStackService.deleteProfilePicture(id));
//	}

}
