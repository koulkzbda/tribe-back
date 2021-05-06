package tribe.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import tribe.controller.dto.ErrorCode;
import tribe.controller.dto.ErrorMessageDto;
import tribe.controller.dto.PictureDto;
import tribe.domain.socialNetwork.MemberProfile;
import tribe.domain.socialNetwork.MemberProfilePictures;
import tribe.domain.socialNetwork.Picture;
import tribe.exception.NoPicturesFoundException;
import tribe.repository.MemberProfilePicturesRepo;
import tribe.repository.MemberProfileRepo;
import tribe.repository.MemberRepo;
import tribe.repository.PictureRepo;

@Service//  TO DO
public class PublicationService {

	protected MemberRepo memberRepo;
	protected MemberProfileRepo memberProfileRepo;
	protected MemberProfilePicturesRepo memberProfilePicturesRepo;
	protected PictureRepo pictureRepo;
	protected SecurityServiceImpl securityService;
	protected MessageSource messageSource;

	public PublicationService(MemberRepo memberRepo, MemberProfileRepo memberProfileRepo, MemberProfilePicturesRepo memberProfilePicturesRepo,
			PictureRepo pictureRepo, SecurityServiceImpl securityService, MessageSource messageSource) {
		this.memberRepo = memberRepo;
		this.memberProfileRepo = memberProfileRepo;
		this.securityService = securityService;
		this.memberProfilePicturesRepo = memberProfilePicturesRepo;
		this.pictureRepo = pictureRepo;
		this.messageSource = messageSource;
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
	public List<PictureDto> setProfilePicture(PictureDto pictureDto) throws NoPicturesFoundException {
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
			throw new NoPicturesFoundException(new ErrorMessageDto(
					ErrorCode.PICTURE,
					messageSource.getMessage("errorMessage.inexistingPicture", null, LocaleContextHolder.getLocale()),
					messageSource.getMessage("errorMessage.NoPictureFoundWithId", null, LocaleContextHolder.getLocale()) + " " + pictureDto.getId()
					));
		}
		
		memberProfilePictures.setPictures(pictures);
		profile.setProfilePictures(memberProfilePictures);
		this.memberProfileRepo.save(profile);
		
		return pictures.stream().map(PictureDto::new).collect(Collectors.toList());
	}
	
	@Transactional
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
	public List<PictureDto> deleteProfilePicture(String id) throws NoPicturesFoundException {
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
			throw new NoPicturesFoundException(new ErrorMessageDto(
					ErrorCode.PICTURE,
					messageSource.getMessage("errorMessage.inexistingPicture", null, LocaleContextHolder.getLocale()),
					messageSource.getMessage("errorMessage.NoPictureFoundWithId", null, LocaleContextHolder.getLocale()) + " " + id
					));
		}
		
		memberProfilePictures.setPictures(pictures);
		profile.setProfilePictures(memberProfilePictures);
		this.memberProfileRepo.save(profile);
		
		return pictures.stream().map(PictureDto::new).collect(Collectors.toList());
	}

}
