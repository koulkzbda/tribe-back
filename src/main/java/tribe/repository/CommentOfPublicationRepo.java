package tribe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tribe.domain.CommentOfPublication;

public interface CommentOfPublicationRepo extends JpaRepository<CommentOfPublication, String> {
	
}