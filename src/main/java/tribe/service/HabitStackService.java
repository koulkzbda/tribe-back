package tribe.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import tribe.controller.dto.FeedbuzzDto;
import tribe.domain.System;
import tribe.repository.HabitStackRepo;
import tribe.repository.MemberRepo;

@Service
public class HabitStackService {

	protected MemberRepo memberRepo;
	protected HabitStackRepo habitStackRepo;
	protected SystemService systemService;
	protected SecurityServiceImpl securityService;

	public HabitStackService(MemberRepo memberRepo, HabitStackRepo habitStackRepo, SystemService systemService,
			SecurityServiceImpl securityService) {
		this.memberRepo = memberRepo;
		this.habitStackRepo = habitStackRepo;
		this.systemService = systemService;
		this.securityService = securityService;
	}

	// TO DO Create HabitStackDto
	public List<FeedbuzzDto> findByConnectedMember() {
		List<FeedbuzzDto> habitStacksDto = new ArrayList<>();
		
		List<System> systems = systemService.findSystemByConnectedMember();
		
		systems.forEach(system -> {
			java.lang.System.out.println(system);
			habitStacksDto.addAll(system.getHabitStacks().stream().map(FeedbuzzDto::new).collect(Collectors.toList()));
		});
		
		return habitStacksDto;
	}

//	@Transactional
//	public List<PictureDto> addProfilePictures(MultipartFile[] files, String profileId, String profilePictureName)
//			throws IOException, NoSuchElementException {
//
//		MemberProfilePictures pictures = this.memberProfilePicturesRepo.findByMemberProfileId(profileId).get();
//		MemberProfile profile = this.memberProfileRepo.findById(profileId).get();
//		List<Picture> picturesList = new ArrayList<Picture>();
//
//		for (int i = 0; i < files.length; i++) {
//			String fileName = StringUtils.cleanPath(files[i].getOriginalFilename());
//			Picture picture = new Picture(fileName, files[i].getContentType(), files[i].getBytes());
//			picture.setPictures(pictures);
//			if (fileName.equals(profilePictureName)) {
//				profile.getProfilePictures().getPictures().stream().forEach(pict -> pict.setIsHeadlinePicture(false));
//				picture.setIsHeadlinePicture(true);
//			}
//			picturesList.add(picture);
//		}
//
//		pictures.addPictures(picturesList);
//		profile.setProfilePictures(pictures);
//
//		this.memberProfileRepo.save(profile);
//		
//		return getProfilePictures();
//
//	}
//
//	@Transactional
//	public List<PictureDto> setProfilePicture(PictureDto pictureDto) throws InvalidPictureException {
//		MemberProfile profile = memberProfileRepo.findEagerByMemberId(
//				memberRepo.findByEmail(securityService.getUserEmail()
//						)
//				.get().getId()).get();
//		MemberProfilePictures memberProfilePictures = profile.getProfilePictures();
//		List<Picture> pictures = memberProfilePictures.getPictures();
//		
//		if ( pictures.stream().anyMatch(pict -> pict.getId().equals(pictureDto.getId())) ) {
//			pictures.stream().forEach(pict -> {
//				
//				pict.setIsHeadlinePicture(false);
//				if (pict.getId().equals(pictureDto.getId())) {
//					pict.setIsHeadlinePicture(true);
//				}
//			});
//		} else {
//			throw new InvalidPictureException(new ErrorMessageDto(ErrorCode.PICTURE, "Photo inexistante"));
//		}
//		
//		memberProfilePictures.setPictures(pictures);
//		profile.setProfilePictures(memberProfilePictures);
//		this.memberProfileRepo.save(profile);
//		
//		return pictures.stream().map(PictureDto::new).collect(Collectors.toList());
//	}
//	
//	public List<PictureDto> getProfilePictures() {
//		MemberProfile profile = memberProfileRepo.findEagerByMemberId(
//				memberRepo.findByEmail(securityService.getUserEmail()
//						)
//				.get().getId()).get();
//		MemberProfilePictures memberProfilePictures = profile.getProfilePictures();
//		List<Picture> pictures = memberProfilePictures.getPictures();
//		
//		return pictures.stream().map(PictureDto::new).collect(Collectors.toList());
//	}
//	
//	@Transactional
//	public List<PictureDto> deleteProfilePicture(String id) throws InvalidPictureException {
//		MemberProfile profile = memberProfileRepo.findEagerByMemberId(
//				memberRepo.findByEmail(securityService.getUserEmail()
//						)
//				.get().getId()).get();
//		MemberProfilePictures memberProfilePictures = profile.getProfilePictures();
//		List<Picture> pictures = memberProfilePictures.getPictures();
//		
//		if ( pictures.stream().anyMatch(pict -> pict.getId().equals(id)) ) {
//			this.pictureRepo.deleteById(id);
//			pictures = pictures.stream().filter(pict -> !pict.getId().equals(id)).collect(Collectors.toList());
//		} else {
//			throw new InvalidPictureException(new ErrorMessageDto(ErrorCode.PICTURE, "Photo inexistante"));
//		}
//		
//		memberProfilePictures.setPictures(pictures);
//		profile.setProfilePictures(memberProfilePictures);
//		this.memberProfileRepo.save(profile);
//		
//		return pictures.stream().map(PictureDto::new).collect(Collectors.toList());
//	}
//	
//	@Transactional
//	public ProfileDto updateBio(ProfileDto profileDto) {
//		MemberProfile profile = memberProfileRepo.findEagerByMemberId(
//				memberRepo.findByEmail(securityService.getUserEmail()
//						)
//				.get().getId()).get();
//
//		profile.setBio(profileDto.getBio());
//		this.memberProfileRepo.save(profile);
//		
//		return profileDto;
//	}

}
