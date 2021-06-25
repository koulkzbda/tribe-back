package tribe.domain.socialNetwork;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;


@Entity
public class TribeCategory {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	protected String id;
	
	protected String name;
	
    protected String description;
    
    protected Boolean validated;
    
    @CreationTimestamp
	protected LocalDateTime createdAt;
    
    @ManyToMany
    @JoinColumn
    protected List<Tribe> tribes = new ArrayList<>();;

    public TribeCategory() {}

	public TribeCategory(String name, String description, Boolean validated,
			List<Tribe> tribes) {
		this.name = name;
		this.description = description;
		this.validated = validated;
		this.tribes = tribes;
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

	public List<Tribe> getTribes() {
		return tribes;
	}

	public void setTribes(List<Tribe> tribes) {
		this.tribes = tribes;
	}

}
