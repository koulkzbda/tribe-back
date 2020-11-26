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
    protected User user;

    public Profile() {}
    
	public Profile(String bio, User user) {
		this.bio = bio;
		this.user = user;
	}

	public Profile(String bio, Pictures pictures, User user) {
		this.bio = bio;
		this.pictures = pictures;
		this.user = user;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
