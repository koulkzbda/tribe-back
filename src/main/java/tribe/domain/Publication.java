package tribe.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Publication {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	protected String id;
	
    protected String content;
    
    @CreationTimestamp
	protected LocalDateTime postedAt;
    
    @ManyToOne(cascade = CascadeType.ALL)
    protected MemberWall memberWall;
    
    @ManyToOne(cascade = CascadeType.ALL)
    protected TribeWall tribeWall;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    protected PublicationPictures publicationPictures;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn( name = "member_id")
    protected Member author;
    
    @OneToMany(mappedBy = "publication")
	protected List<Like> likes = new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "publication")
	protected List<CommentOfPublication> commentsOfPublication = new ArrayList<>();
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
	protected ReactionNotification reactionNotification;

    public Publication() {}
    
    public Publication(String content, PublicationPictures publicationPictures, Member author,
    		List<Like> likes, List<CommentOfPublication> commentsOfPublication, ReactionNotification reactionNotification) {
    	this.content = content;
    	this.publicationPictures = publicationPictures;
    	this.author = author;
    	this.likes = likes;
    	this.commentsOfPublication = commentsOfPublication;
    	this.reactionNotification = reactionNotification;
    }
    
    public Publication(String content, PublicationPictures publicationPictures, Member author,
			List<Like> likes, List<CommentOfPublication> commentsOfPublication) {
		this.content = content;
		this.publicationPictures = publicationPictures;
		this.author = author;
		this.likes = likes;
		this.commentsOfPublication = commentsOfPublication;
	}

	public Publication(String content, PublicationPictures publicationPictures, Member author,
			List<Like> likes) {
		this.content = content;
		this.publicationPictures = publicationPictures;
		this.author = author;
		this.likes = likes;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public PublicationPictures getPublicationPictures() {
		return publicationPictures;
	}

	public void setPublicationPictures(PublicationPictures publicationPictures) {
		this.publicationPictures = publicationPictures;
	}

	public Member getAuthor() {
		return author;
	}

	public void setAuthor(Member author) {
		this.author = author;
	}

	public List<Like> getLikes() {
		return likes;
	}

	public void setLikes(List<Like> likes) {
		this.likes = likes;
	}

	public LocalDateTime getPostedAt() {
		return postedAt;
	}

	public void setPostedAt(LocalDateTime postedAt) {
		this.postedAt = postedAt;
	}

	public List<CommentOfPublication> getCommentsOfPublication() {
		return commentsOfPublication;
	}

	public void setCommentsOfPublication(List<CommentOfPublication> commentsOfPublication) {
		this.commentsOfPublication = commentsOfPublication;
	}

	public ReactionNotification getReactionNotification() {
		return reactionNotification;
	}

	public void setReactionNotification(ReactionNotification reactionNotification) {
		this.reactionNotification = reactionNotification;
	}

	public MemberWall getMemberWall() {
		return memberWall;
	}

	public void setMemberWall(MemberWall memberWall) {
		this.memberWall = memberWall;
	}

	public TribeWall getTribeWall() {
		return tribeWall;
	}

	public void setTribeWall(TribeWall tribeWall) {
		this.tribeWall = tribeWall;
	}
}
