package io.vamani.application.repository;
import io.vamani.application.domain.BillRegisterDetails;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Spring Data  repository for the BillRegisterDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BillRegisterDetailsRepository extends JpaRepository<BillRegisterDetails, Long> {

    @Query("select billRegisterDetails from BillRegisterDetails billRegisterDetails where billRegisterDetails.billRegisterMaster.id = ?1 order by billRegisterDetails.id")
    List<BillRegisterDetails> findAllByBillRegisterMaster(Long id);

    @Modifying
    @Transactional
    @Query("delete from BillRegisterDetails billRegisterDetails where billRegisterDetails.billRegisterMaster.id = ?1")
    void deleteAllByBillRegisterMaster(Long id);
}
