package tribe.service;

import org.springframework.stereotype.Service;

import tribe.domain.enumaration.WeekdayEnum;

@Service
public class WeekdayUtil {

	public WeekdayEnum getWeekdayEnum(String dayName) {
		switch (dayName) {
		case "MONDAY":
			return WeekdayEnum.MONDAY;
		case "TUESDAY":
			return WeekdayEnum.TUESDAY;
		case "WEDNESDAY":
			return WeekdayEnum.WEDNESDAY;
		case "THURSDAY":
			return WeekdayEnum.THURSDAY;
		case "FRIDAY":
			return WeekdayEnum.FRIDAY;
		case "SATURDAY":
			return WeekdayEnum.SATURDAY;
		case "SUNDAY":
			return WeekdayEnum.SUNDAY;
		default:
			return null;
		}
	}
	
}