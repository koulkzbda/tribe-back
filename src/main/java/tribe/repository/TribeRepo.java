package tribe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tribe.domain.Tribe;

public interface TribeRepo extends JpaRepository<Tribe, String> {
	
}