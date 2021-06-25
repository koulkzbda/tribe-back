package tribe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tribe.domain.habitTracking.Step;

public interface StepRepo extends JpaRepository<Step, String> {
	
	List<Step> findByMemberId(String id);
	
}