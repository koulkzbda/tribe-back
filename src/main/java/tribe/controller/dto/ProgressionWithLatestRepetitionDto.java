package tribe.controller.dto;

import tribe.domain.habitTracking.Progression;

public class ProgressionWithLatestRepetitionDto extends ProgressionDto {
	
	protected RepetitionDto repetition;
	
	protected Integer nbCompleted;
	

	public ProgressionWithLatestRepetitionDto() {}
	
	public ProgressionWithLatestRepetitionDto(Progression progression) {
		super(progression);
	
		if (progression.getRepetitions().size() > 0) {
			this.repetition = new RepetitionDto(progression.getRepetitions().get(0));
		}
		
		//  TO DO nbCompleted
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
	
}
