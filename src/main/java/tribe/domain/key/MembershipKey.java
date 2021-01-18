package tribe.domain.key;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MembershipKey implements Serializable {
	
	private static final long serialVersionUID = -2518738440955134389L;

	@Column(name = "member_id")
	protected String memberId;

	@Column(name = "tribe_id")
	protected String tribeId;

	public MembershipKey() {
		super();
	}

	public MembershipKey(String memberId, String tribeId) {
		super();
		this.memberId = memberId;
		this.tribeId = tribeId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tribeId == null) ? 0 : tribeId.hashCode());
		result = prime * result + ((memberId == null) ? 0 : memberId.hashCode());
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
		MembershipKey other = (MembershipKey) obj;
		if (tribeId == null) {
			if (other.tribeId != null)
				return false;
		} else if (!tribeId.equals(other.tribeId))
			return false;
		if (memberId == null) {
			if (other.memberId != null)
				return false;
		} else if (!memberId.equals(other.memberId))
			return false;
		return true;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getTribeId() {
		return tribeId;
	}

	public void setTribeId(String tribeId) {
		this.tribeId = tribeId;
	}
}
