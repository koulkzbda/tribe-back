package tribe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tribe.domain.habitTracking.Identity;

public interface IdentityRepo extends JpaRepository<Identity, String> {
	
}