package tribe.domain.key;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class WeightingKey implements Serializable {
	
	private static final long serialVersionUID = 6409387874688927390L;

	protected String identityId;

	protected String indentityCategoryId;

	public WeightingKey() {
		super();
	}

	public WeightingKey(String identityId, String indentityCategoryId) {
		super();
		this.identityId = identityId;
		this.indentityCategoryId = indentityCategoryId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((identityId == null) ? 0 : identityId.hashCode());
		result = prime * result + ((indentityCategoryId == null) ? 0 : indentityCategoryId.hashCode());
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
		WeightingKey other = (WeightingKey) obj;
		if (identityId == null) {
			if (other.identityId != null)
				return false;
		} else if (!identityId.equals(other.identityId))
			return false;
		if (indentityCategoryId == null) {
			if (other.indentityCategoryId != null)
				return false;
		} else if (!indentityCategoryId.equals(other.indentityCategoryId))
			return false;
		return true;
	}

	public String getIdentityId() {
		return identityId;
	}

	public void setIdentityId(String identityId) {
		this.identityId = identityId;
	}

	public String getIndentityCategoryId() {
		return indentityCategoryId;
	}

	public void setIndentityCategoryId(String indentityCategoryId) {
		this.indentityCategoryId = indentityCategoryId;
	}
}
