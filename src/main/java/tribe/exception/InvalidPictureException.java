package tribe.exception;

import tribe.controller.dto.ErrorMessageDto;

public class InvalidPictureException extends JobException {

	public InvalidPictureException(ErrorMessageDto errorMessageDto) {
		super(errorMessageDto);
	}

	private static final long serialVersionUID = -910815418388092852L;

}
