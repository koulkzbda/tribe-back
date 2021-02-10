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
import tribe.domain.Picture;
import tribe.domain.PublicationPictures;
import tribe.domain.Repetition;
import tribe.exception.InvalidPictureException;
import tribe.repository.PictureRepo;
import tribe.repository.PublicationPicturesRepo;
import tribe.repository.RepetitionRepo;

@Service//  TO DO
public class RepetitionService {

	protected RepetitionRepo repetitionRepo;
	protected PublicationPicturesRepo publicationPicturesRepo;
	protected PictureRepo pictureRepo;

	public RepetitionService(RepetitionRepo repetitionRepo, PublicationPicturesRepo publicationPicturesRepo,
			PictureRepo pictureRepo) {
		this.repetitionRepo = repetitionRepo;
		this.publicationPicturesRepo = publicationPicturesRepo;
		this.pictureRepo = pictureRepo;
	}

	@Transactional
	public List<PictureDto> addPublicationPictures(MultipartFile[] files, String publicationId, String profilePictureName)
			throws IOException, NoSuchElementException {

		PublicationPictures pictures = this.publicationPicturesRepo.findByPublicationId(publicationId).get();
		Repetition repetition = this.repetitionRepo.findById(publicationId).get();
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
	public List<PictureDto> setHeadlinePicture(PictureDto pictureDto, String publicationId) throws InvalidPictureException {
		
		PublicationPictures publicationPictures = this.publicationPicturesRepo.findByPublicationId(publicationId).get();
		List<Picture> pictures = publicationPictures.getPictures();
		
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
		
		publicationPictures.setPictures(pictures);
		publicationPicturesRepo.save(publicationPictures);
		
		return pictures.stream().map(PictureDto::new).collect(Collectors.toList());
	}
	
	public List<PictureDto> getPublicationPictures(String publicationId) {
		List<Picture> pictures = this.publicationPicturesRepo.findByPublicationId(publicationId).get().getPictures();
		
		return pictures.stream().map(PictureDto::new).collect(Collectors.toList());
	}
	
	@Transactional
	public List<PictureDto> deletePicture(String publicationId, String pictureId) throws InvalidPictureException {
		
		PublicationPictures publicationPictures = this.publicationPicturesRepo.findByPublicationId(publicationId).get();
		List<Picture> pictures = publicationPictures.getPictures();
		
		if ( pictures.stream().anyMatch(pict -> pict.getId().equals(pictureId)) ) {
			this.pictureRepo.deleteById(pictureId);
			pictures = pictures.stream().filter(pict -> !pict.getId().equals(pictureId)).collect(Collectors.toList());
		} else {
			throw new InvalidPictureException(new ErrorMessageDto(ErrorCode.PICTURE, "Photo inexistante"));
		}
		
		publicationPictures.setPictures(pictures);
		publicationPicturesRepo.save(publicationPictures);
		
		return pictures.stream().map(PictureDto::new).collect(Collectors.toList());
	}

}
