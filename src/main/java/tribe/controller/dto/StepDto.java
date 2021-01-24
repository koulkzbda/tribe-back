package tribe.controller.dto;

import tribe.domain.Step;

public class StepDto {
	
	protected String id;
	
	protected String description;

	protected LocationDto location;
	
	public StepDto() {}
	
	public StepDto(Step step) {
		this.id = step.getId();
		this.description = step.getDescription();
		this.location = new LocationDto(step.getLocation());
	}

	public StepDto(String id, String description, LocationDto location) {
		this.id = id;
		this.description = description;
		this.location = location;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocationDto getLocation() {
		return location;
	}

	public void setLocation(LocationDto location) {
		this.location = location;
	}
}
