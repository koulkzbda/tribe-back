package tribe.controller.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import tribe.domain.habitTracking.HabitStack;

public class FeedbuzzDto {

	protected String id;
	
	protected String habitStackName;
	
	protected LocalDateTime time;
	
	protected Integer nbDays;

	protected List<ProgressionWithLatestRepetitionDto> progressions = new ArrayList<>();
	
	protected String userId;

	public FeedbuzzDto() {}
	
	public FeedbuzzDto(HabitStack habitStack) {
		this.id = habitStack.getId();
		this.habitStackName = habitStack.getName();
		if ( habitStack.getWeekdays().size() > 0 ) {
			this.nbDays = habitStack.getWeekdays().size();
			
			this.time = LocalDateTime.of(LocalDate.now(), habitStack.getWeekdays().iterator().next().getTime());
		}
		this.progressions = habitStack.getProgressions().stream().map(ProgressionWithLatestRepetitionDto::new).sorted(Comparator.comparing(ProgressionWithLatestRepetitionDto::getExecutionOrder)).collect(Collectors.toList());
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

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public List<ProgressionWithLatestRepetitionDto> getProgressions() {
		return progressions;
	}

	public void setProgressions(List<ProgressionWithLatestRepetitionDto> progressions) {
		this.progressions = progressions;
	}

	public Integer getNbDays() {
		return nbDays;
	}

	public void setNbDays(Integer nbDays) {
		this.nbDays = nbDays;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
