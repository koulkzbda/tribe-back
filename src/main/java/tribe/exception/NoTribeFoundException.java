package tribe.exception;

import tribe.controller.dto.ErrorCode;
import tribe.controller.dto.ErrorMessageDto;
import tribe.controller.dto.HabitStackCreationDto;

public class NoTribeFoundException extends JobException {

	private static final long serialVersionUID = -668551635571316108L;
	
	public NoTribeFoundException(ErrorMessageDto errorMessageDto) {
		super(errorMessageDto);
	}
	
	public NoTribeFoundException() {
		super(new ErrorMessageDto(ErrorCode.ENTITY, "No tribe found"));
	}
	
	public NoTribeFoundException(HabitStackCreationDto hs) {
		super(new ErrorMessageDto(ErrorCode.ENTITY, "No tribe found with tribe id : " + hs.getTribeId()));
	}

}
