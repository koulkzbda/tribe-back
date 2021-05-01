package tribe.exception;

import tribe.controller.dto.ErrorCode;
import tribe.controller.dto.ErrorMessageDto;

public class NoMemberFoundException extends JobException {

	private static final long serialVersionUID = -668551635571316108L;

	public NoMemberFoundException(ErrorMessageDto errorMessageDto) {
		super(errorMessageDto);
	}
	
	public NoMemberFoundException() {
		super(new ErrorMessageDto(ErrorCode.SECURITY, "No member connected or inexisting member connected."));
	}
	
	public NoMemberFoundException(String message) {
		super(new ErrorMessageDto(ErrorCode.SECURITY, message));
	}

}
