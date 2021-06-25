package tribe.domain.enumaration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tribe.controller.dto.WeekdayEnumDto;

public enum WeekdayEnum {
	MONDAY("MONDAY", "Monday", "Lundi"), TUESDAY("TUESDAY", "Tuesday", "Mardi"),
	WEDNESDAY("WEDNESDAY", "Wednesday", "Mercredi"), THURSDAY("THURSDAY", "Thursday", "Jeudi"),
	FRIDAY("FRIDAY", "Friday", "Vendredi"), SATURDAY("SATURDAY", "Saturday", "Samedi"), SUNDAY("SUNDAY", "Sunday", "Dimanche");

	public final String label;

	public final String en;

	public final String fr;

	private WeekdayEnum(String label, String english, String french) {
		this.label = label;
		this.en = english;
		this.fr = french;
	}

	private static final Map<String, WeekdayEnum> BY_LABEL = new HashMap<>();

	public static final List<WeekdayEnumDto> WEEKDAYS = new ArrayList<>();

	static {
		for (WeekdayEnum wd : values()) {
			BY_LABEL.put(wd.label, wd);
			WEEKDAYS.add(new WeekdayEnumDto(wd));
		}
	}

	public static WeekdayEnum valueOfLabel(String label) {
		return BY_LABEL.get(label);
	}
}
