package tribe.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tribe.domain.socialNetwork.Member;

public interface MemberRepo extends JpaRepository<Member, String> {

	Optional<Member> findByEmail(String email);
	
}
