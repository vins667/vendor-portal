package io.vamani.application.db2.repository;

import io.vamani.application.db2.domain.Vieworderpartner;
import io.vamani.application.db2.model.TdsDetailBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;

@SuppressWarnings("unused")
@Repository
@Transactional("db2TransactionManager")
@PersistenceContext(name = "db2EntityManagerFactory")
public interface VieworderpartnerRepository extends JpaRepository<Vieworderpartner, String> {
    @Query("select vieworderpartner from Vieworderpartner vieworderpartner where vieworderpartner.customersuppliertype = ?1 and vieworderpartner.legalname1 like ?2")
    Page<Vieworderpartner> findAllByLegalname1IgnoreCaseLike(String customersuppliertype, String legalname1, Pageable pageable);

    @Query(value = "select max(ag.statecode) from businesspartner bp, addressgst ag where bp.absuniqueid = ag.uniqueid and bp.numberid=?1", nativeQuery = true)
    String getStateCode(Long numberId);

    @Query(value = "SELECT NVL(OI.ECCNO, '') MSME_NO, NVL(ADR1.VALUESTRING, '') MONTHR1VALUE, NVL(AD3B.VALUESTRING, '') MONTH3BVALUE"
       + " FROM VIEWORDERPARTNER OP"
       + " LEFT OUTER JOIN ADSTORAGE ADR1 ON ADR1.UNIQUEID = OP.ABSUNIQUEID AND ADR1.NAMEENTITYNAME='OrderPartner' and ADR1.NAMENAME='gstrR1FiledMonth' AND ADR1.FIELDNAME='gstrR1FiledMonth'"
        + " LEFT OUTER JOIN ADSTORAGE AD3B ON AD3B.UNIQUEID = OP.ABSUNIQUEID AND AD3B.NAMEENTITYNAME='OrderPartner' and AD3B.NAMENAME='gstr3BFiledMonth' AND AD3B.FIELDNAME='gstr3BFiledMonth', ORDERPARTNERIE OI"
        + " WHERE OP.CUSTOMERSUPPLIERCOMPANYCODE = OI.CUSTOMERSUPPLIERCOMPANYCODE AND OP.CUSTOMERSUPPLIERTYPE = OI.CUSTOMERSUPPLIERTYPE AND OP.CUSTOMERSUPPLIERCODE = OI.CUSTOMERSUPPLIERCODE AND OP.CUSTOMERSUPPLIERCODE = ?1",nativeQuery = true )
    List<Object []> getOrderPartnerDetails(String supplierCode);

    @Query(value = "SELECT OT.TDSCODE, OT.TDSITAXCODE, IT.VALUE FROM ORDERPARTNERTDS OT, ITAX IT WHERE OT.COMPANYCODE = IT.COMPANYCODE AND IT.TAXCATEGORYCODE='TDS' AND OT.TDSITAXCODE = IT.CODE AND OT.CSMSUPCUSTOMERSUPPLIERCODE = ?1",nativeQuery = true)
    List<Object []> getTdsDetails(String customerSupplierCode);

    @Query(value = "SELECT VDP.PAYMENTMETHODCODE, VDP.PAYMENTMETHODDESCRIPTION, VDP.POBASICVALUE, VDP.POGSTVALUE FROM VIEW_DIPURCHASEORDERDETAILS VDP WHERE VDP.COMPANYCODE='100' AND VDP.CODE = ?1", nativeQuery = true)
    List<Object []> getPoDetail(String puchaseorder);

    @Query(value = "SELECT COALESCE(PURCHASEEXCHANGERATE , 0) PURCHASEEXCHANGERATE FROM CURRENCYDAILYEXCHANGERATE WHERE CURRENCYCODE='INR' AND REFERENCEDCURRENCYCODE=?1 ORDER BY INITIALDATE DESC FETCH FIRST ROW ONLY", nativeQuery = true)
    BigDecimal fetchExchangeRate(String currencycode);

    @Query("select coalesce(vieworderpartner.customersuppliercompanycode, ''), coalesce(vieworderpartner.customersuppliertype, ''), coalesce(vieworderpartner.customersuppliercode, ''), coalesce(vieworderpartner.legalname1, ''), coalesce(orderpartnerie.commissionerate, ''), coalesce(orderpartnerie.eccno, ''), coalesce(addressgst.gstinnumber, '') from Vieworderpartner vieworderpartner"
        + " inner join Orderpartnerie orderpartnerie on vieworderpartner.customersuppliercompanycode = orderpartnerie.id.customersuppliercompanycode and vieworderpartner.customersuppliertype = orderpartnerie.id.customersuppliertype and vieworderpartner.customersuppliercode = orderpartnerie.id.customersuppliercode"
        + " left outer join Addressgst addressgst on addressgst.uniqueid = vieworderpartner.bpabsuniqueid"
        + " where vieworderpartner.customersuppliertype = ?1 and vieworderpartner.customersuppliercode like ?2 and vieworderpartner.legalname1 like ?3")
    Page<Object[]> findAllByOrderPartnerLikeAndLegalname1Like(String customersuppliertype, String code, String legalname1, Pageable pageable);

