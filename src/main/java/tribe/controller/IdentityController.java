package tribe.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestBody;
import tribe.controller.dto.IdentitiesDto;
import tribe.controller.dto.IdentityCategoryEnumDto;
import tribe.controller.dto.IdentityDto;
import tribe.domain.enumaration.IdentityCategoryEnum;
import tribe.service.IdentityService;

@RestController
@RequestMapping("/members/identities")
public class IdentityController {

	protected IdentityService identityService;

	public IdentityController(IdentityService identityService) {
		this.identityService = identityService;
	}

	@GetMapping(value="/categories", produces = "application/tribe-back-v1+json")
	public ResponseEntity<List<IdentityCategoryEnumDto>> getAllIdentityCategories() {
		return ResponseEntity.status(HttpStatus.OK).body(IdentityCategoryEnum.IDENTITY_CATEGORIES);
	}
	
	@PostMapping(produces = "application/tribe-back-v1+json")
	public ResponseEntity<List<IdentityDto>> createIdentities(@RequestBody @Valid IdentitiesDto identityDtos) {
		return ResponseEntity.status(HttpStatus.CREATED).body(identityService.createIdentities(identityDtos.getIdentities()));
	}

}
