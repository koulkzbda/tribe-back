package tribe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tribe.domain.key.LikeKey;
import tribe.domain.socialNetwork.Like;

public interface LikeRepo extends JpaRepository<Like, LikeKey> {

//	@Query("select r from ReservationCovoituragePassager r join r.passager p where p.matricule = :matricule")
//	List<ReservationCovoituragePassager> findByPassagerMatricule(@Param("matricule") String matricule);
	
}
