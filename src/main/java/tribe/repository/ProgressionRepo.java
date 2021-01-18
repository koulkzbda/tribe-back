package tribe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tribe.domain.Progression;

public interface ProgressionRepo extends JpaRepository<Progression, String> {
	
}