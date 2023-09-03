package io.vamani.application.repository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import io.vamani.application.domain.CostingGroupMaster;


/**
 * Spring Data  repository for the CostingGroupMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CostingGroupMasterRepository extends JpaRepository<CostingGroupMaster, Long> {
	 @Query("select costingGroupMaster from CostingGroupMaster costingGroupMaster where costingGroupMaster.code like ?1 and costingGroupMaster.description like ?2")
	 Page<CostingGroupMaster> findAllByCodeAndDesc(String code, String description, Pageable pageable);
	 
	 @Query("select costingGroupMaster from CostingGroupMaster costingGroupMaster where costingGroupMaster.code= ?1")
	 CostingGroupMaster findAllByCode(String code);
	 	 
	 @Query("select costingGroupMaster from CostingGroupMaster costingGroupMaster")
	 List<CostingGroupMaster> findAllCostingGroupMaster();
}
