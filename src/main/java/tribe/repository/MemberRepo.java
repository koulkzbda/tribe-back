package tribe.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tribe.domain.Member;

public interface MemberRepo extends JpaRepository<Member, String> {

	Optional<Member> findByEmail(String email);
	
}
