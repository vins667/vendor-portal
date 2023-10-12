package io.vamani.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import io.vamani.application.domain.StitchCostHeadMaster;

@Repository
public interface StitchCostHeadMasterRepository extends JpaRepository<StitchCostHeadMaster, Long> {

}
