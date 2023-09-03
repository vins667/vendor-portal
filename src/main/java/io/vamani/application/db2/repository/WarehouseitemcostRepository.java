package io.vamani.application.db2.repository;

import io.vamani.application.db2.domain.Warehouseitemcost;
import io.vamani.application.db2.domain.WarehouseitemcostId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.math.BigInteger;
import java.util.List;

@SuppressWarnings("unused")
@Repository
@Transactional("db2TransactionManager")
@PersistenceContext(name = "db2EntityManagerFactory")
public interface WarehouseitemcostRepository extends JpaRepository<Warehouseitemcost, WarehouseitemcostId> {
    @Query("select warehouseitemcost from Warehouseitemcost warehouseitemcost where warehouseitemcost.id.companycode = ?1 and warehouseitemcost.id.warehouseaccountinggroupcode = ?2 and warehouseitemcost.id.itemtypeaficode = ?3 and warehouseitemcost.id.subcode01 = ?4 and warehouseitemcost.id.subcode02 = ?5 and warehouseitemcost.id.subcode03 = ?6 and warehouseitemcost.id.subcode04 = ?7 and warehouseitemcost.id.subcode05 = ?8 and warehouseitemcost.id.subcode06 = ?9 and warehouseitemcost.id.subcode07 = ?10 and warehouseitemcost.id.subcode08 = ?11 and warehouseitemcost.id.subcode09 = ?12 and warehouseitemcost.id.subcode10 = ?13  and warehouseitemcost.id.qualitylevelcode = ?14")
    List<Warehouseitemcost> findByAccgroupcodeAndItemcode(String companycode, String warehouseaccountinggroupcode, String itemtypeaficode, String subcode01, String subcode02, String subcode03, String subcode04, String subcode05, String subcode06, String subcode07, String subcode08, String subcode09, String subcode10, BigInteger qualitylevelcode);
}
