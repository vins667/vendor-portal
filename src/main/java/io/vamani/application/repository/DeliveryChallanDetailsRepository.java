package io.vamani.application.repository;
import io.vamani.application.domain.DeliveryChallanDetails;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
/**
 * Spring Data  repository for the DeliveryChallanDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DeliveryChallanDetailsRepository extends JpaRepository<DeliveryChallanDetails, Long> {

	@Query("select deliveryChallanDetails from DeliveryChallanDetails deliveryChallanDetails where deliveryChallanDetails.deliveryChallan.id=?1")
    List<DeliveryChallanDetails> findByDeliveryChallanId(Long id);
	
    @Modifying
    @Transactional
    @Query("delete from DeliveryChallanDetails deliveryChallanDetails where deliveryChallanDetails.deliveryChallan.id=?1")
    void deleteAllByDeliveryChallan(Long masterId);

}
