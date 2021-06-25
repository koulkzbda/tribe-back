package tribe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tribe.domain.habitTracking.Weighting;
import tribe.domain.key.WeightingKey;

public interface WeightingRepo extends JpaRepository<Weighting, WeightingKey> {

//	@Query("select r from ReservationCovoituragePassager r join r.passager p where p.matricule = :matricule")
//	List<ReservationCovoituragePassager> findByPassagerMatricule(@Param("matricule") String matricule);
	
}
