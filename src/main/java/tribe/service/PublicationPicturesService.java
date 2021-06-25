package tribe.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import tribe.controller.dto.ErrorCode;
import tribe.controller.dto.ErrorMessageDto;
import tribe.controller.dto.PictureDto;
import tribe.domain.habitTracking.Repetition;
import tribe.domain.socialNetwork.Picture;
import tribe.domain.socialNetwork.PublicationPictures;
import tribe.exception.NoPicturesFoundException;
import tribe.exception.NoPublicationFoundException;
import tribe.repository.MemberRepo;
import tribe.repository.PictureRepo;
import tribe.repository.PublicationPicturesRepo;
import tribe.repository.RepetitionRepo;

@Service
public class PublicationPicturesService {

	protected MemberRepo memberRepo;
	protected PublicationPicturesRepo publicationPicturesRepo;
	protected RepetitionRepo repetitionRepo;
	protected PictureRepo pictureRepo;
	protected SecurityServiceImpl securityService;
	protected MessageSource messageSource;

	@Autowired
	public PublicationPicturesService(MemberRepo memberRepo, RepetitionRepo repetitionRepo, PublicationPicturesRepo publicationPicturesRepo,
			PictureRepo pictureRepo, SecurityServiceImpl securityService, MessageSource messageSource) {
		this.memberRepo = memberRepo;
		this.securityService = securityService;
		this.repetitionRepo = repetitionRepo;
		this.publicationPicturesRepo = publicationPicturesRepo;
		this.pictureRepo = pictureRepo;
		this.messageSource = messageSource;
	}

	@Transactional
	public List<PictureDto> addPublicationPictures(MultipartFile[] files, String publicationId, String profilePictureName)
			throws IOException, NoSuchElementException {
		
		PublicationPictures pictures = this.publicationPicturesRepo.findByPublicationId(publicationId)
				.orElseThrow(() -> new NoPicturesFoundException("publication", publicationId));
		Repetition repetition = this.repetitionRepo.findById(publicationId)
				.orElseThrow(() -> new NoPublicationFoundException(publicationId));
		List<Picture> picturesList = new ArrayList<Picture>();

		for (int i = 0; i < files.length; i++) {
			String fileName = StringUtils.cleanPath(files[i].getOriginalFilename());
			Picture picture = new Picture(fileName, files[i].getContentType(), files[i].getBytes());
			picture.setPictures(pictures);
			if (fileName.equals(profilePictureName)) {
				repetition.getPublicationPictures().getPictures().stream().forEach(pict -> pict.setIsHeadlinePicture(false));
				picture.setIsHeadlinePicture(true);
			}
			picturesList.add(picture);
		}

		pictures.addPictures(picturesList);
		repetition.setPublicationPictures(pictures);

		this.repetitionRepo.save(repetition);
		
		return getPublicationPictures(publicationId);

	}

	@Transactional
	public List<PictureDto> setHeadlinePicture(PictureDto pictureDto, String publicationId) throws NoPicturesFoundException {
		
		PublicationPictures publicationPictures = this.publicationPicturesRepo.findByPublicationId(publicationId)
				.orElseThrow(() -> new NoPicturesFoundException("publication", publicationId));
		Set<Picture> pictures = publicationPictures.getPictures();
		
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
		
		publicationPictures.setPictures(pictures);
		publicationPicturesRepo.save(publicationPictures);
		
		return pictures.stream().map(PictureDto::new).collect(Collectors.toList());
	}
	
	public List<PictureDto> getPublicationPictures(String publicationId) {
		Set<Picture> pictures = this.publicationPicturesRepo.findByPublicationId(publicationId)
				.orElseThrow(() -> new NoPicturesFoundException("publication", publicationId))
				.getPictures();
		
		return pictures.stream().map(PictureDto::new).collect(Collectors.toList());
	}
	
	@Transactional
	public List<PictureDto> deletePicture(String publicationId, String pictureId) throws NoPicturesFoundException {
		
		PublicationPictures publicationPictures = this.publicationPicturesRepo.findByPublicationId(publicationId)
				.orElseThrow(() -> new NoPicturesFoundException("publication", publicationId));
		Set<Picture> pictures = publicationPictures.getPictures();
		
		if ( pictures.stream().anyMatch(pict -> pict.getId().equals(pictureId)) ) {
			this.pictureRepo.deleteById(pictureId);
			pictures = pictures.stream().filter(pict -> !pict.getId().equals(pictureId)).collect(Collectors.toSet());
		} else {
			throw new NoPicturesFoundException(new ErrorMessageDto(
					ErrorCode.PICTURE,
					messageSource.getMessage("errorMessage.inexistingPicture", null, LocaleContextHolder.getLocale()),
					messageSource.getMessage("errorMessage.NoPictureFoundWithId", null, LocaleContextHolder.getLocale()) + " " + pictureId
					));
		}
		
		publicationPictures.setPictures(pictures);
		publicationPicturesRepo.save(publicationPictures);
		
		return pictures.stream().map(PictureDto::new).collect(Collectors.toList());
	}

}
