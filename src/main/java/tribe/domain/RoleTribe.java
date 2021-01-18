package tribe.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import tribe.domain.enumaration.RoleTribeEnum;

@Entity
public class RoleTribe {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	protected String id;

    @Enumerated(EnumType.STRING)
    private RoleTribeEnum roleTribe;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private Membership membership;
    
    public RoleTribe() {
    }
	public RoleTribe(RoleTribeEnum roleTribe, Membership membership) {
		this.roleTribe = roleTribe;
		this.membership = membership;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Membership getMembership() {
		return membership;
	}
	public void setMembership(Membership membership) {
		this.membership = membership;
	}
	public RoleTribeEnum getRoleTribe() {
		return roleTribe;
	}

	public void setRoleTribe(RoleTribeEnum roleTribe) {
		this.roleTribe = roleTribe;
	}
}
