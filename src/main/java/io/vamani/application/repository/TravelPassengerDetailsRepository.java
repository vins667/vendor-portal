package io.vamani.application.repository;
import io.vamani.application.domain.TravelForexDetails;
import io.vamani.application.domain.TravelPassengerDetails;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 * Spring Data  repository for the TravelPassengerDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TravelPassengerDetailsRepository extends JpaRepository<TravelPassengerDetails, Long> {

	@Query("select travelPassengerDetails from TravelPassengerDetails travelPassengerDetails where travelPassengerDetails.travelApplicationMaster.id =?1")
	List<TravelPassengerDetails> findByTravelApplicationMasterId(Long id);
	
	@Modifying
    @Transactional
    @Query("delete from TravelPassengerDetails travelPassengerDetails where travelPassengerDetails.travelApplicationMaster.id =?1")
	void deletePassengerById(Long id);
	
}
