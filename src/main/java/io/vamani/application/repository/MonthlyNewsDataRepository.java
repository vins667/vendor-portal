package io.vamani.application.repository;

import io.vamani.application.domain.MonthlyNewsData;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MonthlyNewsData entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MonthlyNewsDataRepository extends JpaRepository<MonthlyNewsData, Long> {
	
	@Query("select monthlyNewsData from MonthlyNewsData monthlyNewsData where monthlyNewsData.closedDate is null")
	MonthlyNewsData findAllClosedDate();
}
