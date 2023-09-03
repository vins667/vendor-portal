package io.vamani.application.db2.repository;

import io.vamani.application.db2.domain.Warehouselocation;
import io.vamani.application.db2.domain.WarehouselocationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;

@SuppressWarnings("unused")
@Repository
@Transactional("db2TransactionManager")
@PersistenceContext(name = "db2EntityManagerFactory")
public interface WarehouselocationRepository extends JpaRepository<Warehouselocation, WarehouselocationId> {
    @Query("select min(warehouselocation.id.code) from Warehouselocation warehouselocation where warehouselocation.id.whszonephywhscompanycode = ?1 and warehouselocation.id.whszonephysicalwarehousecode = ?2 and warehouselocation.id.warehousezonecode = ?3")
    String fetchWarehouselocation(String whszonephywhscompanycode, String whszonephysicalwarehousecode, String warehousezonecode);
}
