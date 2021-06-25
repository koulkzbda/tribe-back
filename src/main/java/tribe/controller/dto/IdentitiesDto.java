package tribe.controller.dto;

import java.util.ArrayList;
import java.util.List;

public class IdentitiesDto {
	
	protected List<IdentityDto> identities = new ArrayList<>();
	
	protected String systemId;

	public IdentitiesDto() {
	}

	public IdentitiesDto(List<IdentityDto> identities, String systemId) {
		this.identities = identities;
		this.systemId = systemId;
	}

	public List<IdentityDto> getIdentities() {
		return identities;
	}

	public void setIdentities(List<IdentityDto> identities) {
		this.identities = identities;
	}

}
