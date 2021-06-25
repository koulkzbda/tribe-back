package tribe.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tribe.domain.enumaration.RepetitionStatusEnum;
import tribe.domain.enumaration.WeekdayEnum;
import tribe.domain.habitTracking.Repetition;

public interface RepetitionRepo extends JpaRepository<Repetition, String> {
	
	@Query("select r from Repetition r left join r.progression p left join p.metrics m where r.id = ?1")
	Optional<Repetition> findByIdWithMetrics(String id);
	
	@Query("select distinct r from Repetition r "
			+ "left join r.repetitionStatus rs "
			+ "left join r.progression p "
			+ "left join p.habitStack hs "
			+ "left join hs.system s "
			+ "left join s.member m "
			+ "left join hs.weekdays wd "
			+ "where m.id = ?1 and "
			+ "wd.weekday = ?2 and "
			+ "rs.repetitionStatus = ?3 and "
			+ "rs.createdAt between ?5 and ?4 and "
			+ "p.isActive = TRUE")
	List<Repetition> findNotDoneYesterday(String id, WeekdayEnum yesterdaydayEnum, RepetitionStatusEnum toDo, LocalDateTime today, LocalDateTime yesterday);
	
}