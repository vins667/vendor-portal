package io.vamani.application.repository;

import io.vamani.application.domain.DirectBookingEntry;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

/**
 * Spring Data  repository for the DirectBookingEntry entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DirectBookingEntryRepository extends JpaRepository<DirectBookingEntry, Long> {
    // Query Page
    @Query("select directbookingentry from DirectBookingEntry directbookingentry where directbookingentry.flag=?1 and trim(directbookingentry.findocumentcode) = ?2")
    Page<DirectBookingEntry> findAllByFindocumentcode(String flag, String findocumentcode, Pageable pageable);

    @Query("select directbookingentry from DirectBookingEntry directbookingentry where directbookingentry.billno like ?1 and directbookingentry.bookingdate between ?2 and ?3 and directbookingentry.flag=?4 and (directbookingentry.suppliercustomercode like ?5 or directbookingentry.suppliercustomerdesc like ?6) and directbookingentry.company like ?7 and directbookingentry.division like ?8 and directbookingentry.businessunitcode like ?9 and directbookingentry.billdate = ?10 and CAST(directbookingentry.id as text) like ?11")
    Page<DirectBookingEntry> findAllByBillNoLikeAndBilldate(String billno, Instant billDateFrom, Instant billDateTo, String flag, String suppliercustomercode, String suppliercustomerdesc, String company, String division, String businessunitcode, Instant billDate, String id, Pageable pageable);

    @Query("select directbookingentry from DirectBookingEntry directbookingentry where directbookingentry.billno like ?1 and directbookingentry.bookingdate between ?2 and ?3 and directbookingentry.flag=?4 and (directbookingentry.suppliercustomercode like ?5 or directbookingentry.suppliercustomerdesc like ?6) and directbookingentry.company like ?7 and directbookingentry.division like ?8 and directbookingentry.businessunitcode like ?9 and CAST(directbookingentry.id as text) like ?10")
    Page<DirectBookingEntry> findAllByBillNoLike(String billno, Instant billDateFrom, Instant billDateTo, String flag, String suppliercustomercode, String suppliercustomerdesc, String company, String division, String businessunitcode, String id, Pageable pageable);

    @Query("select directbookingentry from DirectBookingEntry directbookingentry where directbookingentry.billno like ?1 and directbookingentry.flag = ?2 and (directbookingentry.suppliercustomercode like ?3 or directbookingentry.suppliercustomerdesc like ?4) and directbookingentry.company like ?5 and directbookingentry.division like ?6 and directbookingentry.businessunitcode like ?7 and directbookingentry.billdate = ?8 and CAST(directbookingentry.id as text) like ?9")
    Page<DirectBookingEntry> findAllByBillNoLikeAndBilldate(String billno, String flag, String suppliercustomercode, String suppliercustomerdesc, String company, String division, String businessunitcode, Instant billDate, String id, Pageable pageable);

    @Query("select directbookingentry from DirectBookingEntry directbookingentry where directbookingentry.billno like ?1 and directbookingentry.flag = ?2 and (directbookingentry.suppliercustomercode like ?3 or directbookingentry.suppliercustomerdesc like ?4) and directbookingentry.company like ?5 and directbookingentry.division like ?6 and directbookingentry.businessunitcode like ?7 and CAST(directbookingentry.id as text) like ?8")
    Page<DirectBookingEntry> findAllByBillNoLike(String billno, String flag, String suppliercustomercode, String suppliercustomerdesc, String company, String division, String businessunitcode, String id, Pageable pageable);

    //Approval Query
    @Query("select directbookingentry from DirectBookingEntry directbookingentry where directbookingentry.billno like ?1 and directbookingentry.flag = ?2 and directbookingentry.bookingdate between ?3 and ?4 and (directbookingentry.suppliercustomercode like ?5 or directbookingentry.suppliercustomerdesc like ?6) and directbookingentry.company like ?7 and directbookingentry.division like ?8 and directbookingentry.businessunitcode like ?9 and directbookingentry.billdate = ?10 and CAST(directbookingentry.id as text) like ?11")
    Page<DirectBookingEntry> findAllByBillNoLikeAndFlagAndBilldate(String billno, String flag, Instant billDateFrom, Instant billDateTo, String suppliercustomercode, String suppliercustomerdesc, String company, String division, String businessunitcode, Instant billDate, String id, Pageable pageable);

    @Query("select directbookingentry from DirectBookingEntry directbookingentry where directbookingentry.billno like ?1 and directbookingentry.flag = ?2 and directbookingentry.bookingdate between ?3 and ?4 and (directbookingentry.suppliercustomercode like ?5 or directbookingentry.suppliercustomerdesc like ?6) and directbookingentry.company like ?7 and directbookingentry.division like ?8 and directbookingentry.businessunitcode like ?9 and CAST(directbookingentry.id as text) like ?10")
    Page<DirectBookingEntry> findAllByBillNoLikeAndFlag(String billno, String flag, Instant billDate, Instant billDateTo, String suppliercustomercode, String suppliercustomerdesc, String company, String division, String businessunitcode, String id, Pageable pageable);

    @Query("select directbookingentry from DirectBookingEntry directbookingentry where directbookingentry.billno like ?1 and directbookingentry.flag = ?2 and (directbookingentry.suppliercustomercode like ?3 or directbookingentry.suppliercustomerdesc like ?4) and directbookingentry.company like ?5 and directbookingentry.division like ?6 and directbookingentry.businessunitcode like ?7 and directbookingentry.billdate = ?8 and CAST(directbookingentry.id as text) like ?9")
    Page<DirectBookingEntry> findAllByBillNoLikeAndFlagAndBilldate(String billno, String flag, String suppliercustomercode, String suppliercustomerdesc, String company, String division, String businessunitcode, Instant billDate, String id, Pageable pageable);

    @Query("select directbookingentry from DirectBookingEntry directbookingentry where directbookingentry.billno like ?1 and directbookingentry.flag = ?2 and (directbookingentry.suppliercustomercode like ?3 or directbookingentry.suppliercustomerdesc like ?4) and directbookingentry.company like ?5 and directbookingentry.division like ?6 and directbookingentry.businessunitcode like ?7 and CAST(directbookingentry.id as text) like ?8")
    Page<DirectBookingEntry> findAllByBillNoLikeAndFlagDt(String billno, String flag, String suppliercustomercode, String suppliercustomerdesc, String company, String division, String businessunitcode, String id, Pageable pageable);

    @Query("select directbookingentry from DirectBookingEntry directbookingentry where directbookingentry.suppliercustomercode = ?1 and upper(directbookingentry.billno) = ?2")
    List<DirectBookingEntry> findAllByBillNo(String suppliercustomercode, String billno);

    @Query("select directbookingentry from DirectBookingEntry directbookingentry where directbookingentry.flag='P' and directbookingentry.findocumentcode is null")
    List<DirectBookingEntry> findAllPostedButCodeNotUpdate();

    @Query(value = "SELECT nextval('findocumentlineseq')", nativeQuery = true)
    Long getNextFinlineseq();
}
