package tribe.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import tribe.controller.dto.MemberDto;
import tribe.repository.MemberRepo;

@RestController
public class AuthenticationController {

	protected MemberRepo memberRepo;

	public AuthenticationController(MemberRepo memberRepo) {
		this.memberRepo = memberRepo;
	}

	@GetMapping("/me")
	public ResponseEntity<?> whoAmI() {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		return this.memberRepo.findByEmail(email).map(MemberDto::new).map(ResponseEntity::ok)
				.orElse(ResponseEntity.badRequest().build());
	}

}
