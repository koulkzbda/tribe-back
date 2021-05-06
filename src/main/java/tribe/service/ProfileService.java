package tribe.service;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import tribe.controller.dto.ProfileDto;
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
	public ProfileDto findByConnectedMember() {
		return new ProfileDto(memberProfileRepo
				.findEagerByMemberId(memberRepo.findByEmail(securityService.getUserEmail())
						.orElseThrow(NoMemberFoundException::new)
						.getId())
				.orElseThrow(NoProfileFoundException::new));

	}
	
	@Transactional
	public ProfileDto updateBio(ProfileDto profileDto) {
		MemberProfile profile = memberProfileRepo.findEagerByMemberId(
				memberRepo.findByEmail(securityService.getUserEmail()
						)
				.orElseThrow(NoMemberFoundException::new)
				.getId())
				.orElseThrow(NoProfileFoundException::new);

		profile.setBio(profileDto.getBio());
		this.memberProfileRepo.save(profile);
		
		return profileDto;
	}
	
	public Member addNewProfile(Member member) {
		
		MemberProfile profile = new MemberProfile(null, null, member);
		MemberProfilePictures profilePictures = new MemberProfilePictures(new ArrayList<>(), profile);
		profile.setProfilePictures(profilePictures);
		member.setMemberProfile(profile);
		
		return member;
	}

}
