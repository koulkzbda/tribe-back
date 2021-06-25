package tribe.controller.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import tribe.domain.habitTracking.Weekday;

public class WeekdayDto {
	
	protected WeekdayEnumDto weekday;
    
    protected LocalDateTime time;

	public WeekdayDto() {
	}
	
	public WeekdayDto(Weekday wd) {
		time = LocalDateTime.of(LocalDate.now(), wd.getTime());
		weekday = new WeekdayEnumDto(wd.getWeekday());
	}

	public WeekdayEnumDto getWeekday() {
		return weekday;
	}

	public void setWeekday(WeekdayEnumDto weekday) {
		this.weekday = weekday;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

}
