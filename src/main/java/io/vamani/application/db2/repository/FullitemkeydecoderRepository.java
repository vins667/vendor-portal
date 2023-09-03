package io.vamani.application.db2.repository;

import io.vamani.application.db2.domain.Fullitemkeydecoder;
import io.vamani.application.db2.domain.FullitemkeydecoderId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;

@SuppressWarnings("unused")
@Repository
@Transactional("db2TransactionManager")
@PersistenceContext(name = "db2EntityManagerFactory")
public interface FullitemkeydecoderRepository extends JpaRepository<Fullitemkeydecoder, FullitemkeydecoderId> {
    @Query("select fullitemkeydecoder from Fullitemkeydecoder fullitemkeydecoder where fullitemkeydecoder.summarizeddescription like ?1")
    Page<Fullitemkeydecoder> findAllByLongdescriptionIgnoreCaseLike(String longdescription, Pageable pageable);

    @Query(value = "select pi.baseprimaryunitcode from "
        + " (select ist.ITEMTYPECODE, count ctr from ItemSubcodeTemplate ist where ist.type=1 group by ist.ITEMTYPECODE) productcouter,"
        + " product pi where"
        + " productcouter.ITEMTYPECODE = pi.itemtypecode and"
        + " pi.itemtypecode =?1 and ("
        + " (productcouter.ctr = 1 and pi.subcode01 =?2) or"
        + " (productcouter.ctr = 2 and pi.subcode01 =?2 and pi.subcode02 =?3) or"
        + " (productcouter.ctr = 3 and pi.subcode01 =?2 and pi.subcode02 =?3 and pi.subcode03 =?4) or"
        + " (productcouter.ctr = 4 and pi.subcode01 =?2 and pi.subcode02 =?3 and pi.subcode03 =?4 and pi.subcode04 =?5) or"
        + " (productcouter.ctr = 5 and pi.subcode01 =?2 and pi.subcode02 =?3 and pi.subcode03 =?4 and pi.subcode04 =?5 and pi.subcode05 =?6) or"
        + " (productcouter.ctr = 6 and pi.subcode01 =?2 and pi.subcode02 =?3 and pi.subcode03 =?4 and pi.subcode04 =?5 and pi.subcode05 =?6 and pi.subcode06 =?7) or"
        + " (productcouter.ctr = 7 and pi.subcode01 =?2 and pi.subcode02 =?3 and pi.subcode03 =?4 and pi.subcode04 =?5 and pi.subcode05 =?6 and pi.subcode06 =?7 and pi.subcode07 =?8) or"
        + " (productcouter.ctr = 8 and pi.subcode01 =?2 and pi.subcode02 =?3 and pi.subcode03 =?4 and pi.subcode04 =?5 and pi.subcode05 =?6 and pi.subcode06 =?7 and pi.subcode07 =?8 and pi.subcode08 =?9))", nativeQuery = true)
        String fetchUOM(String itemtypecode, String subcode01, String subcode02, String subcode03, String subcode04, String subcode05, String subcode06, String subcode07, String subcode08);

    @Query(value = "select pi.tariffcode from "
        + " (select ist.ITEMTYPECODE, count ctr from ItemSubcodeTemplate ist where ist.type=1 group by ist.ITEMTYPECODE) productcouter,"
        + " productie pi where"
        + " productcouter.ITEMTYPECODE = pi.itemtypecode and"
        + " pi.itemtypecode =?1 and ("
        + " (productcouter.ctr = 1 and pi.subcode01 =?2) or"
        + " (productcouter.ctr = 2 and pi.subcode01 =?2 and pi.subcode02 =?3) or"
        + " (productcouter.ctr = 3 and pi.subcode01 =?2 and pi.subcode02 =?3 and pi.subcode03 =?4) or"
        + " (productcouter.ctr = 4 and pi.subcode01 =?2 and pi.subcode02 =?3 and pi.subcode03 =?4 and pi.subcode04 =?5) or"
        + " (productcouter.ctr = 5 and pi.subcode01 =?2 and pi.subcode02 =?3 and pi.subcode03 =?4 and pi.subcode04 =?5 and pi.subcode05 =?6) or"
        + " (productcouter.ctr = 6 and pi.subcode01 =?2 and pi.subcode02 =?3 and pi.subcode03 =?4 and pi.subcode04 =?5 and pi.subcode05 =?6 and pi.subcode06 =?7) or"
        + " (productcouter.ctr = 7 and pi.subcode01 =?2 and pi.subcode02 =?3 and pi.subcode03 =?4 and pi.subcode04 =?5 and pi.subcode05 =?6 and pi.subcode06 =?7 and pi.subcode07 =?8) or"
        + " (productcouter.ctr = 8 and pi.subcode01 =?2 and pi.subcode02 =?3 and pi.subcode03 =?4 and pi.subcode04 =?5 and pi.subcode05 =?6 and pi.subcode06 =?7 and pi.subcode07 =?8 and pi.subcode08 =?9))", nativeQuery = true)
    String fetchTarrif(String itemtypecode, String subcode01, String subcode02, String subcode03, String subcode04, String subcode05, String subcode06, String subcode07, String subcode08);
}
