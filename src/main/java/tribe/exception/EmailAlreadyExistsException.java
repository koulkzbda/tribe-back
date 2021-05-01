package tribe.exception;

import tribe.controller.dto.ErrorCode;
import tribe.controller.dto.ErrorMessageDto;

public class EmailAlreadyExistsException extends JobException {

	private static final long serialVersionUID = -668551635571316108L;

	public EmailAlreadyExistsException(ErrorMessageDto errorMessageDto) {
		super(errorMessageDto);
	}
	
	public EmailAlreadyExistsException(String message) {
		super(new ErrorMessageDto(ErrorCode.SECURITY, message));
	}

}
