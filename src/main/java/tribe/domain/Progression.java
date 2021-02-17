package tribe.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
public class Progression extends HabitVersion {
	
	protected String versionName;
	
	@ManyToOne(cascade = CascadeType.ALL)
	protected Habit habit;
	
	@OneToOne(cascade = CascadeType.ALL)
    protected HabitContract habitContract;
	
	@ManyToOne(cascade = CascadeType.ALL)
	protected HabitStack habitStack;
	
	@OneToMany(mappedBy = "progression", cascade = CascadeType.ALL)
    protected List<Repetition> repetitions = new ArrayList<>();
	
	@OneToOne(cascade = CascadeType.ALL)
    protected Step preparationHabit;
	
	@OneToOne(cascade = CascadeType.ALL)
    protected Step reward;
	
	@OneToOne(cascade = CascadeType.ALL)
    protected Step conditionningHabit;
	
	@OneToMany(mappedBy = "progression", cascade = CascadeType.ALL)
	protected List<Metric> metrics = new ArrayList<>();
	
    public Progression() {
    	super();
    }
    
    public Progression(String description, Boolean isActive, Location location, String versionName, Habit habit, HabitContract habitContract, HabitStack habitStack,
    		List<Repetition> repetitions, Step preparationHabit, Step reward, Step conditionningHabit) {
    	super(description, isActive, location);
		this.versionName = versionName;
		this.habit = habit;
		this.habitContract = habitContract;
		this.habitStack = habitStack;
		this.repetitions = repetitions;
		this.preparationHabit = preparationHabit;
		this.reward = reward;
		this.conditionningHabit = conditionningHabit;
	}

	public Progression(String description, Boolean isActive, Location location, String versionName, Habit habit, HabitContract habitContract, HabitStack habitStack,
    		Step preparationHabit, Step reward, Step conditionningHabit
    		) {
		super(description, isActive, location);
		this.versionName = versionName;
		this.habit = habit;
		this.habitContract = habitContract;
		this.habitStack = habitStack;
		this.conditionningHabit = conditionningHabit;
		this.preparationHabit = preparationHabit;
		this.reward = reward;
	}

	public Progression(String description, Boolean isActive, Location location, String versionName, Habit habit, HabitContract habitContract, HabitStack habitStack) {
		super(description, isActive, location);
		this.versionName = versionName;
		this.habit = habit;
		this.habitContract = habitContract;
		this.habitStack = habitStack;
	}
	
	public void addRepetition(Repetition repetition) {
		this.repetitions.add(repetition);
	}

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public Habit getHabit() {
		return habit;
	}

	public void setHabit(Habit habit) {
		this.habit = habit;
	}

	public HabitContract getHabitContract() {
		return habitContract;
	}

	public void setHabitContract(HabitContract habitContract) {
		this.habitContract = habitContract;
	}

	public HabitStack getHabitStack() {
		return habitStack;
	}

	public void setHabitStack(HabitStack habitStack) {
		this.habitStack = habitStack;
	}

	public Step getPreparationHabit() {
		return preparationHabit;
	}

	public void setPreparationHabit(Step preparationHabit) {
		this.preparationHabit = preparationHabit;
	}

	public Step getReward() {
		return reward;
	}

	public void setReward(Step reward) {
		this.reward = reward;
	}

	public Step getConditionningHabit() {
		return conditionningHabit;
	}

	public void setConditionningHabit(Step conditionningHabit) {
		this.conditionningHabit = conditionningHabit;
	}

	public List<Repetition> getRepetitions() {
		return repetitions;
	}

	public void setRepetitions(List<Repetition> repetitions) {
		this.repetitions = repetitions;
	}

	public List<Metric> getMetrics() {
		return metrics;
	}

	public void setMetrics(List<Metric> metrics) {
		this.metrics = metrics;
	}
}
