package tribe.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tribe.domain.MemberProfile;

public interface MemberProfileRepo extends JpaRepository<MemberProfile, String> {

	Optional<MemberProfile> findByMemberId(String id);
	
	@Query("select p from MemberProfile p join p.member m left join fetch p.memberProfilePictures t where m.id = ?1")
	Optional<MemberProfile> findEagerByMemberId(String id);
	
}