package tribe.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Profile {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected String bio;
    
    @OneToOne
    protected Pictures pictures;
    
    @OneToOne
    protected Member member;

    public Profile() {}
    
	public Profile(String bio, Member member) {
		this.bio = bio;
		this.member = member;
	}

	public Profile(String bio, Pictures pictures, Member member) {
		this.bio = bio;
		this.pictures = pictures;
		this.member = member;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public Member getUser() {
		return member;
	}

	public void setUser(Member member) {
		this.member = member;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pictures getPictures() {
		return pictures;
	}

	public void setPictures(Pictures pictures) {
		this.pictures = pictures;
	}
}
