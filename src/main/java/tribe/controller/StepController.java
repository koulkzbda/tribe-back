package tribe.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tribe.controller.dto.StepDto;
import tribe.service.StepService;

@RestController
@RequestMapping("/members/steps")
public class StepController {

	protected StepService stepService;
	
	public StepController(StepService stepService) {
		this.stepService = stepService;
	}

	@GetMapping(produces = "application/tribe-back-v1+json")
	public ResponseEntity<List<StepDto>> getTribesWithSystems() {
		return ResponseEntity.status(HttpStatus.OK).body(stepService.getStepsByMemberConnected());
	}

}
