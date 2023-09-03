package io.vamani.application.repository;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import io.vamani.application.domain.CostingProcessMaster;

/**
 * Spring Data  repository for the CostingProcessMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CostingProcessMasterRepository extends JpaRepository<CostingProcessMaster, Long> {
	 @Query("select costingProcessMaster from CostingProcessMaster costingProcessMaster where costingProcessMaster.processcode like ?1 and costingProcessMaster.processdesc like ?2 ")
	 Page<CostingProcessMaster> findAllByCodeAndDsc(String processcode, String processdesc, Pageable pageable);
	 
	 @Query("select costingProcessMaster from CostingProcessMaster costingProcessMaster")
	 List<CostingProcessMaster> findAllCostingProcessMaster();
}
