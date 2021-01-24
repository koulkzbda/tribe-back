package tribe.domain;

import java.time.LocalDateTime;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import tribe.domain.key.LikeKey;

@Entity
@Table(name = "`like`")
public class Like {

	@EmbeddedId
	private LikeKey id = new LikeKey();

	@ManyToOne
	@MapsId(value = "likerId")
	@JoinColumn(name = "liker_id")
	protected Member liker;

	@ManyToOne
	@MapsId(value = "publicationId")
//	@JoinColumn(name = "publication_id")
	protected Publication publication;
	
	@CreationTimestamp
	protected LocalDateTime likedAt;


	public Like() {
	}

	public Like(Member liker, Publication publication, LocalDateTime likedAt) {
		super();
		this.liker = liker;
		this.publication = publication;
		this.likedAt = likedAt;
	}
	
	public Like(Member liker, Publication publication) {
		super();
		this.liker = liker;
		this.publication = publication;
	}

	public LikeKey getId() {
		return id;
	}


	public void setId(LikeKey id) {
		this.id = id;
	}


	public Member getLiker() {
		return liker;
	}


	public void setLiker(Member liker) {
		this.liker = liker;
	}


	public Publication getPublication() {
		return publication;
	}


	public void setPublication(Publication publication) {
		this.publication = publication;
	}

	public LocalDateTime getLikedAt() {
		return likedAt;
	}

	public void setLikedAt(LocalDateTime likedAt) {
		this.likedAt = likedAt;
	}
}
