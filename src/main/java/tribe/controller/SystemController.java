package tribe.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tribe.controller.dto.SystemCreatedDto;
import tribe.controller.dto.SystemDto;
import tribe.service.SystemService;

@RestController
@RequestMapping("/members/systems")
public class SystemController {

	protected SystemService systemService;

	public SystemController(SystemService systemService) {
		this.systemService = systemService;
	}
	
	@GetMapping(produces = "application/tribe-back-v1+json")
	public ResponseEntity<List<SystemDto>> getSystemsByConnectedMember() {
		return ResponseEntity.status(HttpStatus.OK).body(systemService.getSystemsByConnectedMember());
	}
	
	@PostMapping(produces = "application/tribe-back-v1+json")
	public ResponseEntity<SystemCreatedDto> createSystem(@RequestBody @Valid SystemCreatedDto system) {
		return ResponseEntity.status(HttpStatus.CREATED).body(systemService.createSystem(system));
	}
	
}
