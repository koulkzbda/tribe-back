package tribe.controller.dto;

import tribe.domain.habitTracking.HabitStack;

public class HabitStackCreationDto extends HabitStackDto {
	
	protected String tribeId;
	
	protected String systemId;

	public HabitStackCreationDto() {
		super();
	}
	
	public HabitStackCreationDto(HabitStack habitStack) {
		super(habitStack);
	}

	public String getTribeId() {
		return tribeId;
	}

	public void setTribeId(String tribeId) {
		this.tribeId = tribeId;
	}

	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

}
