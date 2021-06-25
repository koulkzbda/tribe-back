package tribe.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tribe.domain.habitTracking.Habit;

public interface HabitRepo extends JpaRepository<Habit, String> {
	
	
	
	@Query("select distinct h from Habit h "
			+ "left join fetch h.identities i "
			+ "left join fetch h.progressions p "
			+ "left join fetch p.repetitions r "
			+ "left join r.repetitionStatus rs "
			+ "where h.id = ?1 "
			+ "and rs.updatedAt >= ?2")
	Optional<Habit> findByIdWithIdentitiesProgressionsAndTodayRepetition(String id, LocalDateTime today);
	
}