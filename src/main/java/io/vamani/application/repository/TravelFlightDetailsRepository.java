package io.vamani.application.repository;
import io.vamani.application.domain.TravelAccommodationDetails;
import io.vamani.application.domain.TravelApplicationMaster;
import io.vamani.application.domain.TravelFlightDetails;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 * Spring Data  repository for the TravelFlightDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TravelFlightDetailsRepository extends JpaRepository<TravelFlightDetails, Long> {

	@Query("select travelFlightDetails from TravelFlightDetails travelFlightDetails where travelFlightDetails.travelApplicationMaster.id =?1")
	List<TravelFlightDetails> findByTravelApplicationMasterId(Long id);
	
	@Modifying
    @Transactional
    @Query("delete from TravelFlightDetails travelFlightDetails where travelFlightDetails.travelApplicationMaster.id =?1")
	void deleteFlightDtilById(Long id);

}
