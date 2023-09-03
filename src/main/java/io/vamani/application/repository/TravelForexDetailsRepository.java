package io.vamani.application.repository;
import io.vamani.application.domain.TravelAccommodationDetails;
import io.vamani.application.domain.TravelApplicationMaster;
import io.vamani.application.domain.TravelFlightDetails;
import io.vamani.application.domain.TravelForexDetails;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 * Spring Data  repository for the TravelForexDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TravelForexDetailsRepository extends JpaRepository<TravelForexDetails, Long> {

	@Query("select travelForexDetails from TravelForexDetails travelForexDetails where travelForexDetails.travelApplicationMaster.id =?1")
	List<TravelForexDetails> findByTravelApplicationMasterId(Long id);
	
	@Modifying
    @Transactional
    @Query("delete from TravelForexDetails travelForexDetails where travelForexDetails.travelApplicationMaster.id =?1")
	void deleteForexById(Long id);
}
