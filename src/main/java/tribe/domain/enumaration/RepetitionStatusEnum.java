package tribe.domain.enumaration;

import java.util.HashMap;
import java.util.Map;

public enum RepetitionStatusEnum {
	DONE("DONE"), TO_DO("TO_DO"), CANCELED("CANCELED"), NOT_DONE("NOT_DONE");

	public final String label;

	private RepetitionStatusEnum(String label) {
		this.label = label;
	}

	private static final Map<String, RepetitionStatusEnum> BY_LABEL = new HashMap<>();

	static {
		for (RepetitionStatusEnum rs : values()) {
			BY_LABEL.put(rs.label, rs);
		}
	}

	public static RepetitionStatusEnum valueOfLabel(String label) {
		return BY_LABEL.get(label);
	}
}
