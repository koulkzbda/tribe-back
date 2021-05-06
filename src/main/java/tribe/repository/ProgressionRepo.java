package tribe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tribe.domain.enumaration.WeekdayEnum;
import tribe.domain.habitTracking.Progression;

public interface ProgressionRepo extends JpaRepository<Progression, String> {
	
	@Query("select distinct p from Progression p "
			+ "left join p.habitStack hs "
			+ "left join fetch p.repetitions r "
			+ "left join hs.system s "
			+ "left join s.member m "
			+ "left join hs.weekdays wd "
			+ "where m.id = ?1 and "
			+ "wd.weekday = ?2 and "
			+ "p.isActive = TRUE")
	List<Progression> findByMemberIdAndWeekday(String id, WeekdayEnum weekdayEnum);
	
}