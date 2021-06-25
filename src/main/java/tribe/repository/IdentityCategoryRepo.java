package tribe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tribe.domain.habitTracking.IdentityCategory;

public interface IdentityCategoryRepo extends JpaRepository<IdentityCategory, String> {
	
}