package tribe.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tribe.domain.Profile;

public interface ProfileRepo extends JpaRepository<Profile, Long> {

	Optional<Profile> findByMemberId(Long id);
	
	@Query("select p from Profile p join p.member m left join fetch p.profilePictures t where m.id = ?1")
	Optional<Profile> findEagerByMemberId(Long id);
	
}