package tribe.domain.socialNetwork;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import tribe.domain.enumaration.Role;

@Entity
public class RoleMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Member member;

    @Enumerated(EnumType.STRING)
    private Role role;

    public RoleMember() {
    }

    public RoleMember(Member member, Role role) {
        this.member = member;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Member getUser() {
        return member;
    }

    public void setUser(Member member) {
        this.member = member;
    }
}
