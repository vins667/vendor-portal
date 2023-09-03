package io.vamani.application.repository;

import io.vamani.application.domain.DirectBookingDetails;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Spring Data  repository for the DirectBookingDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DirectBookingDetailsRepository extends JpaRepository<DirectBookingDetails, Long> {
    @Query("select directbookingdetails from DirectBookingDetails directbookingdetails where directbookingdetails.directBookingEntry.id = ?1 order by directbookingdetails.id")
    List<DirectBookingDetails> findAllByDirectBookingEntry(Long DirectBookingEntryId);

    @Modifying
    @Transactional
    @Query("delete from DirectBookingDetails directbookingdetails where directbookingdetails.directBookingEntry.id = ?1")
    void deleteAllByDirectBookingEntry(Long DirectBookingEntryId);
}
