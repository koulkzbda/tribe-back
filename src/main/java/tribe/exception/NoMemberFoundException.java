package tribe.exception;

import tribe.controller.dto.ErrorCode;
import tribe.controller.dto.ErrorMessageDto;
import tribe.controller.dto.MemberCreatedDto;

public class NoMemberFoundException extends JobException {

	private static final long serialVersionUID = -668551635571316108L;

	public NoMemberFoundException(ErrorMessageDto errorMessageDto) {
		super(errorMessageDto);
	}
	
	public NoMemberFoundException() {
		super(new ErrorMessageDto(ErrorCode.SECURITY, "No member connected or inexisting member connected."));
	}
	
	public NoMemberFoundException(MemberCreatedDto member) {
		super(new ErrorMessageDto(ErrorCode.SECURITY, "No member with email : " + member.getEmail()));
	}
	
	public NoMemberFoundException(String message) {
		super(new ErrorMessageDto(ErrorCode.SECURITY, message));
	}

}
