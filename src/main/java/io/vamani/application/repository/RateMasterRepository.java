package io.vamani.application.repository;

import io.vamani.application.domain.RateMaster;
import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the RateMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RateMasterRepository extends JpaRepository<RateMaster, Long> {

	@Query("select rateMaster from RateMaster rateMaster where rateMaster.endDate is null")
    Optional<RateMaster> findEndDate();

    @Query("select rateMaster from RateMaster rateMaster where rateMaster.endDate is null and vehicleType=?1")
    Optional<RateMaster> findEndDate(String vehicleType);
	
	@Query("select rateMaster from RateMaster rateMaster where rateMaster.endDate is null")
    RateMaster findAllEndDate();

}
