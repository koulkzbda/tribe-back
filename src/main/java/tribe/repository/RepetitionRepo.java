package tribe.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tribe.domain.Repetition;

public interface RepetitionRepo extends JpaRepository<Repetition, String> {
	
	@Query("select r from Repetition r left join r.progression p left join p.metrics m where r.id = ?1")
	Optional<Repetition> findByIdWithMetrics(String id);
	
}