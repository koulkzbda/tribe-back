package tribe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tribe.domain.IdentityCategory;

public interface IdentityCategoryRepo extends JpaRepository<IdentityCategory, String> {
	
}