package io.vamani.application.db2.repository;

import io.vamani.application.db2.domain.Stocktransaction;
import io.vamani.application.db2.domain.StocktransactionId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
public interface StocktransactionRepository extends JpaRepository<Stocktransaction, StocktransactionId> {
    @Query("select stocktransaction from Stocktransaction stocktransaction inner join Adstorage adstorage on stocktransaction.absuniqueid = adstorage.id.uniqueid and adstorage.id.nameentityname='StockTransaction' and adstorage.id.namename='InterfaceBundleNumber' and adstorage.id.fieldname='InterfaceBundleNumber' where stocktransaction.id.companycode = ?1 and stocktransaction.productionordercode = ?2 and stocktransaction.ordercountercode = ?3 and stocktransaction.ordercode = ?4 and adstorage.valuestring = ?5")
    List<Stocktransaction> findByProductionOrderAndDemandAndBundle(String companycode, String productionordercode, String demandcountercode, String demandcode, String bundleNo);

    @Query("select stocktransaction from Stocktransaction stocktransaction inner join Adstorage adstorage on stocktransaction.absuniqueid = adstorage.id.uniqueid and adstorage.id.nameentityname='StockTransaction' and adstorage.id.namename='InterfaceBundleNumber' and adstorage.id.fieldname='InterfaceBundleNumber' where stocktransaction.id.companycode = ?1 and stocktransaction.productionordercode = ?2 and stocktransaction.ordercountercode = ?3 and stocktransaction.ordercode = ?4 and adstorage.valuestring = ?5 and stocktransaction.logicalwarehousecode = ?6")
    List<Stocktransaction> findByProductionOrderAndDemandAndBundleAndWarehouse(String companycode, String productionordercode, String demandcountercode, String demandcode, String bundleNo, String warehouse);

    @Query("select stocktransaction from Stocktransaction stocktransaction inner join Adstorage adstorage on"
        + " stocktransaction.absuniqueid=adstorage.id.uniqueid and adstorage.id.nameentityname='StockTransaction' and"
        + " adstorage.id.namename='OriginalElementCode' and adstorage.id.fieldname='OriginalElementCode' and adstorage.valuestring=?1")
    Page<Stocktransaction> findAllByElementCode(String elementscode, Pageable pageable);
}
