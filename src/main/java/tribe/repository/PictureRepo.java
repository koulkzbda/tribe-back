package tribe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tribe.domain.socialNetwork.Picture;

public interface PictureRepo extends JpaRepository<Picture, String> {
	
}