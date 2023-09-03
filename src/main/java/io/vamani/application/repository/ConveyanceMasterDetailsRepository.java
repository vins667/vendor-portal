package io.vamani.application.repository;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import io.vamani.application.domain.ConveyanceMaster;
import io.vamani.application.domain.ConveyanceMasterDetails;


/**
 * Spring Data  repository for the ConveyanceAttach entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ConveyanceMasterDetailsRepository extends JpaRepository<ConveyanceMasterDetails, Long> {
	Set<ConveyanceMasterDetails> findAllByConveyanceMaster(ConveyanceMaster conveyanceMaster);

    @Query("select conveyanceMasterDetails from ConveyanceMasterDetails conveyanceMasterDetails where conveyanceMasterDetails.conveyanceMaster.id =?1")
    List<ConveyanceMasterDetails> findAllByConveyanceMasterId(Long id);

    @Query("select coalesce(sum(conveyanceMasterDetails.miscAmount), 0) from ConveyanceMasterDetails conveyanceMasterDetails where conveyanceMasterDetails.conveyanceMaster.id =?1")
    Double findMisAmtByConveyanceMasterId(Long id);

    @Modifying
    @Transactional
    @Query("delete from ConveyanceMasterDetails conveyanceMasterDetails where conveyanceMasterDetails.conveyanceMaster.id =?1")
    void deleteAllDetailById(Long masterId);

}
