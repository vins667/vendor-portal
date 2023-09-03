package io.vamani.application.repository;

import io.vamani.application.domain.CompOffMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;


/**
 * Spring Data  repository for the CompOffMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CompOffMasterRepository extends JpaRepository<CompOffMaster, Long> {

    @Query("select compOffMaster from CompOffMaster compOffMaster where compOffMaster.userCode.login=?1")
    Page<CompOffMaster> findAllByEmpCode(String empCode, Pageable var1);

    @Query("select compOffMaster from CompOffMaster compOffMaster where compOffMaster.userCode.login=?1 and compOffMaster.flag='A' and compOffMaster.availDate is null order by compOffMaster.compOffDate")
    List<CompOffMaster> findAllByEmpCode(String empCode);

    @Query("select compOffMaster from CompOffMaster compOffMaster where compOffMaster.userCode.login = ?1 and compOffMaster.compOffDate = ?2")
    CompOffMaster findByEmpCodeAndDate(String empCode, Instant instant);

    @Query("select compOffMaster from CompOffMaster compOffMaster where compOffMaster.userCode.login = ?1 and compOffMaster.compOffDate between ?2 AND ?3 order by compOffMaster.compOffDate")
    List<CompOffMaster> findAllByEmpCodeAndMonth(String empCode, Instant first, Instant last);

    @Query("select compOffMaster from CompOffMaster compOffMaster where compOffMaster.hodApprovedBy = ?1 and compOffMaster.compOffDate between ?2 AND ?3 order by compOffMaster.compOffDate")
    List<CompOffMaster> findAllByEmpCodeAndMonthApproval(String empCode, Instant first, Instant last);

    @Query("select compOffMaster from CompOffMaster compOffMaster where compOffMaster.hodApprovedBy=?1 and compOffMaster.userCode.login like ?2 and compOffMaster.flag='E' ")
    Page<CompOffMaster> findAllByHodApprovedByPending(String hodApprovedBy, String empCode, Pageable var1);

    @Query("select compOffMaster from CompOffMaster compOffMaster where compOffMaster.hodApprovedBy=?1 and compOffMaster.compOffDate between ?2 and ?3 and compOffMaster.userCode.login like ?4 and compOffMaster.flag in ('A','C')")
    Page<CompOffMaster> findAllByHodApprovedByApproved(String hodApprovedBy, Instant dateFrom, Instant dateTo, String empCode, Pageable var1);

    @Query("select compOffMaster from CompOffMaster compOffMaster where compOffMaster.hodApprovedBy=?1 and compOffMaster.compOffDate between ?2 and ?3 and compOffMaster.userCode.login like ?4 and compOffMaster.flag=?5")
    Page<CompOffMaster> findAllByHodApprovedByRejected(String hodApprovedBy, Instant dateFrom, Instant dateTo, String empCode, String flag, Pageable var1);
}
