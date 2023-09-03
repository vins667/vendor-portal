package io.vamani.application.repository;
import io.vamani.application.domain.TravelApplicationMaster;
import io.vamani.application.domain.TravelForexDetails;
import io.vamani.application.domain.TravelLuggageDetails;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 * Spring Data  repository for the TravelLuggageDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TravelLuggageDetailsRepository extends JpaRepository<TravelLuggageDetails, Long> {

	@Query("select travelLuggageDetails from TravelLuggageDetails travelLuggageDetails where travelLuggageDetails.travelApplicationMaster.id =?1")
	List<TravelLuggageDetails> findByTravelApplicationMasterId(Long id);
	
	@Modifying
    @Transactional
    @Query("delete from TravelLuggageDetails travelLuggageDetails where travelLuggageDetails.travelApplicationMaster.id =?1")
	void deleteLuggageById(Long id);

}
