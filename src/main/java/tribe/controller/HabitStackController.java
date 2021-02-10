package tribe.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tribe.controller.dto.ErrorCode;
import tribe.controller.dto.ErrorMessageDto;
import tribe.controller.dto.FeedbuzzDto;
import tribe.controller.dto.RepetitionFeedbuzzUpdateDto;
import tribe.exception.InvalidRepetitionFeedbuzzzException;
import tribe.service.HabitStackService;

@RestController
@RequestMapping("/habit-stack")
public class HabitStackController {

	protected HabitStackService habitStackService;

	public HabitStackController(HabitStackService habitStackService) {
		this.habitStackService = habitStackService;
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

		return ResponseEntity.status(HttpStatus.OK).body(habitStackService.updateRepetition(repetition));
	}

}
