package io.vamani.application.mssql.repository;

import io.vamani.application.mssql.domain.Designation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Spring Data  repository for the Timet entity.
 */
@SuppressWarnings("unused")
@Repository
@Transactional("partnerTransactionManager")
@PersistenceContext(name = "partnerEntityManagerFactory")
public interface DesignationRepository extends JpaRepository<Designation, Integer> {

    @Query("select designation from Designation designation order by designation.desCode")
    List<Designation> findAll();
}
