package tribe.controller.dto;

public class MemberCreatedDto {

    protected String firstName;

    protected String lastName;

    protected String email;
    
    protected String password;
    
    protected String emailConfirmationUrlTemplate;

	public MemberCreatedDto(String firstName, String lastName, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	public MemberCreatedDto(MemberCreatedDto member) {
		this.firstName = member.firstName;
		this.lastName = member.lastName;
		this.email = member.email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailConfirmationUrlTemplate() {
		return emailConfirmationUrlTemplate;
	}

	public void setEmailConfirmationUrlTemplate(String emailConfirmationUrlTemplate) {
		this.emailConfirmationUrlTemplate = emailConfirmationUrlTemplate;
	}
    
}
