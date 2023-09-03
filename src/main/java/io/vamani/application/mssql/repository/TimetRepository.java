package io.vamani.application.mssql.repository;

import io.vamani.application.mssql.domain.Timet;
import io.vamani.application.mssql.domain.TimetId;
import org.springframework.data.jpa.repository.JpaRepository;
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
public interface TimetRepository extends JpaRepository<Timet, TimetId> {
    Timet findAllByTimetId(TimetId timetId);
}
