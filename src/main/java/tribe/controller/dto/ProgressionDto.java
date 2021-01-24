package tribe.controller.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tribe.domain.Identity;
import tribe.domain.Progression;

public class ProgressionDto {

	protected String progressionId;

	protected String habitId;
	
	protected String habitName;
	
	protected String gatewayHabit;
	
	protected LocationDto location;
	
	protected RepetitionDto repetition;
	
	protected Integer nbCompleted;
	
	protected List<String> identities = new ArrayList<>();
	
	protected List<String> identityCategories = new ArrayList<>();
	
	protected StepDto preparationHabit;
	
	protected StepDto contioningStep;
	
	protected StepDto reward;
	
	protected HabitContractDto habitContract;

	public ProgressionDto() {}
	
	public ProgressionDto(Progression progression) {
		this.progressionId = progression.getId();
		this.habitId = progression.getHabit().getId();
		this.habitName = progression.getHabit().getName();
		this.gatewayHabit = progression.getHabit().getGatewayHabit();
		this.location = new LocationDto(progression.getLocation());
		this.repetition = new RepetitionDto(progression.getRepetitions().get(0));
		if ( progression.getPreparationHabit() != null ) {
			this.preparationHabit = new StepDto(progression.getPreparationHabit());
		}
		if ( progression.getConditionningHabit() != null ) {
			this.contioningStep = new StepDto(progression.getConditionningHabit());
		}
		if ( progression.getReward() != null ) {
			this.reward = new StepDto(progression.getReward());
		}
		if (progression.getHabit().getIdentities() != null) {
			this.identities = progression.getHabit().getIdentities().stream().map(Identity::getName).collect(Collectors.toList());
			
			progression.getHabit().getIdentities().forEach(identity -> {
				identity.getWeightings().stream().forEach(weighting -> {
					identityCategories.add(weighting.getIdentityCategory().getIdentityCategory().name());
				});
			});
			identityCategories.stream().distinct().collect(Collectors.toList());
		}
		//  TO DO nbCompleted
	}

	public ProgressionDto(String progressionId, String habitId, String habitName, String gatewayHabit,
			LocationDto location, RepetitionDto repetition, Integer nbCompleted,
			List<String> identities, List<String> identityCategories) {
		this.progressionId = progressionId;
		this.habitId = habitId;
		this.habitName = habitName;
		this.gatewayHabit = gatewayHabit;
		this.location = location;
		this.repetition = repetition;
		this.nbCompleted = nbCompleted;
		this.identities = identities;
		this.identityCategories = identityCategories;
	}

	public String getProgressionId() {
		return progressionId;
	}

	public void setProgressionId(String progressionId) {
		this.progressionId = progressionId;
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

	public RepetitionDto getRepetition() {
		return repetition;
	}

	public void setRepetition(RepetitionDto repetition) {
		this.repetition = repetition;
	}

	public Integer getNbCompleted() {
		return nbCompleted;
	}

	public void setNbCompleted(Integer nbCompleted) {
		this.nbCompleted = nbCompleted;
	}

	public List<String> getIdentities() {
		return identities;
	}

	public void setIdentities(List<String> identities) {
		this.identities = identities;
	}

	public List<String> getIdentityCategories() {
		return identityCategories;
	}

	public void setIdentityCategories(List<String> identityCategories) {
		this.identityCategories = identityCategories;
	}

	public StepDto getContioningStep() {
		return contioningStep;
	}

	public void setContioningStep(StepDto contioningStep) {
		this.contioningStep = contioningStep;
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
}
