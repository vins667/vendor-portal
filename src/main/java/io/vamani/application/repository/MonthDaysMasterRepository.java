package io.vamani.application.repository;

import io.vamani.application.domain.MonthDaysMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the MonthDaysMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MonthDaysMasterRepository extends JpaRepository<MonthDaysMaster, String> {
}
