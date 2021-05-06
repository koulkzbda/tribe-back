package tribe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tribe.domain.socialNetwork.Tribe;

public interface TribeRepo extends JpaRepository<Tribe, String> {
	
}