package tribe.domain.habitTracking;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import tribe.controller.dto.LocationDto;
import tribe.controller.dto.StepDto;
import tribe.domain.socialNetwork.Location;
import tribe.domain.socialNetwork.Member;


@Entity
public class Step extends HabitVersion {
	
	protected String score;
	
	@ManyToOne(cascade = CascadeType.ALL)
	protected Member member;
	
    public Step() {
    	super();
    }
    
    public Step(StepDto step) {
    	super();
    	description = step.getDescription();
    	isActive = true;
    	
    	LocationDto newLocation = step.getLocation();
    	if(newLocation != null) {
    		location = new Location(newLocation);
    	}
    }

	public Step(String description, Boolean isActive, Location location, String score, Member member) {
		super(description, isActive, location);
		this.score = score;
		this.member = member;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
}
