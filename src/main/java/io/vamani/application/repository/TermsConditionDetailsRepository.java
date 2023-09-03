package io.vamani.application.repository;
import io.vamani.application.domain.TermsConditionDetails;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TermsConditionDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TermsConditionDetailsRepository extends JpaRepository<TermsConditionDetails, Long> {
	
	 @Query("select termsConditionDetails from TermsConditionDetails termsConditionDetails where termsConditionDetails.termsConditionMaster.id =?1")
	    List<TermsConditionDetails> findAllByTermsConditionMasterId(Long id);

	    @Modifying
	    @Transactional
	    @Query("delete from TermsConditionDetails termsConditionDetails where termsConditionDetails.termsConditionMaster.id =?1")
	    void deleteAllDetailById(Long masterId);


}
