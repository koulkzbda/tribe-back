package tribe.controller.advisors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import tribe.controller.dto.ErrorMessageDto;
import tribe.exception.EmailAlreadyExistsException;
import tribe.exception.NoMemberFoundException;
import tribe.exception.NoPublicationFoundException;


@ControllerAdvice
public class ErrorHandlerFeedbuzzControllerAdvice {

	@ExceptionHandler(NoMemberFoundException.class)
	public ResponseEntity<ErrorMessageDto> whenNoMemberFoundException (NoMemberFoundException e) {
		return ResponseEntity.badRequest().body(e.getErrorMessageDto());	
	}
	
	@ExceptionHandler(NoPublicationFoundException.class)
	public ResponseEntity<ErrorMessageDto> whenNoPublicationFoundException (NoPublicationFoundException e) {
		return ResponseEntity.badRequest().body(e.getErrorMessageDto());	
	}
	
	@ExceptionHandler(EmailAlreadyExistsException.class)
	public ResponseEntity<ErrorMessageDto> whenEmailAlreadyExistsException (EmailAlreadyExistsException e) {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getErrorMessageDto());	
	}
}
