package io.vamani.application.mssql.repository;

import io.vamani.application.mssql.domain.Catgory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Spring Data  repository for the Catgory entity.
 */
@SuppressWarnings("unused")
@Repository
@Transactional("partnerTransactionManager")
@PersistenceContext(name = "partnerEntityManagerFactory")
public interface CatgoryRepository extends JpaRepository<Catgory, Long> {
    @Query("select catgory from Catgory catgory order by catgory.catName")
    List<Catgory> orderedAll();
}
