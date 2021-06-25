package tribe.exception;

import tribe.controller.dto.ErrorCode;
import tribe.controller.dto.ErrorMessageDto;
import tribe.controller.dto.IdentityDto;

public class NoIdentityFoundException extends JobException {

	private static final long serialVersionUID = -668551635571316108L;
	
	public NoIdentityFoundException(ErrorMessageDto errorMessageDto) {
		super(errorMessageDto);
	}
	
	public NoIdentityFoundException() {
		super(new ErrorMessageDto(ErrorCode.ENTITY, "No identity found"));
	}
	
	public NoIdentityFoundException(String memberId) {
		super(new ErrorMessageDto(ErrorCode.ENTITY, "No identity found with member id : " + memberId));
	}
	
	public NoIdentityFoundException(IdentityDto identity) {
		super(new ErrorMessageDto(ErrorCode.ENTITY, "No identity found with member id : " + identity.getId()));
	}

}
