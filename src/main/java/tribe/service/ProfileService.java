package tribe.service;

import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import tribe.controller.dto.MemberProfileDto;
import tribe.domain.socialNetwork.Member;
import tribe.domain.socialNetwork.MemberProfile;
import tribe.domain.socialNetwork.MemberProfilePictures;
import tribe.exception.NoMemberFoundException;
import tribe.exception.NoProfileFoundException;
import tribe.repository.MemberProfileRepo;
import tribe.repository.MemberRepo;

@Service
public class ProfileService {

	protected MemberRepo memberRepo;
	protected MemberProfileRepo memberProfileRepo;
	protected SecurityServiceImpl securityService;

	public ProfileService(MemberRepo memberRepo, MemberProfileRepo memberProfileRepo, SecurityServiceImpl securityService) {
		this.memberRepo = memberRepo;
		this.memberProfileRepo = memberProfileRepo;
		this.securityService = securityService;
	}

	@Transactional
	public MemberProfileDto findByConnectedMember() {
		return new MemberProfileDto(memberProfileRepo
				.findEagerByMemberId(memberRepo.findByEmail(securityService.getUserEmail())
						.orElseThrow(NoMemberFoundException::new)
						.getId())
				.orElseThrow(NoProfileFoundException::new));

	}
	
	@Transactional
	public MemberProfileDto updateBio(MemberProfileDto memberProfileDto) {
		MemberProfile profile = memberProfileRepo.findEagerByMemberId(
				memberRepo.findByEmail(securityService.getUserEmail()
						)
				.orElseThrow(NoMemberFoundException::new)
				.getId())
				.orElseThrow(NoProfileFoundException::new);

		profile.setBio(memberProfileDto.getBio());
		this.memberProfileRepo.save(profile);
		
		return memberProfileDto;
	}
	
	public Member addNewProfile(Member member) {
		
		MemberProfile profile = new MemberProfile(null, null, member);
		MemberProfilePictures profilePictures = new MemberProfilePictures(new HashSet<>(), profile);
		profile.setProfilePictures(profilePictures);
		member.setMemberProfile(profile);
		
		return member;
	}

}
