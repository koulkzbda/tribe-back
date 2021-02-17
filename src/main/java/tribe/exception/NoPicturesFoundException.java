package tribe.exception;

import tribe.controller.dto.ErrorCode;
import tribe.controller.dto.ErrorMessageDto;

public class NoPicturesFoundException extends JobException {

	private static final long serialVersionUID = -668551635571316108L;

	public NoPicturesFoundException(ErrorMessageDto errorMessageDto) {
		super(errorMessageDto);
	}
	
	public NoPicturesFoundException() {
		super(new ErrorMessageDto(ErrorCode.PICTURE, "No pictures found."));
	}
	
	public NoPicturesFoundException(String EntityName, String id) {
		super(new ErrorMessageDto(ErrorCode.PICTURE, "No pictures found for " + EntityName + " with id = " + id));
	}

}
