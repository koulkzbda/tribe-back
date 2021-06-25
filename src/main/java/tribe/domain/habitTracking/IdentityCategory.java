package tribe.domain.habitTracking;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import tribe.controller.dto.IdentityCategoryEnumDto;
import tribe.domain.enumaration.IdentityCategoryEnum;

@Entity
public class IdentityCategory {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	protected String id;

	@OneToMany(mappedBy = "identityCategory")
	@NotFound(action = NotFoundAction.IGNORE)
	protected Set<Weighting> weightings = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private IdentityCategoryEnum identityCategory;

    public IdentityCategory() {
    }
    
    public IdentityCategory(IdentityCategoryEnumDto ic) {
    	identityCategory = IdentityCategoryEnum.valueOfLabel(ic.getLabel());
    }

	public IdentityCategory(Set<Weighting> weightings, IdentityCategoryEnum identityCategory) {
		this.weightings = weightings;
		this.identityCategory = identityCategory;
	}
	
	public void addWeighting(Weighting w) {
		weightings.add(w);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Set<Weighting> getWeightings() {
		return weightings;
	}

	public void setWeightings(Set<Weighting> weightings) {
		this.weightings = weightings;
	}

	public IdentityCategoryEnum getIdentityCategory() {
		return identityCategory;
	}

	public void setIdentityCategory(IdentityCategoryEnum identityCategory) {
		this.identityCategory = identityCategory;
	}
}
