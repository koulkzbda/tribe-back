package tribe.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import tribe.controller.dto.IdentityDto;
import tribe.controller.dto.WeightingDto;
import tribe.domain.habitTracking.Identity;
import tribe.domain.habitTracking.Weighting;
import tribe.domain.key.WeightingKey;
import tribe.domain.socialNetwork.Member;
import tribe.exception.NoMemberFoundException;
import tribe.repository.IdentityRepo;
import tribe.repository.MemberRepo;
import tribe.repository.WeightingRepo;

@Service
public class IdentityService {

	protected MemberRepo memberRepo;
	protected IdentityRepo identityRepo;
	protected WeightingRepo weightingRepo;
	protected SecurityServiceImpl securityService;

	public IdentityService(MemberRepo memberRepo, IdentityRepo identityRepo,
			WeightingRepo weightingRepo, SecurityServiceImpl securityService) {
		this.memberRepo = memberRepo;
		this.identityRepo = identityRepo;
		this.weightingRepo = weightingRepo;
		this.securityService = securityService;
	}

	@Transactional
	public List<IdentityDto> findByConnectedMember() {
		String memberId = memberRepo.findByEmail(securityService.getUserEmail())
				.orElseThrow(NoMemberFoundException::new)
				.getId();
		List<Identity> identities = identityRepo.findEagerByMemberId(memberId);
		
		return identities.stream().map(IdentityDto::new).collect(Collectors.toList());
	}
	
	@Transactional
	public List<IdentityDto> createIdentities(List<IdentityDto> identityDtos) {
		Member member = memberRepo.findByEmail(securityService.getUserEmail())
		.orElseThrow(NoMemberFoundException::new);
		List<Identity> identities = identityDtos.stream().peek(dto -> dto.setVotes(0)).map(Identity::new).peek(i -> i.setMember(member)).collect(Collectors.toList());
		member.setIdentities(identities);
		memberRepo.save(member);
		
		return identities.stream().map(IdentityDto::new).collect(Collectors.toList());
	}
	
	@Transactional
	public Identity updateIdentity(Identity identity, IdentityDto identityDto) {
		Set<WeightingKey> weightingKeysDto = identityDto.getWeightings().stream().map(WeightingDto::getId).collect(Collectors.toSet());
		Set<WeightingKey> weightingKeysDeleted = identity.getWeightings().stream().map(Weighting::getId).collect(Collectors.toSet());
		if (weightingKeysDeleted.removeAll(weightingKeysDto)) {
			weightingKeysDeleted.stream().forEach(id -> {
				weightingRepo.deleteById(id);
			});
		}
		
		Set<Weighting> weightings = new HashSet<>();
		if (identity.getWeightings().size() > 0) {
			weightings.addAll(identity.getWeightings().stream().map(w -> updateWeightingWeight(w, identityDto.getWeightings())).collect(Collectors.toSet()));
		}
		identity.setWeightings(weightings);
		
		return identity;
	}
	
	private Weighting updateWeightingWeight(Weighting w, List<WeightingDto> weightings) {
		WeightingDto weightingDto = weightings.stream().filter(weighting -> weighting.getId().equals(w.getId())).findAny().get();
		w.setWeight(weightingDto.getWeight());
		
		return w;
	}
	
}
