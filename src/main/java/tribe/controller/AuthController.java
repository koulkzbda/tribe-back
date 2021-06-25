package tribe.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tribe.controller.dto.MemberCreatedDto;
import tribe.controller.dto.MemberDto;
import tribe.service.AuthService;

@RestController
public class AuthController {

	protected AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	@GetMapping(value = "/auto-login", produces = "application/tribe-back-v1+json")
	public ResponseEntity<MemberDto> autoLogin() {
		return ResponseEntity.status(HttpStatus.OK).body(authService.getMemberWithNewToken());
	}
	
	@PostMapping(value = "/register", produces = "application/tribe-back-v1+json")
	public ResponseEntity<MemberCreatedDto> createMember(@RequestBody @Valid MemberCreatedDto member) {
		return ResponseEntity.status(HttpStatus.CREATED).body(authService.createMember(member));
	}
	
	@GetMapping(value = "/confirmation", produces = "application/tribe-back-v1+json")
	public ResponseEntity<MemberDto> confirmMember(@RequestParam String id, @RequestParam String token) {
		return ResponseEntity.status(HttpStatus.OK).body(authService.confirmMember(id, token));
	}
	
	@PostMapping(value = "/send-email-confirmation", produces = "application/tribe-back-v1+json")
	public ResponseEntity<MemberCreatedDto> sendConfirmationEmail(@RequestBody @Valid MemberCreatedDto member) {
		return ResponseEntity.status(HttpStatus.OK).body(authService.sendConfirmationEmail(member));
	}
	
	@GetMapping(value = "/forgot-password", produces = "application/tribe-back-v1+json")
	public ResponseEntity<MemberDto> sendForgetPasswordEmail(@RequestParam String email, @RequestParam String resetPasswordUrl) {
		return ResponseEntity.status(HttpStatus.OK).body(authService.forgetPassword(email, resetPasswordUrl));
	}
	
	@GetMapping(value = "/reset-password", produces = "application/tribe-back-v1+json")
	public ResponseEntity<MemberDto> resetPassword(@RequestParam String id, @RequestParam String token, @RequestParam String password) {
		return ResponseEntity.status(HttpStatus.OK).body(authService.resetPassword(id, token, password));
	}

}
