package io.vamani.application.db2.repository;


import io.vamani.application.db2.domain.Orderpartnerie;
import io.vamani.application.db2.domain.OrderpartnerieId;
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
public interface OrderpartnerieRepository extends JpaRepository<Orderpartnerie, OrderpartnerieId> {
    @Modifying
    @Transactional
    @Query(value = "update orderpartnerie set eccno=?1 where customersuppliercompanycode = ?2 and customersuppliertype = ?3 and customersuppliercode = ?4", nativeQuery = true)
    void updateMsmeNumber(String eccno, String customersuppliercompanycode, String customersuppliertype, String customersuppliercode);

    @Modifying
    @Transactional
    @Query(value = "update businesspartner set phonenumber=?1 where numberid = ?2", nativeQuery = true)
    void updatePhone(String phonenumber, Integer numberid);

    @Modifying
    @Transactional
    @Query(value = "update businesspartner set emailaddress=?1 where numberid = ?2", nativeQuery = true)
    void updateEmail(String emailaddress, Integer numberid);
}
