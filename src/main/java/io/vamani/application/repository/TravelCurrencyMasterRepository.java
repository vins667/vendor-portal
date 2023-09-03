package io.vamani.application.repository;
import io.vamani.application.domain.TravelCurrencyMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TravelCurrencyMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TravelCurrencyMasterRepository extends JpaRepository<TravelCurrencyMaster, Long> {

}
