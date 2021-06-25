package tribe.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tribe.controller.dto.TribeWithSystemsDto;
import tribe.service.TribeService;

@RestController
@RequestMapping("/tribes")
public class TribeController {

	protected TribeService tribeService;
	
	public TribeController(TribeService tribeService) {
		this.tribeService = tribeService;
	}

	@GetMapping(value= "/members", produces = "application/tribe-back-v1+json")
	public ResponseEntity<List<TribeWithSystemsDto>> getTribesWithSystems() {
		return ResponseEntity.status(HttpStatus.OK).body(tribeService.getTribesWithSystems());
	}

}
