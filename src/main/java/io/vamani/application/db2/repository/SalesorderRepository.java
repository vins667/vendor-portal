package io.vamani.application.db2.repository;

import io.vamani.application.db2.domain.Salesorder;
import io.vamani.application.db2.domain.SalesorderId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;

@SuppressWarnings("unused")
@Repository
@Transactional("db2TransactionManager")
@PersistenceContext(name = "db2EntityManagerFactory")
public interface SalesorderRepository extends JpaRepository<Salesorder, SalesorderId> {

    @Modifying
    @Transactional
    @Query("update Salesorder salesorder set salesorder.currentstatus='2' where salesorder.id.code = ?1")
    void updateSalesOrderStatus(String salesordercode);

}
