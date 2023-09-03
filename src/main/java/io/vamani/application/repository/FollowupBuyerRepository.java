package io.vamani.application.repository;
import io.vamani.application.domain.FollowupBuyer;
import io.vamani.application.domain.TdsYearMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/**
 * Spring Data  repository for the FollowupBuyer entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FollowupBuyerRepository extends JpaRepository<FollowupBuyer, Long> {
    @Query("select followupBuyer from FollowupBuyer followupBuyer where followupBuyer.buyercode like ?1 and followupBuyer.buyername like ?2")
    Page<FollowupBuyer> findByBuyercodeAndBuyername(String buyercode, String buyername, Pageable pageable);

}
