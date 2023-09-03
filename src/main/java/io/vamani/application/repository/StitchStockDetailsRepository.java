package io.vamani.application.repository;

import io.vamani.application.domain.StitchStockDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the StitchIssuePackDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StitchStockDetailsRepository extends JpaRepository<StitchStockDetails, Long> {
    @Query("select stitchStockDetails from StitchStockDetails stitchStockDetails where stitchStockDetails.cutPlanBundleId = ?1")
    List<StitchStockDetails> findAllByBundleId(Long bundeId);

    @Query("select stitchStockDetails from StitchStockDetails stitchStockDetails where stitchStockDetails.cutPlanBundleDetailsId = ?1")
    StitchStockDetails findAllByPieceId(Long pieceId);

    @Query("select stitchStockDetails from StitchStockDetails stitchStockDetails where stitchStockDetails.cutPlanBundleDetailsId = ?1")
    List<StitchStockDetails> findAllByPieceIds(Long pieceId);
}
