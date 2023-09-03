package io.vamani.application.repository;

import io.vamani.application.domain.TdsGroupMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the TdsGroupMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TdsGroupMasterRepository extends JpaRepository<TdsGroupMaster, Long> {
    @Query("select tdsGroupMaster from TdsGroupMaster tdsGroupMaster where tdsGroupMaster.year=?1 order by coalesce(tdsGroupMaster.groupOrder, 0), tdsGroupMaster.id")
    List<TdsGroupMaster> findAllYear(int year);
}
