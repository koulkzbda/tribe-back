package tribe.controller.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tribe.domain.habitTracking.HabitStack;

public class HabitStackDto {

	protected String id;
	
	protected String habitStackName;
	
	protected List<ProgressionWithMetricsDto> progressions = new ArrayList<>();
	
	protected List<WeekdayDto> weekdays = new ArrayList<>();
	
	protected String userId;

	public HabitStackDto() {}
	
	public HabitStackDto(HabitStack habitStack) {
		this.id = habitStack.getId();
		this.habitStackName = habitStack.getName();
		if ( habitStack.getWeekdays().size() > 0 ) {
			this.weekdays = habitStack.getWeekdays().stream().map(WeekdayDto::new).collect(Collectors.toList());
		}
		this.progressions = habitStack.getProgressions().stream().map(ProgressionWithMetricsDto::new).collect(Collectors.toList());
		this.userId = habitStack.getSystem().getMember().getId();
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

	public List<WeekdayDto> getWeekdays() {
		return weekdays;
	}

	public void setWeekdays(List<WeekdayDto> weekdays) {
		this.weekdays = weekdays;
	}

	public List<ProgressionWithMetricsDto> getProgressions() {
		return progressions;
	}

	public void setProgressions(List<ProgressionWithMetricsDto> progressions) {
		this.progressions = progressions;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
