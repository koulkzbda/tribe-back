package tribe.domain.habitTracking;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import tribe.domain.key.WeightingKey;

@Entity
public class Weighting {

	@EmbeddedId
	private WeightingKey id = new WeightingKey();

	@ManyToOne
	@MapsId(value = "identityId")
	protected Identity identity;

	@ManyToOne
	@MapsId(value = "indentityCategoryId")
	protected IdentityCategory identityCategory;
	
	protected Double weight;


	public Weighting() {
	}


	public Weighting(Identity identity, IdentityCategory identityCategory, Double weight) {
		this.identity = identity;
		this.identityCategory = identityCategory;
		this.weight = weight;
	}


	public WeightingKey getId() {
		return id;
	}


	public void setId(WeightingKey id) {
		this.id = id;
	}


	public Identity getIdentity() {
		return identity;
	}


	public void setIdentity(Identity identity) {
		this.identity = identity;
	}


	public IdentityCategory getIdentityCategory() {
		return identityCategory;
	}


	public void setIdentityCategory(IdentityCategory identityCategory) {
		this.identityCategory = identityCategory;
	}


	public Double getWeight() {
		return weight;
	}


	public void setWeight(Double weight) {
		this.weight = weight;
	}

}
