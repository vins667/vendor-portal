package io.vamani.application.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import io.vamani.application.domain.StitchCostSubHeadDetails;

@Repository
public interface StitchCostSubHeadDetailsRepository extends JpaRepository<StitchCostSubHeadDetails, Long> {
	@Query("select DISTINCT(details.stitchCostSubHeadMaster.stitchCostHeadMaster.id) from StitchCostSubHeadDetails details"
			+ " where details.factory=?1 and to_char(details.enterdDate,'yyyy-mm-dd')=to_char(CURRENT_DATE,'yyyy-mm-dd')"
			+ " order by details.stitchCostSubHeadMaster.stitchCostHeadMaster.id")
	List<Long> findMasterIdByFactoryCode(String factory);
}
