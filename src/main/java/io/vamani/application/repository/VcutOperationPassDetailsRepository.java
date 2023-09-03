package io.vamani.application.repository;

import io.vamani.application.domain.VcutOperationPassDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * Spring Data  repository for the VcutOperationMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VcutOperationPassDetailsRepository extends JpaRepository<VcutOperationPassDetails, Long> {
    @Query("select vcutOperationPassDetail from VcutOperationPassDetails vcutOperationPassDetail where vcutOperationPassDetail.cutPlanBundleDetailsId = ?1 and vcutOperationPassDetail.operation = ?2")
    List<VcutOperationPassDetails> findAllByProductIdAndOperation(Long productId, String operationcode);
}
