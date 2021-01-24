package tribe.controller.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tribe.domain.HabitStack;

public class FeedbuzzDto {

	protected String id;
	
	protected String habitStackName;

	protected List<ProgressionDto> progressions = new ArrayList<>();

	public FeedbuzzDto() {}
	
	public FeedbuzzDto(HabitStack habitStack) {
		this.id = habitStack.getId();
		this.habitStackName = habitStack.getName();
		this.progressions = habitStack.getProgressions().stream().map(ProgressionDto::new).collect(Collectors.toList());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHabitStackName() {
		return habitStackName;
	}

	public void setHabitStackName(String habitStackName) {
		this.habitStackName = habitStackName;
	}

	public List<ProgressionDto> getProgressions() {
		return progressions;
	}

	public void setProgressions(List<ProgressionDto> progressions) {
		this.progressions = progressions;
	}
	
}
