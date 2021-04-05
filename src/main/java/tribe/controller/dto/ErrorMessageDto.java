package tribe.controller.dto;

public class ErrorMessageDto {

	protected ErrorCode code;
	
	protected String message;
	
	protected String details;

	public ErrorMessageDto(ErrorCode code, String message, String details) {
		this.code = code;
		this.message = message;
		this.details = details;
	}

	public ErrorMessageDto(ErrorCode code, String message) {
		this.code = code;
		this.message = message;
	}

	public ErrorCode getCode() {
		return code;
	}

	public void setCode(ErrorCode code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
}
