package tribe.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import tribe.controller.dto.SystemCreatedDto;
import tribe.controller.dto.SystemDto;
import tribe.domain.habitTracking.Identity;
import tribe.domain.habitTracking.System;
import tribe.domain.socialNetwork.Member;
import tribe.exception.NoMemberFoundException;
import tribe.repository.IdentityRepo;
import tribe.repository.MemberRepo;
import tribe.repository.SystemRepo;

@Service
public class SystemService {

	protected MemberRepo memberRepo;
	protected SystemRepo systemRepo;
	protected IdentityRepo identityRepo;
	protected SecurityServiceImpl securityService;

	public SystemService(MemberRepo memberRepo, SystemRepo systemRepo,
			IdentityRepo identityRepo,
			SecurityServiceImpl securityService) {
		this.memberRepo = memberRepo;
		this.systemRepo = systemRepo;
		this.identityRepo = identityRepo;
		this.securityService = securityService;
	}
	
	@Transactional
	public SystemCreatedDto createSystem(SystemCreatedDto system) {
		Member member = memberRepo.findByEmail(securityService.getUserEmail())
		.orElseThrow(NoMemberFoundException::new);
		Set<Identity> identities = system.getIdentities().stream().map(id -> identityRepo.getOne(id.getId())).collect(Collectors.toSet());
		System systemSaved = new System(system, member, identities);
		systemSaved = systemRepo.save(systemSaved);
		
		return new SystemCreatedDto(systemSaved, system.getIdentities());
	}

	@Transactional
	public List<SystemDto> getSystemsByConnectedMember() {
		Member member = memberRepo.findByEmail(securityService.getUserEmail())
				.orElseThrow(NoMemberFoundException::new);
		
		return systemRepo.findEagerByMemberId(member.getId()).stream().map(SystemDto::new).collect(Collectors.toList());
	}

}
