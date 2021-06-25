package tribe.controller.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tribe.domain.habitTracking.System;

public class SystemDto {

	protected String id;

	protected String name;
	
	protected List<IdentityDto> identities = new ArrayList<>();
	
	protected List<HabitStackDto> habitStacks = new ArrayList<>();
	
	public SystemDto() {}

	public SystemDto(System system) {
		this.id = system.getId();
		this.name = system.getName();
		if (system.getIdentities().size() > 0) {
			this.identities = system.getIdentities().stream().map(IdentityDto::new).collect(Collectors.toList());
		}
		if (system.getHabitStacks().size() > 0) {
			this.habitStacks = system.getHabitStacks().stream().map(HabitStackDto::new).collect(Collectors.toList());
		}
		
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

	public List<HabitStackDto> getHabitStacks() {
		return habitStacks;
	}

	public void setHabitStacks(List<HabitStackDto> habitStacks) {
		this.habitStacks = habitStacks;
	}

}
