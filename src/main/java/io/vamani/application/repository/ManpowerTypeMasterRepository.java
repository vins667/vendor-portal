package io.vamani.application.repository;

import io.vamani.application.domain.ManpowerTypeMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the MachineMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ManpowerTypeMasterRepository extends JpaRepository<ManpowerTypeMaster, String> {
}
