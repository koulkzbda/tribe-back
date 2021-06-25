package tribe.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tribe.domain.habitTracking.Identity;

public interface IdentityRepo extends JpaRepository<Identity, String> {
	
	@Query("select i from Identity i "
			+ "join i.member m "
			+ "left join fetch i.weightings w "
			+ "where m.id = ?1")
	List<Identity> findEagerByMemberId(String id);
	
	@Query("select i from Identity i "
			+ "join i.member m "
			+ "left join fetch i.habits h"
			+ "left join fetch i.weightings w "
			+ "where i.id = ?1")
	Optional<Identity> findByIdWithHabitsAndWeightings(String id);
	
}