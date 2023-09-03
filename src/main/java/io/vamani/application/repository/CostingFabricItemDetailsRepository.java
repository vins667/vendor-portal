package io.vamani.application.repository;
import io.vamani.application.domain.CostingFabricItemDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CostingFabricItemDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CostingFabricItemDetailsRepository extends JpaRepository<CostingFabricItemDetails, Long> {

	 @Query("select costingFabricItemDetails from CostingFabricItemDetails costingFabricItemDetails where costingFabricItemDetails.itemType like ?1 and costingFabricItemDetails.code like ?2")
	 Page<CostingFabricItemDetails> findAllByItemAndCode(String itemtype, String code, Pageable pageable);
	 
	 @Query("select costingFabricItemDetails from CostingFabricItemDetails costingFabricItemDetails where costingFabricItemDetails.itemType= ?1")
	 CostingFabricItemDetails findAllByItemType(String itemtype);
}
