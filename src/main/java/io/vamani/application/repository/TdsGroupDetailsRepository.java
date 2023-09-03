package io.vamani.application.repository;

import io.vamani.application.domain.TdsGroupDetails;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the TdsGroupDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TdsGroupDetailsRepository extends JpaRepository<TdsGroupDetails, Long> {
    @Query("select tdsGroupDetails from TdsGroupDetails tdsGroupDetails where tdsGroupDetails.tdsGroupMaster.year=?1 order by tdsGroupDetails.tdsGroupMaster.id")
    List<TdsGroupDetails> findAllYear(int year);

    @Query("select tdsGroupDetails.id from TdsGroupDetails tdsGroupDetails where tdsGroupDetails.tdsGroupMaster.year=?1")
    List<Long> findAllIdsYear(int year);
}
