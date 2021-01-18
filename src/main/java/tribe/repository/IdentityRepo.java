package tribe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tribe.domain.Identity;

public interface IdentityRepo extends JpaRepository<Identity, String> {
	
}