package tribe.domain.socialNetwork;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Entity
public class CommentOfComment extends Comment {
	
	@ManyToOne(cascade = CascadeType.ALL)
	protected CommentOfPublication commentOfPublication;
	
    public CommentOfComment() {
    	super();
    }

	public CommentOfComment(String content, Member author, CommentOfPublication commentOfPublication) {
		super(content, author);
		this.commentOfPublication = commentOfPublication;
	}

	public CommentOfPublication getCommentOfPublication() {
		return commentOfPublication;
	}

	public void setCommentOfPublication(CommentOfPublication commentOfPublication) {
		this.commentOfPublication = commentOfPublication;
	}
}
