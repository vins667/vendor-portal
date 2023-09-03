package io.vamani.application.mssql.repository;

import io.vamani.application.mssql.domain.Hourt;
import io.vamani.application.mssql.domain.HourtId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;

/**
 * Spring Data  repository for the EmployeeView entity.
 */
@SuppressWarnings("unused")
@Repository
@Transactional("partnerTransactionManager")
@PersistenceContext(name = "partnerEntityManagerFactory")
public interface HourtRepository extends JpaRepository<Hourt, HourtId> {
}
