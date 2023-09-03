package io.vamani.application.mssql.repository;

import io.vamani.application.mssql.domain.Foodcat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Spring Data  repository for the Foodcat entity.
 */
@SuppressWarnings("unused")
@Repository
@Transactional("partnerTransactionManager")
@PersistenceContext(name = "partnerEntityManagerFactory")
public interface FoodcatRepository extends JpaRepository<Foodcat, Long> {
    @Query("select foodcat from Foodcat foodcat order by foodcat.foodDesc")
    List<Foodcat> orderedAll();
}
