package io.vamani.application.repository;

import io.vamani.application.domain.NewsDetails;
import io.vamani.application.domain.NewsDetailsAttach;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.util.Set;


/**
 * Spring Data  repository for the NewsDetailsAttach entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NewsDetailsAttachRepository extends JpaRepository<NewsDetailsAttach, Long> {
    Set<NewsDetailsAttach> findAllByNewsDetailsOrderByIdAsc(NewsDetails newsDetails);
}
