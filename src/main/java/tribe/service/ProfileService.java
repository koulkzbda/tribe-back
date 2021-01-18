package tribe.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import tribe.controller.dto.ErrorCode;
import tribe.controller.dto.ErrorMessageDto;
import tribe.controller.dto.PictureDto;
import tribe.controller.dto.ProfileDto;
import tribe.domain.MemberProfile;
import tribe.domain.Picture;
import tribe.domain.MemberProfilePictures;
import tribe.exception.InvalidPictureException;
import tribe.repository.MemberProfileRepo;
import tribe.repository.MemberRepo;
import tribe.repository.PictureRepo;
import tribe.repository.MemberProfilePicturesRepo;

@Service
public class ProfileService {

	protected MemberRepo memberRepo;
	protected MemberProfileRepo memberProfileRepo;
	protected MemberProfilePicturesRepo memberProfilePicturesRepo;
	protected PictureRepo pictureRepo;
	protected SecurityServiceImpl securityService;

	public ProfileService(MemberRepo memberRepo, MemberProfileRepo memberProfileRepo, MemberProfilePicturesRepo memberProfilePicturesRepo,
			PictureRepo pictureRepo, SecurityServiceImpl securityService) {
		this.memberRepo = memberRepo;
		this.memberProfileRepo = memberProfileRepo;
		this.securityService = securityService;
		this.memberProfilePicturesRepo = memberProfilePicturesRepo;
		this.pictureRepo = pictureRepo;
	}

	public ProfileDto findByConnectedMember() {
		return new ProfileDto(memberProfileRepo
				.findEagerByMemberId(memberRepo.findByEmail(securityService.getUserEmail()).get().getId()).get());

	}

	@Transactional
	public List<PictureDto> addProfilePictures(MultipartFile[] files, String profileId, String profilePictureName)
			throws IOException, NoSuchElementException {

		MemberProfilePictures pictures = this.memberProfilePicturesRepo.findByMemberProfileId(profileId).get();
		MemberProfile profile = this.memberProfileRepo.findById(profileId).get();
		List<Picture> picturesList = new ArrayList<Picture>();

		for (int i = 0; i < files.length; i++) {
			String fileName = StringUtils.cleanPath(files[i].getOriginalFilename());
			Picture picture = new Picture(fileName, files[i].getContentType(), files[i].getBytes());
			picture.setPictures(pictures);
			if (fileName.equals(profilePictureName)) {
				profile.getProfilePictures().getPictures().stream().forEach(pict -> pict.setIsHeadlinePicture(false));
				picture.setIsHeadlinePicture(true);
			}
			picturesList.add(picture);
		}

		pictures.addPictures(picturesList);
		profile.setProfilePictures(pictures);

		this.memberProfileRepo.save(profile);
		
		return getProfilePictures();

	}

	@Transactional
	public List<PictureDto> setProfilePicture(PictureDto pictureDto) throws InvalidPictureException {
		MemberProfile profile = memberProfileRepo.findEagerByMemberId(
				memberRepo.findByEmail(securityService.getUserEmail()
						)
				.get().getId()).get();
		MemberProfilePictures memberProfilePictures = profile.getProfilePictures();
		List<Picture> pictures = memberProfilePictures.getPictures();
		
		if ( pictures.stream().anyMatch(pict -> pict.getId().equals(pictureDto.getId())) ) {
			pictures.stream().forEach(pict -> {
				
				pict.setIsHeadlinePicture(false);
				if (pict.getId().equals(pictureDto.getId())) {
					pict.setIsHeadlinePicture(true);
				}
			});
		} else {
			throw new InvalidPictureException(new ErrorMessageDto(ErrorCode.PICTURE, "Photo inexistante"));
		}
		
		memberProfilePictures.setPictures(pictures);
		profile.setProfilePictures(memberProfilePictures);
		this.memberProfileRepo.save(profile);
		
		return pictures.stream().map(PictureDto::new).collect(Collectors.toList());
	}
	
	public List<PictureDto> getProfilePictures() {
		MemberProfile profile = memberProfileRepo.findEagerByMemberId(
				memberRepo.findByEmail(securityService.getUserEmail()
						)
				.get().getId()).get();
		MemberProfilePictures memberProfilePictures = profile.getProfilePictures();
		List<Picture> pictures = memberProfilePictures.getPictures();
		
		return pictures.stream().map(PictureDto::new).collect(Collectors.toList());
	}
	
	@Transactional
	public List<PictureDto> deleteProfilePicture(String id) throws InvalidPictureException {
		MemberProfile profile = memberProfileRepo.findEagerByMemberId(
				memberRepo.findByEmail(securityService.getUserEmail()
						)
				.get().getId()).get();
		MemberProfilePictures memberProfilePictures = profile.getProfilePictures();
		List<Picture> pictures = memberProfilePictures.getPictures();
		
		if ( pictures.stream().anyMatch(pict -> pict.getId().equals(id)) ) {
			this.pictureRepo.deleteById(id);
			pictures = pictures.stream().filter(pict -> !pict.getId().equals(id)).collect(Collectors.toList());
		} else {
			throw new InvalidPictureException(new ErrorMessageDto(ErrorCode.PICTURE, "Photo inexistante"));
		}
		
		memberProfilePictures.setPictures(pictures);
		profile.setProfilePictures(memberProfilePictures);
		this.memberProfileRepo.save(profile);
		
		return pictures.stream().map(PictureDto::new).collect(Collectors.toList());
	}
	
	@Transactional
	public ProfileDto updateBio(ProfileDto profileDto) {
		MemberProfile profile = memberProfileRepo.findEagerByMemberId(
				memberRepo.findByEmail(securityService.getUserEmail()
						)
				.get().getId()).get();

		profile.setBio(profileDto.getBio());
		this.memberProfileRepo.save(profile);
		
		return profileDto;
	}

}
