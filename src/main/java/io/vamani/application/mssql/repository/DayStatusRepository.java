package io.vamani.application.mssql.repository;

import io.swagger.models.auth.In;
import io.vamani.application.mssql.domain.DayStatus;
import io.vamani.application.mssql.domain.DayStatusId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Timet entity.
 */
@SuppressWarnings("unused")
@Repository
@Transactional("partnerTransactionManager")
@PersistenceContext(name = "partnerEntityManagerFactory")
public interface DayStatusRepository extends JpaRepository<DayStatus, DayStatusId> {

    @Query("select dayStatus from DayStatus dayStatus where dayStatus.id.empCode = ?1 and EXTRACT (month FROM dayStatus.id.dayno) = ?2 AND EXTRACT (year FROM dayStatus.id.dayno) = ?3 order by dayStatus.id.dayno")
    public List<DayStatus> findAllByEmpCodeAndDate(String empCode, Integer month, Integer year);

    @Query("select dayStatus.inTm, dayStatus.outTm, dayStatus.status, dayStatus.inDt, dayStatus.outDt from DayStatus dayStatus where dayStatus.id.empCode = ?1 and EXTRACT (day FROM dayStatus.id.dayno) = ?2 and EXTRACT (month FROM dayStatus.id.dayno) = ?3 AND EXTRACT (year FROM dayStatus.id.dayno) = ?4")
    List<Object[]> findByIdAndDayNo(String empCode, Integer day, Integer month, Integer year);

    @Query("select dayStatus from DayStatus dayStatus where dayStatus.id.empCode = ?1 and EXTRACT (day FROM dayStatus.id.dayno) = ?2 and EXTRACT (month FROM dayStatus.id.dayno) = ?3 AND EXTRACT (year FROM dayStatus.id.dayno) = ?4")
    public DayStatus findByEmpCodeAndDate(String empCode, Integer day, Integer month, Integer year);
}
