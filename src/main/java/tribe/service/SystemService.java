package tribe.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import tribe.repository.MemberRepo;
import tribe.repository.SystemRepo;

@Service
public class SystemService {

	protected MemberRepo memberRepo;
	protected SystemRepo systemRepo;
	protected SecurityServiceImpl securityService;

	public SystemService(MemberRepo memberRepo, SystemRepo systemRepo, SecurityServiceImpl securityService) {
		this.memberRepo = memberRepo;
		this.systemRepo = systemRepo;
		this.securityService = securityService;
	}

//	public List<tribe.domain.System> findSystemByConnectedMember() {
//		LocalDateTime today = LocalDate.now().atTime(0, 0);
//		
//		return systemRepo
//				.findWithHabitStacksByMemberId(
//						memberRepo.findByEmail(securityService.getUserEmail()).get().getId(),
//						);
//
//	}

}
