package tribe.exception;

import tribe.controller.dto.ErrorCode;
import tribe.controller.dto.ErrorMessageDto;

public class NoProfileFoundException extends JobException {

	private static final long serialVersionUID = -668551635571316108L;

	public NoProfileFoundException(ErrorMessageDto errorMessageDto) {
		super(errorMessageDto);
	}
	
	public NoProfileFoundException() {
		super(new ErrorMessageDto(ErrorCode.PROFILE, "No profile found."));
	}

}
