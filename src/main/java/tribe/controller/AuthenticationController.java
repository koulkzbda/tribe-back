package tribe.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import tribe.controller.dto.UserDto;
import tribe.repository.UserRepo;

@RestController
public class AuthenticationController {

	protected UserRepo userRepo;

	public AuthenticationController(UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	@GetMapping("/me")
	public ResponseEntity<?> whoAmI() {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		return this.userRepo.findByEmail(email).map(UserDto::new).map(ResponseEntity::ok)
				.orElse(ResponseEntity.badRequest().build());
	}

}
