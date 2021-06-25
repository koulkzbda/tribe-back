package tribe.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import tribe.controller.dto.TribeWithSystemsDto;
import tribe.domain.socialNetwork.Member;
import tribe.domain.socialNetwork.Tribe;
import tribe.exception.NoMemberFoundException;
import tribe.repository.MemberRepo;
import tribe.repository.TribeRepo;

@Service
public class TribeService {

	protected MemberRepo memberRepo;
	protected TribeRepo tribeRepo;
	protected SecurityServiceImpl securityService;

	public TribeService(MemberRepo memberRepo, TribeRepo tribeRepo, SecurityServiceImpl securityService) {
		this.memberRepo = memberRepo;
		this.tribeRepo = tribeRepo;
		this.securityService = securityService;
	}

	@Transactional
	public List<TribeWithSystemsDto> getTribesWithSystems() {
		Member member = memberRepo.findByEmail(securityService.getUserEmail())
		.orElseThrow(NoMemberFoundException::new);
		List<Tribe> tribes = tribeRepo.findEagerByMemberId(member.getId());
		
		return tribes.stream().map(TribeWithSystemsDto::new).collect(Collectors.toList());
	}

}
