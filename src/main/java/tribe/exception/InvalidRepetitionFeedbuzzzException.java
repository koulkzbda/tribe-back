package tribe.exception;

import tribe.controller.dto.ErrorMessageDto;

public class InvalidRepetitionFeedbuzzzException extends JobException {

	public InvalidRepetitionFeedbuzzzException(ErrorMessageDto errorMessageDto) {
		super(errorMessageDto);
	}

	private static final long serialVersionUID = -910815418388092852L;

}
