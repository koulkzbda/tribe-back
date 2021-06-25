package tribe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tribe.domain.socialNetwork.CommentOfPublication;

public interface CommentOfPublicationRepo extends JpaRepository<CommentOfPublication, String> {
	
}