    @Query("select coalesce(vieworderpartner.customersuppliercompanycode, ''), coalesce(vieworderpartner.customersuppliertype, ''), coalesce(vieworderpartner.customersuppliercode, ''),"
        + " coalesce(vieworderpartner.legalname1, ''), coalesce(orderpartnerie.commissionerate, ''), coalesce(orderpartnerie.eccno, ''), coalesce(addressgst.gstinnumber, ''), "
        + " coalesce(orderpartnerie.glcode, ''), coalesce(glmaster.longdescription, ''), coalesce(adstorage.valuestring, ''), coalesce(adstorage1.valuestring, ''), "
        + " coalesce(adstorage2.valuestring, ''), coalesce(adstorage3.valuestring, ''), coalesce(vieworderpartner.emailaddress, ''), coalesce(vieworderpartner.phonenumber, '')"
        + " from Vieworderpartner vieworderpartner"
        + " inner join Orderpartnerie orderpartnerie on vieworderpartner.customersuppliercompanycode = orderpartnerie.id.customersuppliercompanycode and vieworderpartner.customersuppliertype = orderpartnerie.id.customersuppliertype and vieworderpartner.customersuppliercode = orderpartnerie.id.customersuppliercode"
        + " left outer join Glmaster glmaster on orderpartnerie.glcompanycode = glmaster.id.companycode and orderpartnerie.glcode = glmaster.id.code"
        + " left outer join Addressgst addressgst on addressgst.uniqueid = vieworderpartner.bpabsuniqueid"
        + " left outer join Adstorage adstorage on adstorage.id.uniqueid = vieworderpartner.absuniqueid and adstorage.id.nameentityname = 'OrderPartner' and adstorage.id.namename='gstr3BFiledMonth' and adstorage.id.fieldname = 'gstr3BFiledMonth'"
        + " left outer join Adstorage adstorage1 on adstorage1.id.uniqueid = vieworderpartner.absuniqueid and adstorage1.id.nameentityname = 'OrderPartner' and adstorage1.id.namename='gstrR1FiledMonth' and adstorage1.id.fieldname = 'gstrR1FiledMonth'"
        + " left outer join Adstorage adstorage2 on adstorage2.id.uniqueid = vieworderpartner.absuniqueid and adstorage2.id.nameentityname = 'OrderPartner' and adstorage2.id.namename='gstr2AMonth' and adstorage2.id.fieldname = 'gstr2AMonth'"
        + " left outer join Adstorage adstorage3 on adstorage3.id.uniqueid = vieworderpartner.absuniqueid and adstorage3.id.nameentityname = 'OrderPartner' and adstorage3.id.namename='gstr2ARemark' and adstorage3.id.fieldname = 'gstr2ARemark'"
        + " where vieworderpartner.customersuppliercompanycode = ?1 and vieworderpartner.customersuppliertype = ?2 and vieworderpartner.customersuppliercode = ?3")
    List<Object[]> findAllByOrderPartner(String customersuppliercompanycode, String customersuppliertype, String customersuppliercode);

    @Query("select vieworderpartner from Vieworderpartner vieworderpartner where vieworderpartner.customersuppliercompanycode = ?1 and vieworderpartner.customersuppliertype = ?2 and vieworderpartner.customersuppliercode = ?3")
    Vieworderpartner findByCustomersuppliercode(String customersuppliercompanycode, String customersuppliertype, String customersuppliercode);

    @Query("select coalesce(vieworderpartner.customersuppliercompanycode, ''), coalesce(vieworderpartner.customersuppliertype, ''), coalesce(vieworderpartner.customersuppliercode, ''), coalesce(vieworderpartner.legalname1, ''), coalesce(orderpartnerie.commissionerate, ''), coalesce(orderpartnerie.eccno, ''), coalesce(addressgst.gstinnumber, ''),"
        + " coalesce(orderpartnerie.glcode, ''), coalesce(glmaster.longdescription, ''), coalesce(adstorage.valuestring, ''), coalesce(adstorage1.valuestring, ''), "
        + " coalesce(adstorage2.valuestring, ''), coalesce(adstorage3.valuestring, ''), coalesce(vieworderpartner.emailaddress, ''), coalesce(vieworderpartner.phonenumber, '')"
        + " from Vieworderpartner vieworderpartner"
        + " inner join Orderpartnerie orderpartnerie on vieworderpartner.customersuppliercompanycode = orderpartnerie.id.customersuppliercompanycode and vieworderpartner.customersuppliertype = orderpartnerie.id.customersuppliertype and vieworderpartner.customersuppliercode = orderpartnerie.id.customersuppliercode"
        + " left outer join Glmaster glmaster on orderpartnerie.glcompanycode = glmaster.id.companycode and orderpartnerie.glcode = glmaster.id.code"
        + " left outer join Addressgst addressgst on addressgst.uniqueid = vieworderpartner.bpabsuniqueid"
        + " left outer join Adstorage adstorage on adstorage.id.uniqueid = vieworderpartner.absuniqueid and adstorage.id.nameentityname = 'OrderPartner' and adstorage.id.namename='gstr3BFiledMonth' and adstorage.id.fieldname = 'gstr3BFiledMonth'"
        + " left outer join Adstorage adstorage1 on adstorage1.id.uniqueid = vieworderpartner.absuniqueid and adstorage1.id.nameentityname = 'OrderPartner' and adstorage1.id.namename='gstrR1FiledMonth' and adstorage1.id.fieldname = 'gstrR1FiledMonth'"
        + " left outer join Adstorage adstorage2 on adstorage2.id.uniqueid = vieworderpartner.absuniqueid and adstorage2.id.nameentityname = 'OrderPartner' and adstorage2.id.namename='gstr2AMonth' and adstorage2.id.fieldname = 'gstr2AMonth'"
        + " left outer join Adstorage adstorage3 on adstorage3.id.uniqueid = vieworderpartner.absuniqueid and adstorage3.id.nameentityname = 'OrderPartner' and adstorage3.id.namename='gstr2ARemark' and adstorage3.id.fieldname = 'gstr2ARemark'"
        + " where vieworderpartner.customersuppliertype = ?1 order by 1,2, 3")
    List<Object[]> findAllByCustomersuppliertype(String customersuppliertype);
}
