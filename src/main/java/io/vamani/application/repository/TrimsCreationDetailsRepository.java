package io.vamani.application.repository;

import io.vamani.application.domain.TrimsCreationDetails;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Spring Data  repository for the TrimsCreationDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TrimsCreationDetailsRepository extends JpaRepository<TrimsCreationDetails, Long> {
    @Query("select trimsCreationDetails from TrimsCreationDetails trimsCreationDetails where trimsCreationDetails.trimsCreationMaster.id=?1 order by id")
    List<TrimsCreationDetails> findAllByTrimsCreationMasterId(Long Id);

    @Modifying
    @Transactional
    @Query("delete from TrimsCreationDetails trimsCreationDetails where trimsCreationDetails.trimsCreationMaster.id =?1")
    void deleteAllByTrimsCreationMasterId(Long trimsCreationMasterId);
}
