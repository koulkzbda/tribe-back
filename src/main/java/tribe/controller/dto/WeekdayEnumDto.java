package tribe.controller.dto;

import tribe.domain.enumaration.WeekdayEnum;

public class WeekdayEnumDto {
	
	protected String label;
	
	protected String en;
	
	protected String fr;

	public WeekdayEnumDto() {
	}

	public WeekdayEnumDto(String label, String en, String fr) {
		this.label = label;
		this.en = en;
		this.fr = fr;
	}

	public WeekdayEnumDto(WeekdayEnum wd) {
		this.label = wd.label;
		this.en = wd.en;
		this.fr = wd.fr;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getEn() {
		return en;
	}

	public void setEn(String en) {
		this.en = en;
	}

	public String getFr() {
		return fr;
	}

	public void setFr(String fr) {
		this.fr = fr;
	}

}
