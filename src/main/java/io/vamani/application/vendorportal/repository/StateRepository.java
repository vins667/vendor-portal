package io.vamani.application.vendorportal.repository;

import io.vamani.application.vendorportal.domain.State;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.util.List;


/**
 * Spring Data  repository for the State entity.
 */
@SuppressWarnings("unused")
@Repository
@Transactional("vendorportalTransactionManager")
@PersistenceContext(name = "vendorportalEntityManagerFactory")
public interface StateRepository extends JpaRepository<State, Long> {
    @Query("select state from State state where state.country.id=?1 order by state.stateName")
    List<State> findAllByCountry(Long countryId);

}
