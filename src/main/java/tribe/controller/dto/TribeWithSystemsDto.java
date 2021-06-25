package tribe.controller.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tribe.domain.socialNetwork.Tribe;

public class TribeWithSystemsDto {

	protected String id;

	protected String name;

	protected String description;

	protected Boolean validated;

	protected TribeProfileDto profile;

	protected List<SystemDto> systems = new ArrayList<>();

	public TribeWithSystemsDto() {
	}

	public TribeWithSystemsDto(Tribe tribe) {
		id = tribe.getId();
		name = tribe.getName();
		description = tribe.getDescription();
		validated = tribe.getValidated();
		profile = new TribeProfileDto(tribe.getTribeProfile());
		if (tribe.getSystems().size() > 0) {
			systems = tribe.getSystems().stream().map(SystemDto::new).collect(Collectors.toList());
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getValidated() {
		return validated;
	}

	public void setValidated(Boolean validated) {
		this.validated = validated;
	}

	public TribeProfileDto getProfile() {
		return profile;
	}

	public void setProfile(TribeProfileDto profile) {
		this.profile = profile;
	}

	public List<SystemDto> getSystems() {
		return systems;
	}

	public void setSystems(List<SystemDto> systems) {
		this.systems = systems;
	}

}
