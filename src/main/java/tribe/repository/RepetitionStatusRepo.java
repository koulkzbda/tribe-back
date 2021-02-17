package tribe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tribe.domain.RepetitionStatus;

public interface RepetitionStatusRepo extends JpaRepository<RepetitionStatus, String> {
	
}