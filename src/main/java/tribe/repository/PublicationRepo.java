package tribe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tribe.domain.socialNetwork.Publication;

public interface PublicationRepo extends JpaRepository<Publication, String> {
	
}