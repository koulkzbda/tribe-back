package tribe.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tribe.domain.System;

public interface SystemRepo extends JpaRepository<System, String> {
	
	@Query("select distinct s from System s "
			+ "join s.member m "
			+ "left join s.habitStacks hs "
			+ "join hs.progressions p "
			+ "join p.habit h "
			+ "left join h.identities i "
			+ "left join i.weightings w "
			+ "left join w.identityCategory ic "
			+ "join p.habitContract hc "
			+ "left join p.location l "
			+ "join p.repetitions r "
			+ "join r.repetitionStatus rs "
			+ "left join r.metricValues mv "
			+ "left join mv.metric mt "
			+ "left join r.publicationPictures pp "
			+ "left join r.likes lk "
			+ "left join r.commentsOfPublication com "
			+ "left join pp.pictures pict "
			+ "left join p.preparationHabit ph "
			+ "left join ph.location phl "
			+ "left join p.conditionningHabit ch "
			+ "left join ch.location chl "
			+ "left join p.reward rh "
			+ "left join rh.location rhl "
			+ "where m.id = ?1 and "
			+ "rs.updatedAt >= ?2 and "
			+ "p.isActive = TRUE")
	List<System> findWithHabitStacksByMemberId(String id, LocalDateTime today);
	
}