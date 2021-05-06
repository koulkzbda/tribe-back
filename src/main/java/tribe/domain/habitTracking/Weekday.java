package tribe.domain.habitTracking;

import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import tribe.domain.enumaration.WeekdayEnum;

@Entity
public class Weekday {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	protected String id;

	@ManyToOne(cascade = CascadeType.ALL)
	protected HabitStack habitStack;

    @Enumerated(EnumType.STRING)
    private WeekdayEnum weekday;
    
    protected LocalTime time;

    public Weekday() {
    }

	public Weekday(HabitStack habitStack, WeekdayEnum weekday, LocalTime time) {
		this.habitStack = habitStack;
		this.weekday = weekday;
		this.time = time;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public HabitStack getHabitStack() {
		return habitStack;
	}

	public void setHabitStack(HabitStack habitStack) {
		this.habitStack = habitStack;
	}

	public WeekdayEnum getWeekday() {
		return weekday;
	}

	public void setWeekday(WeekdayEnum weekday) {
		this.weekday = weekday;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}
}
