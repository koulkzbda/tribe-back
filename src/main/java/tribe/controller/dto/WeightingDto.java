package tribe.controller.dto;

import java.math.BigDecimal;

import tribe.domain.habitTracking.Weighting;
import tribe.domain.key.WeightingKey;

public class WeightingDto {
	
	protected WeightingKey id;
	
	protected BigDecimal weight;
	
	protected IdentityCategoryEnumDto identityCategory;
	
	public WeightingDto() {
	}
	
	public WeightingDto(BigDecimal weight, IdentityCategoryEnumDto identityCategory) {
		this.weight = weight;
		this.identityCategory = identityCategory;
	}

	public WeightingDto(Weighting weighting) {
		id = weighting.getId();
		weight = weighting.getWeight();
		identityCategory = new IdentityCategoryEnumDto(weighting.getIdentityCategory().getIdentityCategory());
	}

	public WeightingKey getId() {
		return id;
	}

	public void setId(WeightingKey id) {
		this.id = id;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public IdentityCategoryEnumDto getIdentityCategory() {
		return identityCategory;
	}

	public void setIdentityCategory(IdentityCategoryEnumDto identityCategory) {
		this.identityCategory = identityCategory;
	}

}
