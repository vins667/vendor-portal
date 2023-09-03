package io.vamani.application.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.vamani.application.domain.VcutStylePlanUpload;


/**
 * Spring Data  repository for the VcutStylePlanUpload entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VcutStylePlanUploadRepository extends JpaRepository<VcutStylePlanUpload, Long> {
    @Query("select cutStylePlanUpload from VcutStylePlanUpload cutStylePlanUpload where cutStylePlanUpload.planDate = ?1 and cutStylePlanUpload.style = ?2 and cutStylePlanUpload.lineNo = ?3")
    VcutStylePlanUpload findByPlanDateAndStyleNo(LocalDate localDate, String styleNo, String lineNo);

    @Query("select cutStylePlanUpload from VcutStylePlanUpload cutStylePlanUpload where cutStylePlanUpload.planDate = ?1 and cutStylePlanUpload.lineNo = ?2 and cutStylePlanUpload.vcutSessionMasterId is not null")
    List<VcutStylePlanUpload> findByPlanDateAndLineNo(LocalDate localDate, String lineNo);

    @Query("select cutStylePlanUpload from VcutStylePlanUpload cutStylePlanUpload where cutStylePlanUpload.planDate between ?1 and ?2 and cutStylePlanUpload.style like ?3 and cutStylePlanUpload.poNo like ?4")
    Page<VcutStylePlanUpload> findByPlanDateAndStyleNoAndPoNo(LocalDate localDate, LocalDate localDateTo, String styleNo, String poNo, Pageable page);

    @Query("select cutStylePlanUpload from VcutStylePlanUpload cutStylePlanUpload where cutStylePlanUpload.planDate = ?1 order by cutStylePlanUpload.factory, cutStylePlanUpload.lineNo")
    List<VcutStylePlanUpload> findByPlanDate(LocalDate localDate);

    @Query("select cutStylePlanUpload from VcutStylePlanUpload cutStylePlanUpload where cutStylePlanUpload.planDate = ?1 order by cutStylePlanUpload.planDate")
    List<VcutStylePlanUpload> findByPlanDatePrevAll(LocalDate localDate);

    @Query("select cutStylePlanUpload from VcutStylePlanUpload cutStylePlanUpload where cutStylePlanUpload.planDate = ?1 and cutStylePlanUpload.factory = ?2 order by cutStylePlanUpload.factory, cutStylePlanUpload.lineNo")
    List<VcutStylePlanUpload> findByPlanDateAndFactory(LocalDate localDate, String factory);

    @Query("select cutStylePlanUpload from VcutStylePlanUpload cutStylePlanUpload where cutStylePlanUpload.planDate = ?1 and cutStylePlanUpload.lineNo = ?2 and coalesce(cutStylePlanUpload.activePlan, false) = true")
    VcutStylePlanUpload findByLineNoAndActivePlan(LocalDate localDate, String lineNo);

}
