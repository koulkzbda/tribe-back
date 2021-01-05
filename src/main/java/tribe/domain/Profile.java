package tribe.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Profile {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected String bio;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    protected ProfilePictures profilePictures;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn( name = "member_id")
    protected Member member;

    public Profile() {}
    
	public Profile(String bio, Member member) {
		this.bio = bio;
		this.member = member;
	}

	public Profile(String bio, ProfilePictures profilePictures, Member member) {
		this.bio = bio;
		this.profilePictures = profilePictures;
		this.member = member;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ProfilePictures getProfilePictures() {
		return profilePictures;
	}

	public void setProfilePictures(ProfilePictures profilePictures) {
		this.profilePictures = profilePictures;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
}
