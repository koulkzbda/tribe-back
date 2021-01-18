package tribe.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tribe.domain.MemberProfilePictures;

public interface MemberProfilePicturesRepo extends JpaRepository<MemberProfilePictures, String> {
	
	Optional<MemberProfilePictures> findByMemberProfileId(String id);
	
}