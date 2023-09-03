package io.vamani.application.repository;

import io.vamani.application.domain.TdsDeclaration;
import io.vamani.application.domain.TdsGroupDetails;
import io.vamani.application.mssql.domain.EmployeeView;

import java.time.Instant;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Spring Data  repository for the TdsDeclaration entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TdsDeclarationRepository extends JpaRepository<TdsDeclaration, Long> {
    @Query("select tdsDeclaration from TdsDeclaration tdsDeclaration where tdsDeclaration.cardNo=?1 and tdsDeclaration.tdsGroupDetails.tdsGroupMaster.year=?2")
    List<TdsDeclaration> findAllByCardNo(String cardNo, int year);
    
    @Query("select tdsDeclaration from TdsDeclaration tdsDeclaration where tdsDeclaration.cardNo like ?1")
    Page<TdsDeclaration> findByCardNo(String cardNo, Pageable pageable);
    
    @Query("select distinct tdsDeclaration.cardNo, coalesce(tdsDeclaration.tempLock, 'N') from TdsDeclaration tdsDeclaration where tdsDeclaration.cardNo in (?1) and tdsDeclaration.tdsGroupDetails.tdsGroupMaster.year=?2")
    List<Object> findCardNo(List<String> cardNo, int year);

    @Query("select distinct tdsDeclaration.cardNo from TdsDeclaration tdsDeclaration where tdsDeclaration.cardNo like ?1 and tdsDeclaration.tdsGroupDetails.tdsGroupMaster.year=?2 and ((tdsDeclaration.createdBy = 'AUTO' and tdsDeclaration.lastUpdatedDate is not null) or tdsDeclaration.createdBy<>'AUTO')")
    Page<String> findCardNo(String cardNo, int year, Pageable pageable);

    @Query("select tdsDeclaration from TdsDeclaration tdsDeclaration where tdsDeclaration.cardNo like ?1 and tdsDeclaration.tdsGroupDetails.tdsGroupMaster.year=?2 and ((tdsDeclaration.createdBy = 'AUTO' and tdsDeclaration.lastUpdatedDate is not null) or tdsDeclaration.createdBy<>'AUTO')")
    List<TdsDeclaration> findAllByCardNoLike(String cardNo, int year);

    @Query("select distinct tdsDeclaration.cardNo from TdsDeclaration tdsDeclaration where tdsDeclaration.tdsGroupDetails.tdsGroupMaster.year=?1 and ((tdsDeclaration.createdBy = 'AUTO' and tdsDeclaration.lastUpdatedDate is not null) or tdsDeclaration.createdBy<>'AUTO')")
    List<String> findByCardNo(int year);

    @Modifying
    @Transactional
    @Query("update TdsDeclaration tdsDeclaration set tdsDeclaration.tempLock='Y', tdsDeclaration.tempLockTime=?1 where tdsDeclaration.tdsGroupDetails.id in (?2)")
    void updateTempLock(Instant instant, List<Long> ids);

    @Modifying
    @Transactional
    @Query("update TdsDeclaration tdsDeclaration set tdsDeclaration.tempLock=null, tdsDeclaration.tempLockTime=null where tdsDeclaration.tdsGroupDetails.id in (?1)")
    void updateUnTempLock(List<Long> ids);
}
