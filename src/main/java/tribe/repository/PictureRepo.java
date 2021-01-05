package tribe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tribe.domain.Picture;

public interface PictureRepo extends JpaRepository<Picture, String> {
	
}