package tribe.controller.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tribe.domain.habitTracking.Progression;

public class ProgressionDto {

	protected String progressionId;
	
	protected String versionName;

	protected String habitId;
	
	protected String habitName;
	
	protected String gatewayHabit;
	
	protected LocationDto location;
	
	protected List<IdentityDto> identities = new ArrayList<>();
	
	protected StepDto preparationHabit;
	
	protected StepDto conditioningStep;
	
	protected StepDto reward;
	
	protected HabitContractDto habitContract;
	
	protected Integer executionOrder;
	
	protected Boolean isActive;

	public ProgressionDto() {}
	
	public ProgressionDto(Progression progression) {
		this.progressionId = progression.getId();
		this.versionName = progression.getVersionName();
		this.habitId = progression.getHabit().getId();
		this.habitName = progression.getHabit().getName();
		this.gatewayHabit = progression.getHabit().getGatewayHabit();
		this.location = new LocationDto(progression.getLocation());
		if ( progression.getPreparationHabit() != null ) {
			this.preparationHabit = new StepDto(progression.getPreparationHabit());
		}
		if ( progression.getConditioningStep() != null ) {
			this.conditioningStep = new StepDto(progression.getConditioningStep());
		}
		if ( progression.getReward() != null ) {
			this.reward = new StepDto(progression.getReward());
		}
		if (progression.getHabit().getIdentities() != null) {
			this.identities = progression.getHabit().getIdentities().stream().map(IdentityDto::new).collect(Collectors.toList());
		}
		if (progression.getHabitContract() != null) {
			this.habitContract = new HabitContractDto(progression.getHabitContract());
		}
		executionOrder = progression.getExecutionOrder();
		isActive = progression.getIsActive();
	}

	public String getProgressionId() {
		return progressionId;
	}

	public void setProgressionId(String progressionId) {
		this.progressionId = progressionId;
	}

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public String getHabitId() {
		return habitId;
	}

	public void setHabitId(String habitId) {
		this.habitId = habitId;
	}

	public String getHabitName() {
		return habitName;
	}

	public void setHabitName(String habitName) {
		this.habitName = habitName;
	}

	public String getGatewayHabit() {
		return gatewayHabit;
	}

	public void setGatewayHabit(String gatewayHabit) {
		this.gatewayHabit = gatewayHabit;
	}

	public LocationDto getLocation() {
		return location;
	}

	public void setLocation(LocationDto location) {
		this.location = location;
	}

	public List<IdentityDto> getIdentities() {
		return identities;
	}

	public void setIdentities(List<IdentityDto> identities) {
		this.identities = identities;
	}

	public StepDto getConditioningStep() {
		return conditioningStep;
	}

	public void setConditioningStep(StepDto conditioningStep) {
		this.conditioningStep = conditioningStep;
	}

	public StepDto getReward() {
		return reward;
	}

	public void setReward(StepDto reward) {
		this.reward = reward;
	}

	public HabitContractDto getHabitContract() {
		return habitContract;
	}

	public void setHabitContract(HabitContractDto habitContract) {
		this.habitContract = habitContract;
	}

	public StepDto getPreparationHabit() {
		return preparationHabit;
	}

	public void setPreparationHabit(StepDto preparationHabit) {
		this.preparationHabit = preparationHabit;
	}

	public Integer getExecutionOrder() {
		return executionOrder;
	}

	public void setExecutionOrder(Integer executionOrder) {
		this.executionOrder = executionOrder;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
}
