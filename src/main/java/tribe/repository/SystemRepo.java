package tribe.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tribe.domain.habitTracking.System;

public interface SystemRepo extends JpaRepository<System, String> {
	
	@Query("select distinct s from System s "
			+ "left join fetch s.identities i "
			+ "left join fetch i.weightings w "
			+ "left join fetch s.habitStacks hs "
			+ "left join fetch hs.weekdays wd "
			+ "left join fetch hs.progressions p "
			+ "left join p.habitContract hc "
			+ "left join p.location l "
			+ "left join fetch p.metrics metrics "
			+ "left join s.member m "
			+ "where m.id = ?1 "
			+ "and s.isActive = TRUE")
	List<System> findEagerByMemberId(String id);
	
	@Query("select distinct s from System s "
			+ "left join fetch s.habitStacks hs "
			+ "left join fetch s.identities i "
			+ "where s.id = ?1 "
			+ "and s.isActive = TRUE")
	Optional<System> findByIdWithHabitStacksAndIdentities(String id);
	
}