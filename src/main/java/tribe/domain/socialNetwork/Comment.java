package tribe.domain.socialNetwork;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "`comment`")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Comment {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	protected String id;
	
	protected String content;
	
	@CreationTimestamp
	protected LocalDateTime postedAt;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn( name = "member_id")
    protected Member author;
	
    public Comment() {}

	public Comment(String content, Member author) {
		this.content = content;
		this.author = author;
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

	public LocalDateTime getPostedAt() {
		return postedAt;
	}

	public void setPostedAt(LocalDateTime postedAt) {
		this.postedAt = postedAt;
	}

	public Member getAuthor() {
		return author;
	}

	public void setAuthor(Member author) {
		this.author = author;
	}

}
