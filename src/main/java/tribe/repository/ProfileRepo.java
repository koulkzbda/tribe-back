package tribe.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tribe.domain.Profile;

public interface ProfileRepo extends JpaRepository<Profile, Long> {

	Optional<Profile> findByUserId(Long id);
	
}