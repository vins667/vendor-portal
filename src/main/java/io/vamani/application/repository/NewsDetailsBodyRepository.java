package io.vamani.application.repository;

import io.vamani.application.domain.NewsDetails;
import io.vamani.application.domain.NewsDetailsBody;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.util.Set;


/**
 * Spring Data  repository for the NewsDetailsBody entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NewsDetailsBodyRepository extends JpaRepository<NewsDetailsBody, Long> {

    Set<NewsDetailsBody> findAllByNewsDetailsOrderByIdAsc(NewsDetails newsDetails);

    @Transactional
    @Modifying
    @Query("delete from NewsDetailsBody newsDetailsBody where newsDetailsBody.newsDetails.id=?1")
    void deleteByNewsDetails(Long Id);
}
