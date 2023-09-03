package io.vamani.application.repository;

import io.vamani.application.domain.TdsYearMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the TdsYearMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TdsYearMasterRepository extends JpaRepository<TdsYearMaster, Long> {
    @Query("select tdsYearMaster from TdsYearMaster tdsYearMaster where tdsYearMaster.active = true and tdsYearMaster.expired = false")
    List<TdsYearMaster> findByActive();

    @Query("select tdsYearMaster from TdsYearMaster tdsYearMaster where tdsYearMaster.code = ?1")
    TdsYearMaster findByCode(String year);
}
