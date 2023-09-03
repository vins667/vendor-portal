package io.vamani.application.db2.repository;

import io.vamani.application.db2.domain.Costcenter;
import io.vamani.application.db2.domain.CostcenterId;
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
public interface CostcenterRepository extends JpaRepository<Costcenter, CostcenterId> {
    @Query("select costcenter from Costcenter costcenter where costcenter.longdescription like ?1")
    Page<Costcenter> findAllByLongdescriptionIgnoreCaseLike(String longdescription, Pageable pageable);

    @Query(value = "SELECT NVL(MAX(CODE), '') FROM COSTCENTER CC, ADSTORAGE AD WHERE CC.ABSUNIQUEID = AD.UNIQUEID AND"
    + " NAMEENTITYNAME='CostCenter' AND NAMENAME='Factory' AND FIELDNAME='Factory' AND LONGDESCRIPTION LIKE '%CUTTING%' AND VALUESTRING=?1", nativeQuery = true)
    String findCuttingCostCenter(String plantCode);

    @Query(value = "SELECT NVL(MAX(CODE), '') FROM COSTCENTER CC, ADSTORAGE AD WHERE CC.ABSUNIQUEID = AD.UNIQUEID AND"
        + " NAMEENTITYNAME='CostCenter' AND NAMENAME='Factory' AND FIELDNAME='Factory' AND LONGDESCRIPTION LIKE '%SEWING%' AND VALUESTRING=?1", nativeQuery = true)
    String findSewingCostCenter(String plantCode);

    @Query(value = "SELECT NVL(MAX(CODE), '') FROM COSTCENTER CC, ADSTORAGE AD WHERE CC.ABSUNIQUEID = AD.UNIQUEID AND"
        + " NAMEENTITYNAME='CostCenter' AND NAMENAME='Factory' AND FIELDNAME='Factory' AND LONGDESCRIPTION LIKE '%FINISHING%' AND VALUESTRING=?1", nativeQuery = true)
    String findPackingCostCenter(String plantCode);
}
