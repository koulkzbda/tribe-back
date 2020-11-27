package tribe.service;

import org.springframework.stereotype.Service;

import tribe.controller.dto.ProfileDto;
import tribe.repository.ProfileRepo;
import tribe.repository.MemberRepo;

@Service
public class ProfileService {
	
	protected MemberRepo memberRepo;
	protected ProfileRepo profileRepo;
	protected SecurityServiceImpl securityService;
	
	public ProfileService(MemberRepo memberRepo, ProfileRepo profileRepo, SecurityServiceImpl securityService) {
		this.memberRepo = memberRepo;
		this.profileRepo = profileRepo;
		this.securityService = securityService;
	}
	
	public ProfileDto findByConnectedMember() {
		return new ProfileDto(profileRepo.findByMemberId(memberRepo.findByEmail(securityService.getUserEmail()).get().getId()).get());
	}

}
