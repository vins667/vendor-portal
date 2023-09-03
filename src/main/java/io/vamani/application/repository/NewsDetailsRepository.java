package io.vamani.application.repository;

import io.vamani.application.domain.NewsDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the NewsDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NewsDetailsRepository extends JpaRepository<NewsDetails, Long> {

    @Query(value = "select distinct news_details from NewsDetails news_details left join fetch news_details.factoryMasters",
        countQuery = "select count(distinct news_details) from NewsDetails news_details")
    Page<NewsDetails> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select news_details from NewsDetails news_details where news_details.flag='A' and news_details.endDate>=?1")
    List<NewsDetails> findAll(LocalDate localDate);

    @Query(value = "select distinct news_details from NewsDetails news_details left join fetch news_details.factoryMasters")
    List<NewsDetails> findAllWithEagerRelationships();

    @Query("select news_details from NewsDetails news_details left join fetch news_details.factoryMasters where news_details.id =:id")
    Optional<NewsDetails> findOneWithEagerRelationships(@Param("id") Long id);

}
