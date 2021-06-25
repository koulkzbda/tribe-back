package tribe.controller.dto;

import java.util.ArrayList;
import java.util.List;

import tribe.domain.habitTracking.System;

public class SystemCreatedDto {

	protected String id;

	protected String name;
	
	protected List<IdentityDto> identities = new ArrayList<>();
	
	public SystemCreatedDto() {}

	public SystemCreatedDto(String id, String name, List<IdentityDto> identities) {
		this.id = id;
		this.name = name;
		this.identities = identities;
	}
	
	public SystemCreatedDto(System system, List<IdentityDto> identities) {
		id = system.getId();
		name = system.getName();
		this.identities = identities;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<IdentityDto> getIdentities() {
		return identities;
	}

	public void setIdentities(List<IdentityDto> identities) {
		this.identities = identities;
	}

}
