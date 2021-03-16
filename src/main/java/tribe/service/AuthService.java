package tribe.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import tribe.controller.dto.MemberDto;
import tribe.exception.NoMemberFoundException;
import tribe.repository.MemberProfileRepo;
import tribe.repository.MemberRepo;
import tribe.service.interfaces.SecurityService;

@Service
public class AuthService {

	protected MemberRepo memberRepo;
	protected MemberProfileRepo memberProfileRepo;
	protected SecurityService securityService;

	public AuthService(MemberRepo memberRepo, MemberProfileRepo memberProfileRepo, SecurityService securityService) {
		this.memberRepo = memberRepo;
		this.memberProfileRepo = memberProfileRepo;
		this.securityService = securityService;
	}

	@Transactional
	public MemberDto getMemberWithNewToken() {
		String token = this.securityService.getNewToken();
		
		return new MemberDto(memberRepo.findByEmail(securityService.getUserEmail())
						.orElseThrow(NoMemberFoundException::new), token);

	}
	
}
