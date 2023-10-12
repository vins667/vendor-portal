package io.vamani.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import io.vamani.application.domain.StitchCostSubHeadMaster;

@Repository
public interface StitchCostSubHeadMasterRepository extends JpaRepository<StitchCostSubHeadMaster, Long> {

}
