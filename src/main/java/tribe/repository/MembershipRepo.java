package tribe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tribe.domain.key.MembershipKey;
import tribe.domain.socialNetwork.Membership;

public interface MembershipRepo extends JpaRepository<Membership, MembershipKey> {

//	@Query("select r from ReservationCovoituragePassager r join r.passager p where p.matricule = :matricule")
//	List<ReservationCovoituragePassager> findByPassagerMatricule(@Param("matricule") String matricule);
	
}
