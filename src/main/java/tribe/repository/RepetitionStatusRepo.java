package tribe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tribe.domain.habitTracking.RepetitionStatus;

public interface RepetitionStatusRepo extends JpaRepository<RepetitionStatus, String> {
	
}