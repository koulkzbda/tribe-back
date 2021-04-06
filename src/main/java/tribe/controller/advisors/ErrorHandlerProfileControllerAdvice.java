package tribe.controller.advisors;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import tribe.controller.dto.ErrorCode;
import tribe.controller.dto.ErrorMessageDto;
import tribe.exception.NoPicturesFoundException;
import tribe.exception.NoProfileFoundException;


@ControllerAdvice
public class ErrorHandlerProfileControllerAdvice extends ResponseEntityExceptionHandler {
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<String> messages = new ArrayList<>();
		ex.getBindingResult().getAllErrors().stream().forEach(error -> {
			messages.add(error.getDefaultMessage());
		});
		String entityName = ex.getBindingResult().getTarget().getClass().getName();
		ErrorMessageDto error = new ErrorMessageDto(
				ErrorCode.VALIDATION,
				"Invalid " + entityName,
				String.join("\n", messages));
		
		return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
	}

	
	@ExceptionHandler(NoProfileFoundException.class)
	public ResponseEntity<ErrorMessageDto> whenNoProfileFoundException (NoProfileFoundException e) {
		return ResponseEntity.badRequest().body(e.getErrorMessageDto());	
	}
	
	@ExceptionHandler(NoPicturesFoundException.class)
	public ResponseEntity<ErrorMessageDto> whenNoPicturesFoundException (NoPicturesFoundException e) {
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
