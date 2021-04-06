package tribe.controller;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.context.MessageSource;
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

import tribe.controller.dto.FeedbuzzDto;
import tribe.controller.dto.PictureDto;
import tribe.controller.dto.RepetitionFeedbuzzUpdateDto;
import tribe.service.HabitStackService;
import tribe.service.PublicationPicturesService;
import tribe.service.RepetitionService;

@RestController
@RequestMapping("/habit-stack-feedbuzz")
public class HabitStackFeedbuzzController {

	protected HabitStackService habitStackService;
	protected RepetitionService repetitionService;
	protected PublicationPicturesService publicationPicturesService;
	protected MessageSource messageSource;

	public HabitStackFeedbuzzController(HabitStackService habitStackService, RepetitionService repetitionService,
			PublicationPicturesService publicationPicturesService, MessageSource messageSource) {
		this.habitStackService = habitStackService;
		this.repetitionService = repetitionService;
		this.publicationPicturesService = publicationPicturesService;
		this.messageSource = messageSource;
	}

	@GetMapping(produces = "application/tribe-back-v1+json")
	public ResponseEntity<List<FeedbuzzDto>> findByConnectedMember() {
		return ResponseEntity.status(HttpStatus.OK).body(habitStackService.findByConnectedMember());
	}

	@PatchMapping(value = "/repetition", produces = "application/tribe-back-v1+json")
	public ResponseEntity<?> updateRepetition(@RequestBody @Valid RepetitionFeedbuzzUpdateDto repetition) {
		return ResponseEntity.status(HttpStatus.OK).body(repetitionService.updateRepetition(repetition));
	}

	@PostMapping(value = "/repetition/pictures", produces = "application/tribe-back-v1+json")
	public ResponseEntity<?> addPublicationPictures(@RequestParam("files[]") MultipartFile[] files,
			@RequestParam("publicationId") String publicationId,
			@RequestParam(defaultValue = "-1") String headlinePictureName) throws IOException, NoSuchElementException {

		return ResponseEntity.status(HttpStatus.OK)
				.body(publicationPicturesService.addPublicationPictures(files, publicationId, headlinePictureName));

	}

	@PatchMapping(value = "/repetition/headline-picture", produces = "application/tribe-back-v1+json")
	public ResponseEntity<?> setProfilePicture(@RequestBody @Valid PictureDto pictureDto, BindingResult result,
			@RequestParam("publicationId") String publicationId) {

		return ResponseEntity.status(HttpStatus.OK)
				.body(publicationPicturesService.setHeadlinePicture(pictureDto, publicationId));
	}

	@DeleteMapping(value = "/repetition/picture", produces = "application/tribe-back-v1+json")
	public ResponseEntity<?> deleteProfilePicture(@RequestParam("publicationId") String publicationId,
			@RequestParam("pictureId") String pictureId) {

		return ResponseEntity.status(HttpStatus.OK)
				.body(publicationPicturesService.deletePicture(publicationId, pictureId));
	}

}
