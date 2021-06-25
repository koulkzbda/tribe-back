package tribe.controller;

import javax.validation.Valid;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tribe.controller.dto.HabitStackCreationDto;
import tribe.controller.dto.HabitStackDto;
import tribe.service.HabitStackService;

@RestController
@RequestMapping("/members/habit-stacks")
public class HabitStackController {

	protected HabitStackService habitStackService;

	public HabitStackController(HabitStackService habitStackService,
			MessageSource messageSource) {
		this.habitStackService = habitStackService;
	}

	@PostMapping(value = "/habits", produces = "application/tribe-back-v1+json")
	public ResponseEntity<HabitStackDto> createHabit(@RequestBody @Valid HabitStackCreationDto habitStack) {
		return ResponseEntity.status(HttpStatus.OK).body(habitStackService.createHabit(habitStack));
	}

}
