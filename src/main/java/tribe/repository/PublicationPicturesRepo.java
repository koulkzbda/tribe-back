package tribe.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tribe.domain.PublicationPictures;

public interface PublicationPicturesRepo extends JpaRepository<PublicationPictures, String> {
	
	Optional<PublicationPictures> findByPublicationId(String id);
	
}