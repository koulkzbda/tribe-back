package tribe.exception;

import tribe.controller.dto.ErrorMessageDto;

public abstract class JobException extends RuntimeException {

	private static final long serialVersionUID = -1730616489131923653L;
	
	protected ErrorMessageDto errorMessageDto;

	public JobException(ErrorMessageDto message) {
		super(message.getMessage());
		this.errorMessageDto = message;
	}

	public ErrorMessageDto getErrorMessageDto() {
		return errorMessageDto;
	}

}
