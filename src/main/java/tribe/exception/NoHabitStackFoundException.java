package tribe.exception;

import tribe.controller.dto.ErrorCode;
import tribe.controller.dto.ErrorMessageDto;
import tribe.controller.dto.HabitStackCreationDto;

public class NoHabitStackFoundException extends JobException {

	private static final long serialVersionUID = -668551635571316108L;
	
	public NoHabitStackFoundException(ErrorMessageDto errorMessageDto) {
		super(errorMessageDto);
	}
	
	public NoHabitStackFoundException() {
		super(new ErrorMessageDto(ErrorCode.ENTITY, "No habit stack found"));
	}
	
	public NoHabitStackFoundException(HabitStackCreationDto hs) {
		super(new ErrorMessageDto(ErrorCode.ENTITY, "No habit stack found with id : " + hs.getId()));
	}

}
