package tribe.exception;

import tribe.controller.dto.ErrorCode;
import tribe.controller.dto.ErrorMessageDto;
import tribe.controller.dto.ProgressionWithMetricsDto;

public class NoHabitFoundException extends JobException {

	private static final long serialVersionUID = -668551635571316108L;
	
	public NoHabitFoundException(ErrorMessageDto errorMessageDto) {
		super(errorMessageDto);
	}
	
	public NoHabitFoundException() {
		super(new ErrorMessageDto(ErrorCode.ENTITY, "No habit found"));
	}
	
	public NoHabitFoundException(ProgressionWithMetricsDto p) {
		super(new ErrorMessageDto(ErrorCode.ENTITY, "No habit found with id : " + p.getHabitId()));
	}

}
