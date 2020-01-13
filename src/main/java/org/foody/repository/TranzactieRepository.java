package org.foody.repository;

import org.foody.domain.Tranzactie;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Tranzactie entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TranzactieRepository extends JpaRepository<Tranzactie, Long> {

}
