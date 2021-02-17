package tribe.controller;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import tribe.controller.dto.ErrorCode;
import tribe.controller.dto.ErrorMessageDto;
import tribe.controller.dto.FeedbuzzDto;
import tribe.controller.dto.PictureDto;
import tribe.controller.dto.RepetitionFeedbuzzUpdateDto;
import tribe.exception.InvalidPictureException;
import tribe.exception.InvalidRepetitionFeedbuzzzException;
import tribe.service.HabitStackService;
import tribe.service.PublicationPicturesService;
import tribe.service.RepetitionService;

@RestController
@RequestMapping("/habit-stack-feedbuzz")
public class HabitStackFeedbuzzController {

	protected HabitStackService habitStackService;
	protected RepetitionService repetitionService;
	protected PublicationPicturesService publicationPicturesService;

	public HabitStackFeedbuzzController(HabitStackService habitStackService, RepetitionService repetitionService, PublicationPicturesService publicationPicturesService) {
		this.habitStackService = habitStackService;
		this.repetitionService = repetitionService;
		this.publicationPicturesService = publicationPicturesService;
	}

	
	@GetMapping
	public ResponseEntity<List<FeedbuzzDto>> findByConnectedMember() {
		return ResponseEntity.status(HttpStatus.OK).body(habitStackService.findByConnectedMember());
	}
	
	@PatchMapping("/repetition")
	public ResponseEntity<?> updateRepetition(@RequestBody @Valid RepetitionFeedbuzzUpdateDto repetition, BindingResult result) {
		if (result.hasErrors()) {
			throw new InvalidRepetitionFeedbuzzzException(new ErrorMessageDto(ErrorCode.VALIDATION, "Répétition invalide."));
		}

		return ResponseEntity.status(HttpStatus.OK).body(repetitionService.updateRepetition(repetition));
	}
	
	@PostMapping("/repetition/pictures")
	public ResponseEntity<?> addPublicationPictures(@RequestParam("files[]") MultipartFile[] files,
			@RequestParam("publicationId") String publicationId, @RequestParam(defaultValue = "-1") String headlinePictureName)
			throws IOException, NoSuchElementException {

		return ResponseEntity.status(HttpStatus.OK).body(publicationPicturesService.addPublicationPictures(files, publicationId, headlinePictureName));

	}
	
	@PatchMapping("/repetition/headline-picture")
	public ResponseEntity<?> setProfilePicture(@RequestBody @Valid PictureDto pictureDto, BindingResult result,
			@RequestParam("publicationId") String publicationId) {
		if (result.hasErrors()) {
			throw new InvalidPictureException(new ErrorMessageDto(ErrorCode.VALIDATION, "Photo de profil invalide"));
		}

		return ResponseEntity.status(HttpStatus.OK).body(publicationPicturesService.setHeadlinePicture(pictureDto, publicationId));
	}
	
	@DeleteMapping("/repetition/picture")
	public ResponseEntity<?> deleteProfilePicture(@RequestParam("publicationId") String publicationId, @RequestParam("pictureId") String pictureId) {

		return ResponseEntity.status(HttpStatus.OK).body(publicationPicturesService.deletePicture(publicationId, pictureId));
	}

}
