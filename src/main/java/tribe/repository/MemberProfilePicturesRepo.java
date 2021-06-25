package tribe.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tribe.domain.socialNetwork.MemberProfilePictures;

public interface MemberProfilePicturesRepo extends JpaRepository<MemberProfilePictures, String> {
	
	Optional<MemberProfilePictures> findByMemberProfileId(String id);
	
}