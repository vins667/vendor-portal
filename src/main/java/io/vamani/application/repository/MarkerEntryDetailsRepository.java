package io.vamani.application.repository;
import io.vamani.application.domain.MarkerEntryDetails;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MarkerEntryDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MarkerEntryDetailsRepository extends JpaRepository<MarkerEntryDetails, Long> {

	@Query("select markerEntryDetails from MarkerEntryDetails markerEntryDetails where markerEntryDetails.markerMasterEntry.id =?1 order by markerEntryDetails.id")
	List<MarkerEntryDetails> findMarkerDetailById(Long id);

}
