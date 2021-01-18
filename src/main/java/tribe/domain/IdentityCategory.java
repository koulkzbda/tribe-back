package tribe.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

import tribe.domain.enumaration.IdentityCategoryEnum;

@Entity
public class IdentityCategory {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	protected String id;

	@OneToMany(mappedBy = "identityCategory")
	protected List<Weighting> weightings = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private IdentityCategoryEnum identityCategory;

    public IdentityCategory() {
    }

	public IdentityCategory(List<Weighting> weightings, IdentityCategoryEnum identityCategory) {
		this.weightings = weightings;
		this.identityCategory = identityCategory;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Weighting> getWeightings() {
		return weightings;
	}

	public void setWeightings(List<Weighting> weightings) {
		this.weightings = weightings;
	}

	public IdentityCategoryEnum getIdentityCategory() {
		return identityCategory;
	}

	public void setIdentityCategory(IdentityCategoryEnum identityCategory) {
		this.identityCategory = identityCategory;
	}
}
