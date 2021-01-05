package tribe.exception;

import tribe.controller.dto.ErrorMessageDto;

public class InvalidProfileException extends JobException {

	private static final long serialVersionUID = -1760637759427676444L;

	public InvalidProfileException(ErrorMessageDto errorMessageDto) {
		super(errorMessageDto);
	}

}
