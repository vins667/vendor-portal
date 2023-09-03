package io.vamani.application.repository;

import io.vamani.application.domain.StitchIssuePack;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the StitchIssuePack entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StitchIssuePackRepository extends JpaRepository<StitchIssuePack, Long> {
    @Query("select stitchIssuePack from StitchIssuePack stitchIssuePack where stitchIssuePack.transactionType like ?1 and stitchIssuePack.style like ?2 and stitchIssuePack.color like ?3 and stitchIssuePack.plantCode in (select userPlant.id.plantCode from UserPlant userPlant where userPlant.id.login = ?4)")
    Page<StitchIssuePack> findAllByTypeAndPonoAndStyle(String type, String style, String color, String login, Pageable pageable);

    @Query("select stitchIssuePack from StitchIssuePack stitchIssuePack where stitchIssuePack.postedBy is not null and stitchIssuePack.transactionType like ?1 and stitchIssuePack.style like ?2 and stitchIssuePack.color like ?3 and stitchIssuePack.plantCode in (select userPlant.id.plantCode from UserPlant userPlant where userPlant.id.login = ?4)")
    Page<StitchIssuePack> findAllByTypeAndPonoAndStylePostedOnly(String type, String style, String color, String login, Pageable pageable);
}
