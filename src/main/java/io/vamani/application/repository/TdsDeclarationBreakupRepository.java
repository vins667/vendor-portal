package io.vamani.application.repository;

import io.vamani.application.domain.TdsDeclarationBreakup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the TdsDeclarationBreakup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TdsDeclarationBreakupRepository extends JpaRepository<TdsDeclarationBreakup, Long> {
    @Query("select tdsDeclarationBreakup from TdsDeclarationBreakup tdsDeclarationBreakup where tdsDeclarationBreakup.cardNo=?1 and tdsDeclarationBreakup.tdsGroupDetails.tdsGroupMaster.year=?2")
    List<TdsDeclarationBreakup> findAllByCardNo(String cardNo, int year);

    @Query("select tdsDeclarationBreakup from TdsDeclarationBreakup tdsDeclarationBreakup where tdsDeclarationBreakup.cardNo=?1 and tdsDeclarationBreakup.tdsGroupDetails.id = ?2 and tdsDeclarationBreakup.previousEmpDtlsId = ?3")
    TdsDeclarationBreakup findByCardNoAndTdsGroupDetails(String cardNo, Long tdsGroupDetailsId, Long previousEmpDtlsId);
}
