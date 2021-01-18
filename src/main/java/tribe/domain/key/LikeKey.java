package tribe.domain.key;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class LikeKey implements Serializable {

	private static final long serialVersionUID = -5339646861837746286L;
	
	@Column(name = "liker_id")
	protected String likerId;

	@Column(name = "publication_id")
	protected String publicationId;

	public LikeKey() {
		super();
	}

	public LikeKey(String likerId, String publicationId) {
		super();
		this.likerId = likerId;
		this.publicationId = publicationId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((publicationId == null) ? 0 : publicationId.hashCode());
		result = prime * result + ((likerId == null) ? 0 : likerId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LikeKey other = (LikeKey) obj;
		if (publicationId == null) {
			if (other.publicationId != null)
				return false;
		} else if (!publicationId.equals(other.publicationId))
			return false;
		if (likerId == null) {
			if (other.likerId != null)
				return false;
		} else if (!likerId.equals(other.likerId))
			return false;
		return true;
	}

	public String getLikerId() {
		return likerId;
	}

	public void setLikerId(String likerId) {
		this.likerId = likerId;
	}

	public String getPublicationId() {
		return publicationId;
	}

	public void setPublicationId(String publicationId) {
		this.publicationId = publicationId;
	}

}
