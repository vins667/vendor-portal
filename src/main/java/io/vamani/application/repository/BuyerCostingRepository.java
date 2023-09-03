package io.vamani.application.repository;
import io.vamani.application.domain.BuyerCosting;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the BuyerCosting entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BuyerCostingRepository extends JpaRepository<BuyerCosting, Long> {

}
