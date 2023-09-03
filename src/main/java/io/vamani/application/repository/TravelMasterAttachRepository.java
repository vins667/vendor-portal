package io.vamani.application.repository;

import io.vamani.application.domain.TravelMasterAttach;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the TravelMasterAttach entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TravelMasterAttachRepository extends JpaRepository<TravelMasterAttach, Long> {
    @Query("select travelMasterAttach from TravelMasterAttach travelMasterAttach where travelMasterAttach.travelApplicationMaster.id = ?1")
    List<TravelMasterAttach> findAllByMasterId(Long masterId);
}
