package io.vamani.application.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import io.vamani.application.domain.CostingEfficiencyMaster;
import io.vamani.application.domain.CostingProcessMaster;

/**
 * Spring Data  repository for the CostingEfficiencyMaste entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CostingEfficiencyMasterRepository extends JpaRepository<CostingEfficiencyMaster, Long> {
	
	 @Query("select costingEfficiencyMaster from CostingEfficiencyMaster costingEfficiencyMaster where costingEfficiencyMaster.fromQuantity  like ?1  and costingEfficiencyMaster.toQuantity like ?2")
	 Page<CostingEfficiencyMaster> findAllByFromQtyAndToQty(String fromqty, String toqty, Pageable pageable);

}
