package tribe.domain.enumaration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tribe.controller.dto.IdentityCategoryEnumDto;

public enum IdentityCategoryEnum {
	ATHLETIC("ATHLETIC", "Athletic", "Sportif"), PRODUCTIVE("PRODUCTIVE", "Productive", "Productif"), RELAX("RELAX", "Relax", "Relax"),
	LONGLIFE_LEARNER("LONGLIFE_LEARNER", "Longlife Learner", "Apprenant à vie"), LEADER("LEADER", "Leader", "Leader"),
	CRAFTER("CRAFTER", "Crafter", "Créateur"),	GOOD_PARTNER("GOOD_PARTNER", "Good Partner", "Bon Partenaire"),
	ATTRACTIVE("ATTRACTIVE", "Attractive", "Attirant"),	SPIRITUAL("SPIRITUAL", "Spriritual", "Spirituel"),
	ECO_RESPONSIBLE("ECO_RESPONSIBLE", "Eco Responsible", "Éco Responsable"), MINIMALIST("MINIMALIST", "Minimalist", "Minimaliste"),
	FINANCIALLY_INTELLIGENT("FINANCIALLY_INTELLIGENT", "Financially Intelligent", "Intelligent Financièrement");
	
	public final String label;
	
	public final String en;
	
	public final String fr;

	private IdentityCategoryEnum(String label, String english, String french) {
		this.label = label;
		this.en = english;
		this.fr = french;
	}
	
	private static final Map<String, IdentityCategoryEnum> BY_LABEL = new HashMap<>();
	
	public static final List<IdentityCategoryEnumDto> IDENTITY_CATEGORIES = new ArrayList<>();

	static {
		for (IdentityCategoryEnum ic : values()) {
			BY_LABEL.put(ic.label, ic);
			IDENTITY_CATEGORIES.add(new IdentityCategoryEnumDto(ic));
		}
	}

	public static IdentityCategoryEnum valueOfLabel(String label) {
		return BY_LABEL.get(label);
	}
}