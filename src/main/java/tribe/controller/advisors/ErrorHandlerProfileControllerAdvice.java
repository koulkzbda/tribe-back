package tribe.controller.advisors;

import java.io.IOException;
import java.util.NoSuchElementException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import tribe.controller.dto.ErrorCode;
import tribe.controller.dto.ErrorMessageDto;
import tribe.exception.InvalidPictureException;
import tribe.exception.InvalidProfileException;


@ControllerAdvice
public class ErrorHandlerProfileControllerAdvice {

	@ExceptionHandler(InvalidPictureException.class)
	public ResponseEntity<ErrorMessageDto> whenInvalidPictureException (InvalidPictureException e) {
		return ResponseEntity.badRequest().body(e.getErrorMessageDto());	
	}
	
	@ExceptionHandler(InvalidProfileException.class)
	public ResponseEntity<ErrorMessageDto> whenInvalidProfileException (InvalidProfileException e) {
		return ResponseEntity.badRequest().body(e.getErrorMessageDto());	
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<ErrorMessageDto> whenNoSuchElementException (NoSuchElementException e) {
		return ResponseEntity.badRequest().body(new ErrorMessageDto(ErrorCode.PICTURE, e.getMessage()));	
	}
	
	@ExceptionHandler(IOException.class)
	public ResponseEntity<ErrorMessageDto> whenIOException (IOException e) {
		return ResponseEntity.badRequest().body(new ErrorMessageDto(ErrorCode.PICTURE, e.getMessage()));	
	}
	
	
}
