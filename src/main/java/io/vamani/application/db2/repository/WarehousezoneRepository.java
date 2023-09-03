package io.vamani.application.db2.repository;

import io.vamani.application.db2.domain.Warehousezone;
import io.vamani.application.db2.domain.WarehousezoneId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;

@SuppressWarnings("unused")
@Repository
@Transactional("db2TransactionManager")
@PersistenceContext(name = "db2EntityManagerFactory")
public interface WarehousezoneRepository extends JpaRepository<Warehousezone, WarehousezoneId> {
    @Query("select max(warehousezone.id.code) from Warehousezone warehousezone where warehousezone.id.physicalwarehousecompanycode = ?1 and warehousezone.id.physicalwarehousecode = ?2")
    String findWarehousezone(String companycode, String physicalwarehousecode);
}
