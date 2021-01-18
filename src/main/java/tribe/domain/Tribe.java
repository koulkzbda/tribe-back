package tribe.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;


@Entity
public class Tribe {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	protected String id;
	
	protected String name;
	
    protected String description;
    
    protected Boolean validated;
    
    @CreationTimestamp
	protected LocalDateTime createdAt;
    
    @OneToOne(cascade = CascadeType.ALL)
    protected TribeProfile tribeProfile;
    
    @OneToOne(cascade = CascadeType.ALL)
    protected TribeWall tribeWall;
    
    @OneToMany(mappedBy = "tribe", cascade = CascadeType.ALL)
	protected List<Membership> memberships = new ArrayList<>();
    
    @ManyToMany(mappedBy = "tribes", cascade = CascadeType.ALL)
    protected List<TribeCategory> tribeCategory = new ArrayList<>();

    public Tribe() {}

	public Tribe(String name, String description, Boolean validated, TribeProfile tribeProfile,
			TribeWall tribeWall, List<TribeCategory> tribeCategory) {
		this.name = name;
		this.description = description;
		this.validated = validated;
		this.tribeProfile = tribeProfile;
		this.tribeWall = tribeWall;
		this.tribeCategory = tribeCategory;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getValidated() {
		return validated;
	}

	public void setValidated(Boolean validated) {
		this.validated = validated;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public TribeProfile getTribeProfile() {
		return tribeProfile;
	}

	public void setTribeProfile(TribeProfile tribeProfile) {
		this.tribeProfile = tribeProfile;
	}

	public TribeWall getTribeWall() {
		return tribeWall;
	}

	public void setTribeWall(TribeWall tribeWall) {
		this.tribeWall = tribeWall;
	}

	public List<TribeCategory> getTribeCategory() {
		return tribeCategory;
	}

	public void setTribeCategory(List<TribeCategory> tribeCategory) {
		this.tribeCategory = tribeCategory;
	}

	public List<Membership> getMemberships() {
		return memberships;
	}

	public void setMemberships(List<Membership> memberships) {
		this.memberships = memberships;
	}
}
