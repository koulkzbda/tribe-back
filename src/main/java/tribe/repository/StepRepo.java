package tribe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tribe.domain.habitTracking.Step;

public interface StepRepo extends JpaRepository<Step, String> {
	
}