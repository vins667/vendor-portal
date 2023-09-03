package io.vamani.application.db2.repository;

import io.vamani.application.db2.domain.Logicalwarehouse;
import io.vamani.application.db2.domain.LogicalwarehouseId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;

@SuppressWarnings("unused")
@Repository
@Transactional("db2TransactionManager")
@PersistenceContext(name = "db2EntityManagerFactory")
public interface LogicalwarehouseRepository extends JpaRepository<Logicalwarehouse, LogicalwarehouseId> {
    @Query("select logicalwarehouse from Logicalwarehouse logicalwarehouse where logicalwarehouse.plantcode = ?1 and logicalwarehouse.id.code like '%CPTTR'")
    Logicalwarehouse findInTransitWarehouseByPlantCode(String plantcode);

    @Query("select logicalwarehouse from Logicalwarehouse logicalwarehouse where logicalwarehouse.plantcode = ?1 and logicalwarehouse.id.code like '%HFCTR'")
    Logicalwarehouse findInTransitHFCWarehouseByPlantCode(String plantcode);

    @Query("select logicalwarehouse from Logicalwarehouse logicalwarehouse where logicalwarehouse.plantcode = ?1 and logicalwarehouse.id.code like '%SEGTR'")
    Logicalwarehouse findInTransitSEGWarehouseByPlantCode(String plantcode);

    @Query("select logicalwarehouse from Logicalwarehouse logicalwarehouse where logicalwarehouse.plantcode = ?1 and logicalwarehouse.id.code like '%HFSTR'")
    Logicalwarehouse findInTransitHFSEGWarehouseByPlantCode(String plantcode);

    @Query("select logicalwarehouse from Logicalwarehouse logicalwarehouse where logicalwarehouse.plantcode = ?1 and logicalwarehouse.id.code like '%CPT01'")
    Logicalwarehouse findInTransitCUTWarehouseByPlantCode(String plantcode);

    @Query("select logicalwarehouse from Logicalwarehouse logicalwarehouse where logicalwarehouse.plantcode = ?1 and logicalwarehouse.id.code like '%HFC01'")
    Logicalwarehouse findInTransitHFCUTWarehouseByPlantCode(String plantcode);
}
