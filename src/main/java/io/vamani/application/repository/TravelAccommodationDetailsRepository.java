package io.vamani.application.repository;
import io.vamani.application.domain.TravelAccommodationDetails;
import io.vamani.application.domain.TravelApplicationMaster;
import io.vamani.application.domain.TravelFlightDetails;
import io.vamani.application.domain.VcutDeviceLineMaster;
import io.vamani.application.domain.VcutUserDeviceMaster;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 * Spring Data  repository for the TravelAccommodationDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TravelAccommodationDetailsRepository extends JpaRepository<TravelAccommodationDetails, Long> {

	@Query("select travelAccommodationDetails from TravelAccommodationDetails travelAccommodationDetails where travelAccommodationDetails.travelApplicationMaster.id =?1")
	List<TravelAccommodationDetails> findByTravelApplicationMasterId(Long id);
	
	@Modifying
    @Transactional
    @Query("delete from TravelAccommodationDetails travelAccommodationDetails where travelAccommodationDetails.travelApplicationMaster.id =?1")
	void deleteHotelById(Long id);

}
