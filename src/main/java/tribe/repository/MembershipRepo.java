package tribe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tribe.domain.Membership;
import tribe.domain.key.MembershipKey;

public interface MembershipRepo extends JpaRepository<Membership, MembershipKey> {

//	@Query("select r from ReservationCovoituragePassager r join r.passager p where p.matricule = :matricule")
//	List<ReservationCovoituragePassager> findByPassagerMatricule(@Param("matricule") String matricule);
	
}
