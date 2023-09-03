package io.vamani.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import io.vamani.application.domain.VcutSessionDetails;


/**
 * Spring Data  repository for the VcutSessionDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VcutSessionDetailsRepository extends JpaRepository<VcutSessionDetails, Long> {

    @Query("select vcutSessionDetails from VcutSessionDetails vcutSessionDetails where vcutSessionDetails.vcutSessionMaster.id=?1 order by vcutSessionDetails.order")
	 List<VcutSessionDetails> findVcutSessionMasterId(Long id);

    @Query("select vcutSessionDetails from VcutSessionDetails vcutSessionDetails where vcutSessionDetails.vcutSessionMaster.id=?1 and vcutSessionDetails.displayFlag IN('0','1') order by vcutSessionDetails.order")
    List<VcutSessionDetails> findVcutSessionMasterIdAndFlag(Long id);

    @Modifying
    @Transactional
    @Query("delete from VcutSessionDetails vcutSessionDetails  where vcutSessionDetails.vcutSessionMaster.id =?1")
    void deleteAllByVcutSessionMasterId(Long masterId);
}
