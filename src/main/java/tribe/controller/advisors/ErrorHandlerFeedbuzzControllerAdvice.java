package tribe.controller.advisors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import tribe.controller.dto.ErrorMessageDto;
import tribe.exception.InvalidRepetitionFeedbuzzzException;


@ControllerAdvice
public class ErrorHandlerFeedbuzzControllerAdvice {

	@ExceptionHandler(InvalidRepetitionFeedbuzzzException.class)
	public ResponseEntity<ErrorMessageDto> whenInvalidRepetitionFeedbuzzzException (InvalidRepetitionFeedbuzzzException e) {
		return ResponseEntity.badRequest().body(e.getErrorMessageDto());	
	}
}
