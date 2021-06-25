package tribe.controller.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tribe.domain.habitTracking.Identity;

public class IdentityDto {
	
	protected String id;
	
	protected String name;
	
	protected Integer votes;
	
	protected List<WeightingDto> weightings = new ArrayList<>();

	public IdentityDto() {
	}
	
	public IdentityDto(String id, String name, Integer votes, List<WeightingDto> weightings) {
		this.id = id;
		this.name = name;
		this.votes = votes;
		this.weightings = weightings;
	}

	public IdentityDto(String name, Integer votes, List<WeightingDto> weightings) {
		this.name = name;
		this.votes = votes;
		this.weightings = weightings;
	}

	public IdentityDto(Identity identity) {
		id = identity.getId();
		name = identity.getName();
		votes = identity.getVotes();
		weightings = identity.getWeightings().stream().map(WeightingDto::new).collect(Collectors.toList());
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

	public Integer getVotes() {
		return votes;
	}

	public void setVotes(Integer votes) {
		this.votes = votes;
	}

	public List<WeightingDto> getWeightings() {
		return weightings;
	}

	public void setWeightings(List<WeightingDto> weightings) {
		this.weightings = weightings;
	}

}
