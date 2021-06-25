package tribe.domain.habitTracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import tribe.controller.dto.ProgressionWithMetricsDto;
import tribe.domain.socialNetwork.Location;
import tribe.domain.socialNetwork.Member;


@Entity
public class Progression extends HabitVersion implements Comparable<Progression> {
	
	protected String versionName;
	
	@Column(name = "`execution_order`")
	protected Integer executionOrder;
	
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
    protected Step conditioningStep;
	
	@OneToMany(mappedBy = "progression", cascade = CascadeType.ALL)
	protected Set<Metric> metrics = new HashSet<>();
	
    public Progression() {
    	super();
    }
    
    public Progression(ProgressionWithMetricsDto p) {
    	super();
    	executionOrder = p.getExecutionOrder();
    	isActive = true;
    	location = new Location(p.getLocation());
    	versionName = p.getVersionName();
    	if (p.getHabitId() == null) {
    		habit = new Habit(p);
    		habit.addProgression(this);
    		
    	}
    	if (p.getPreparationHabit() != null) {
    		preparationHabit = new Step(p.getPreparationHabit());
    	}
    	if (p.getReward() != null) {
    		reward = new Step(p.getReward());
    	}
    	if (p.getConditioningStep() != null) {
    		conditioningStep = new Step(p.getConditioningStep());
    	}
    	java.lang.System.out.println(p.getMetrics().size());
    	if (p.getMetrics().size() > 0) {
    		metrics = p.getMetrics().stream().map(Metric::new).peek(m -> m.setProgression(this)).collect(Collectors.toSet());
    	}
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
		this.conditioningStep = conditionningHabit;
	}

	public Progression(String description, Boolean isActive, Location location, String versionName, Habit habit, HabitContract habitContract, HabitStack habitStack,
    		Step preparationHabit, Step reward, Step conditionningHabit
    		) {
		super(description, isActive, location);
		this.versionName = versionName;
		this.habit = habit;
		this.habitContract = habitContract;
		this.habitStack = habitStack;
		this.conditioningStep = conditionningHabit;
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
	
	public void setMemberForSteps(Member member) {
		if (preparationHabit != null) {
			preparationHabit.setMember(member);
		}
		if (reward != null) {
			reward.setMember(member);
		}
		if (conditioningStep != null) {
			conditioningStep.setMember(member);
		}
	}
	
	public void addRepetition(Repetition repetition) {
		this.repetitions.add(repetition);
	}
	
	public void deleteRepetition(Repetition repetition) {
		Integer repetitionIndex = repetitions.indexOf(repetition);
		repetition.setProgression(null);
		repetitions.set(repetitionIndex, repetition);
	}
	
	@Override
	public int compareTo(Progression other) {
		if ((this == null || this.getExecutionOrder() == null ) && ( other == null || other.getExecutionOrder() == null ))
			return 0;
		if (this == null || this.getExecutionOrder() == null)
			return -1;
		if (other == null || other.getExecutionOrder() == null)
			return 1;

		return this.getExecutionOrder() - other.getExecutionOrder();
	}

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public Integer getExecutionOrder() {
		return executionOrder;
	}

	public void setExecutionOrder(Integer executionOrder) {
		this.executionOrder = executionOrder;
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

	public Step getConditioningStep() {
		return conditioningStep;
	}

	public void setConditioningStep(Step conditioningStep) {
		this.conditioningStep = conditioningStep;
	}

	public List<Repetition> getRepetitions() {
		return repetitions;
	}

	public void setRepetitions(List<Repetition> repetitions) {
		this.repetitions = repetitions;
	}

	public Set<Metric> getMetrics() {
		return metrics;
	}

	public void setMetrics(Set<Metric> metrics) {
		this.metrics = metrics;
	}
}
