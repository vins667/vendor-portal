package io.vamani.application.mssql.repository;

import io.vamani.application.mssql.domain.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Spring Data  repository for the Section entity.
 */
@SuppressWarnings("unused")
@Repository
@Transactional("partnerTransactionManager")
@PersistenceContext(name = "partnerEntityManagerFactory")
public interface SectionRepository extends JpaRepository<Section, Long> {
    @Query("select section from Section section order by section.desc1")
    List<Section> orderedAll();
}
