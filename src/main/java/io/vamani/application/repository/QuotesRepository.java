package io.vamani.application.repository;

import io.vamani.application.domain.Quotes;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;


/**
 * Spring Data  repository for the Quotes entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QuotesRepository extends JpaRepository<Quotes, Long> {
    @Query("select count(quotes) from Quotes quotes")
    long getCount();
}
