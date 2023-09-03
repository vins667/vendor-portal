package io.vamani.application.repository;

import io.vamani.application.domain.DirectBookingTdsDetails;
import io.vamani.application.domain.DirectBookingTdsDetails;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Spring Data  repository for the DirectBookingTdsDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DirectBookingTdsDetailsRepository extends JpaRepository<DirectBookingTdsDetails, Long> {
    @Query("select directBookingTdsDetails from DirectBookingTdsDetails directBookingTdsDetails where directBookingTdsDetails.directBookingEntry.id = ?1 order by directBookingTdsDetails.id")
    List<DirectBookingTdsDetails> findAllByDirectBookingEntry(Long DirectBookingEntryId);

    @Modifying
    @Transactional
    @Query("delete from DirectBookingTdsDetails directBookingTdsDetails where directBookingTdsDetails.directBookingEntry.id = ?1")
    void deleteAllByDirectBookingEntry(Long DirectBookingEntryId);
}
