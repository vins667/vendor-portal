package io.vamani.application.repository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import io.vamani.application.domain.CostingGroupDetails;

/**
 * Spring Data  repository for the CostingGroupDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CostingGroupDetailsRepository extends JpaRepository<CostingGroupDetails, Long> {

	@Query("select costingGroupDetails from CostingGroupDetails costingGroupDetails where costingGroupDetails.code like ?1 and costingGroupDetails.description like ?2")
	 Page<CostingGroupDetails> findAllByCodeAndDesc(String code, String description, Pageable pageable);
	 
	 @Query("select costingGroupDetails from CostingGroupDetails costingGroupDetails where costingGroupDetails.code= ?1")
	 CostingGroupDetails findAllByCode(String code);
	 
	 @Query("select costingGroupDetails from CostingGroupDetails costingGroupDetails where costingGroupDetails.costingGroupMaster.id= ?1")
	 List<CostingGroupDetails> findAllByCostingGroupMasterId(long costingGroupMasterid);
}
