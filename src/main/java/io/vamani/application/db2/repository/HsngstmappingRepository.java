package io.vamani.application.db2.repository;

import io.vamani.application.db2.domain.Hsngstmapping;
import io.vamani.application.db2.domain.HsngstmappingId;
import io.vamani.application.db2.domain.ViewDitaxglmapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.util.List;

@SuppressWarnings("unused")
@Repository
@Transactional("db2TransactionManager")
@PersistenceContext(name = "db2EntityManagerFactory")
public interface HsngstmappingRepository extends JpaRepository<Hsngstmapping, HsngstmappingId> {
    @Query("select viewDitaxglmapping from Hsngstmapping hsngstmapping, Taxtemplatedetail taxtemplatedetail, ViewDitaxglmapping viewDitaxglmapping"
    +" where hsngstmapping.id.sgsttaxtemplatecode=taxtemplatedetail.id.taxtemplateheadercode and taxtemplatedetail.id.itaxcode=viewDitaxglmapping.id.taxcode and hsngstmapping.id.modulename=?1"
    +" and hsngstmapping.id.tarrifcode=?2 and (taxtemplatedetail.id.itaxcode like 'SG%' OR taxtemplatedetail.id.itaxcode LIKE 'CG%')")
    List<ViewDitaxglmapping> findAllCgstAndSgst(String modulename, String tarrifcode);

    @Query("select viewDitaxglmapping from Hsngstmapping hsngstmapping, Taxtemplatedetail taxtemplatedetail, ViewDitaxglmapping viewDitaxglmapping"
        +" where hsngstmapping.id.sgsttaxtemplatecode=taxtemplatedetail.id.taxtemplateheadercode and taxtemplatedetail.id.itaxcode=viewDitaxglmapping.id.taxcode and hsngstmapping.id.modulename=?1"
        +" and hsngstmapping.id.tarrifcode=?2 and taxtemplatedetail.id.itaxcode like 'IG%'")
    List<ViewDitaxglmapping> findAllIgst(String modulename, String tarrifcode);
}
