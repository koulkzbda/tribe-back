package tribe.domain.habitTracking;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import tribe.controller.dto.WeightingDto;
import tribe.domain.key.WeightingKey;

@Entity
public class Weighting {

	@EmbeddedId
	private WeightingKey id = new WeightingKey();

	@ManyToOne(cascade = CascadeType.ALL)
	@NotFound(action = NotFoundAction.IGNORE)
	@MapsId(value = "identityId")
	protected Identity identity;

	@ManyToOne(cascade = CascadeType.ALL)
	@NotFound(action = NotFoundAction.IGNORE)
	@MapsId(value = "indentityCategoryId")
	protected IdentityCategory identityCategory;
	
	protected BigDecimal weight;


	public Weighting() {
	}

	public Weighting(WeightingDto weighting) {
		this.identityCategory = new IdentityCategory(weighting.getIdentityCategory());
		this.weight = weighting.getWeight();
		this.identityCategory.addWeighting(this);
	}
	
	public Weighting(Identity identity, IdentityCategory identityCategory, BigDecimal weight) {
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


	public BigDecimal getWeight() {
		return weight;
	}


	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

}
