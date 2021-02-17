package tribe.exception;

import tribe.controller.dto.ErrorCode;
import tribe.controller.dto.ErrorMessageDto;

public class NoPublicationFoundException extends JobException {

	private static final long serialVersionUID = -668551635571316108L;

	public NoPublicationFoundException(ErrorMessageDto errorMessageDto) {
		super(errorMessageDto);
	}
	
	public NoPublicationFoundException() {
		super(new ErrorMessageDto(ErrorCode.PUBLICATION, "No publication found"));
	}
	
	public NoPublicationFoundException(String publicationId) {
		super(new ErrorMessageDto(ErrorCode.PUBLICATION, "No publication found with id " + publicationId));
	}

}
