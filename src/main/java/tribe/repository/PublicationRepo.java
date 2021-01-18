package tribe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tribe.domain.Publication;

public interface PublicationRepo extends JpaRepository<Publication, String> {
	
}