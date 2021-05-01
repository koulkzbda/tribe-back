package tribe.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import tribe.domain.Member;
import tribe.domain.enumaration.Role;

public class MemberDto {

	protected String id;
	
    protected String firstName;

    protected String lastName;

    protected String email;

    protected List<Role> roles;
    
    protected String token;

	public MemberDto(String firstName, String lastName, String email, List<Role> roles) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.roles = roles;
	}
	
	public MemberDto(Member member) {
		id = member.getId();
		this.firstName = member.getFirstName();
		this.lastName = member.getLastName();
		this.email = member.getEmail();
		this.roles = member.getRoles().stream().map(role -> role.getRole()).collect(Collectors.toList());
	}
	
	public MemberDto(Member member, String token) {
		id = member.getId();
		this.firstName = member.getFirstName();
		this.lastName = member.getLastName();
		this.email = member.getEmail();
		this.roles = member.getRoles().stream().map(role -> role.getRole()).collect(Collectors.toList());
		this.token = token;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
    
}
