package tribe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tribe.domain.socialNetwork.Tribe;

public interface TribeRepo extends JpaRepository<Tribe, String> {
	
	@Query("select distinct t from Tribe t "
			+ "left join t.tribeProfile tp "
			+ "left join tp.tribeProfilePictures tpp "
			+ "left join fetch t.systems s "
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
			+ "and s.isActive = TRUE "
			+ "and t.validated = TRUE "
			+ "and p.isActive = TRUE")
	List<Tribe> findEagerByMemberId(String id);
	
}