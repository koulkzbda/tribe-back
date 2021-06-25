package tribe.controller.dto;

import tribe.domain.enumaration.IdentityCategoryEnum;

public class IdentityCategoryEnumDto {
	
	protected String label;
	
	protected String en;
	
	protected String fr;

	public IdentityCategoryEnumDto() {
	}

	public IdentityCategoryEnumDto(String label, String en, String fr) {
		this.label = label;
		this.en = en;
		this.fr = fr;
	}

	public IdentityCategoryEnumDto(IdentityCategoryEnum ic) {
		this.label = ic.label;
		this.en = ic.en;
		this.fr = ic.fr;
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
