package io.vamani.application.db2.repository;

import io.vamani.application.db2.domain.Orderpartnerbank;
import io.vamani.application.db2.domain.OrderpartnerbankId;
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
public interface OrderpartnerbankRepository extends JpaRepository<Orderpartnerbank, OrderpartnerbankId> {
    @Query("select orderpartnerbank from Orderpartnerbank orderpartnerbank where orderpartnerbank.id.ordprncsmsuppliercompanycode = ?1 and orderpartnerbank.id.ordprncustomersuppliertype = ?2 and orderpartnerbank.id.ordprncustomersuppliercode = ?3")
    List<Orderpartnerbank> findAllBySuppliercode(String companycode, String csmsupcustomersuppliertype, String csmsupcustomersuppliercode);
}
