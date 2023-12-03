package io.vamani.application.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.vamani.application.domain.StitchCostHeadMaster;
import io.vamani.application.domain.StitchCostSubHeadDetails;

@Repository
public interface StitchCostSubHeadDetailsRepository extends JpaRepository<StitchCostSubHeadDetails, Long> {
	 @Query("select costhead from StitchCostHeadMaster costhead join fetch costhead.stitchCostSubHeadMaster costSubHead "
	 		+ " left join costSubHead.stitchCostSubHeadDetails details where details.factory=?1 "
	 		+ " and TO_CHAR(details.enterdDate,'yyyy-mm-dd')=TO_CHAR(CURRENT_DATE,'yyyy-mm-dd')")
     List<StitchCostHeadMaster> findAllByFactoryCode(String factory);
	 
}
