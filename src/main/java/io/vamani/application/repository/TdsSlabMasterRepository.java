package io.vamani.application.repository;

import io.vamani.application.domain.TdsSlabMaster;
import io.vamani.application.domain.TrailMockOperation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the TdsSlabMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TdsSlabMasterRepository extends JpaRepository<TdsSlabMaster, Long> {
    /*@Query("select tdsSlabMaster from TdsSlabMaster tdsSlabMaster where ?1 between tdsSlabMaster.ageStart and tdsSlabMaster.ageEnd and tdsSlabMaster.gender=?2 order by tdsSlabMaster.amountStart")
    List<TdsSlabMaster> findAllByAgeEndAndGender(Integer age, String gender);*/

    @Query("select tdsSlabMaster from TdsSlabMaster tdsSlabMaster where ?1 between tdsSlabMaster.ageStart and tdsSlabMaster.ageEnd and tdsSlabMaster.gender=?2 and tdsSlabMaster.finYear = ?3 order by tdsSlabMaster.amountStart")
    List<TdsSlabMaster> findAllByAgeEndAndGender(Integer age, String gender, String finYear);

    @Query("select tdsSlabMaster from TdsSlabMaster tdsSlabMaster where ?1 between tdsSlabMaster.ageStart and tdsSlabMaster.ageEnd and tdsSlabMaster.gender=?2 and tdsSlabMaster.finYear = ?3 and tdsSlabMaster.regimeType = ?4 order by tdsSlabMaster.amountStart")
    List<TdsSlabMaster> findAllByAgeEndAndGenderAndRegimeType(Integer age, String gender, String finYear, String regimeType);

    @Query("select tdsSlabMaster from TdsSlabMaster tdsSlabMaster where tdsSlabMaster.finYear=?1 and tdsSlabMaster.gender=?2 order by ageStart")
    List<TdsSlabMaster> findAllByYear(String year, String gender);

    @Query(value = "select distinct tds_slab_master from TdsSlabMaster tds_slab_master")
    Page<TdsSlabMaster> findAllWithUnique(Pageable pageable);

    @Query(value = "select tdsSlabMaster from TdsSlabMaster tdsSlabMaster where tdsSlabMaster.finYear=?1")
    List<TdsSlabMaster> findByYear(String year);
}
