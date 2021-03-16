package tribe.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import tribe.service.AuthService;

@RestController
public class AuthController {

	protected AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	@GetMapping("/auto-login")
	public ResponseEntity<?> autoLogin() {
		return ResponseEntity.status(HttpStatus.OK).body(authService.getMemberWithNewToken());
	}

}
