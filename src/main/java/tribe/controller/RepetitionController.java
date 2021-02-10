package tribe.controller;

import java.io.IOException;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
import tribe.service.RepetitionService;

@RestController// TO DO
@RequestMapping("/repetition")
public class RepetitionController {

	protected RepetitionService repetitionService;

	public RepetitionController(RepetitionService repetitionService) {
		this.repetitionService = repetitionService;
	}

	@PostMapping("/pictures")
	public ResponseEntity<?> addPublicationPictures(@RequestParam("files[]") MultipartFile[] files,
			@RequestParam("publicationId") String publicationId, @RequestParam(defaultValue = "-1") String headlinePictureName)
			throws IOException, NoSuchElementException {

		return ResponseEntity.status(HttpStatus.OK).body(repetitionService.addPublicationPictures(files, publicationId, headlinePictureName));

	}
	
	@PatchMapping("/headline-picture")
	public ResponseEntity<?> setProfilePicture(@RequestBody @Valid PictureDto pictureDto, BindingResult result,
			@RequestParam("publicationId") String publicationId) {
		if (result.hasErrors()) {
			throw new InvalidPictureException(new ErrorMessageDto(ErrorCode.VALIDATION, "Photo de profil invalide"));
		}

		return ResponseEntity.status(HttpStatus.OK).body(repetitionService.setHeadlinePicture(pictureDto, publicationId));
	}
	
	@DeleteMapping("/picture")
	public ResponseEntity<?> deleteProfilePicture(@RequestParam("publicationId") String publicationId, @RequestParam("pictureId") String pictureId) {

		return ResponseEntity.status(HttpStatus.OK).body(repetitionService.deletePicture(publicationId, pictureId));
	}

}
