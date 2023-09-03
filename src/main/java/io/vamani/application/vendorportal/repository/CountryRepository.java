package io.vamani.application.vendorportal.repository;

import io.vamani.application.vendorportal.domain.Country;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.util.List;


/**
 * Spring Data  repository for the Country entity.
 */
@SuppressWarnings("unused")
@Repository
@Transactional("vendorportalTransactionManager")
@PersistenceContext(name = "vendorportalEntityManagerFactory")
public interface CountryRepository extends JpaRepository<Country, Long> {
    @Query("select country from Country country order by country.countryName")
    List<Country> orderedAll();
}
