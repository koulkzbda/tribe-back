package tribe.domain.socialNetwork;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class CommentOfPublication extends Comment {

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "commentOfPublication")
	protected List<CommentOfComment> commentsOfComment = new ArrayList<>();

	@ManyToOne(cascade = CascadeType.ALL)
	protected Publication publication;

	public CommentOfPublication() {
		super();
	}

	public CommentOfPublication(String content, Member author, List<CommentOfComment> commentsOfComment, Publication publication) {
		super(content, author);
		this.commentsOfComment = commentsOfComment;
		this.publication = publication;
	}

	public List<CommentOfComment> getCommentsOfComment() {
		return commentsOfComment;
	}

	public void setCommentsOfComment(List<CommentOfComment> commentsOfComment) {
		this.commentsOfComment = commentsOfComment;
	}

	public Publication getPublication() {
		return publication;
	}

	public void setPublication(Publication publication) {
		this.publication = publication;
	}
}
