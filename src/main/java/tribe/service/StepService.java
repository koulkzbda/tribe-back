package tribe.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import tribe.controller.dto.StepDto;
import tribe.domain.habitTracking.Step;
import tribe.domain.socialNetwork.Member;
import tribe.exception.NoMemberFoundException;
import tribe.repository.MemberRepo;
import tribe.repository.StepRepo;

@Service
public class StepService {

	protected MemberRepo memberRepo;
	protected StepRepo stepRepo;
	protected SecurityServiceImpl securityService;

	public StepService(MemberRepo memberRepo, StepRepo stepRepo, SecurityServiceImpl securityService) {
		this.memberRepo = memberRepo;
		this.stepRepo = stepRepo;
		this.securityService = securityService;
	}

	@Transactional
	public List<StepDto> getStepsByMemberConnected() {
		Member member = memberRepo.findByEmail(securityService.getUserEmail())
		.orElseThrow(NoMemberFoundException::new);
		List<Step> steps = stepRepo.findByMemberId(member.getId());
		
		return steps.stream().map(StepDto::new).collect(Collectors.toList());
	}

}
