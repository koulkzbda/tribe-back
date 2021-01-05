package tribe.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tribe.domain.ProfilePictures;

public interface ProfilePicturesRepo extends JpaRepository<ProfilePictures, String> {
	
	Optional<ProfilePictures> findByProfileId(Long id);
	
}