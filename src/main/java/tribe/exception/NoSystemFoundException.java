package tribe.exception;

import tribe.controller.dto.ErrorCode;
import tribe.controller.dto.ErrorMessageDto;
import tribe.controller.dto.HabitStackCreationDto;

public class NoSystemFoundException extends JobException {

	private static final long serialVersionUID = -668551635571316108L;
	
	public NoSystemFoundException(ErrorMessageDto errorMessageDto) {
		super(errorMessageDto);
	}
	
	public NoSystemFoundException() {
		super(new ErrorMessageDto(ErrorCode.ENTITY, "No system found"));
	}
	
	public NoSystemFoundException(HabitStackCreationDto hs) {
		super(new ErrorMessageDto(ErrorCode.ENTITY, "No tribe found with system id : " + hs.getSystemId()));
	}

}
