package tribe.service;

import org.springframework.stereotype.Service;

import tribe.controller.dto.ProfileDto;
import tribe.repository.ProfileRepo;
import tribe.repository.UserRepo;

@Service
public class ProfileService {
	
	protected UserRepo userRepo;
	protected ProfileRepo profileRepo;
	protected SecurityServiceImpl securityService;
	
	public ProfileService(UserRepo userRepo, ProfileRepo profileRepo, SecurityServiceImpl securityService) {
		this.userRepo = userRepo;
		this.profileRepo = profileRepo;
		this.securityService = securityService;
	}
	
	public ProfileDto findByConnectedUser() {
		return new ProfileDto(profileRepo.findByUserId(userRepo.findByEmail(securityService.getUserEmail()).get().getId()).get());
	}

}
