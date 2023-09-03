package io.vamani.application.db2.repository;

import io.vamani.application.db2.domain.Orderpartnertds;
import io.vamani.application.db2.domain.OrderpartnertdsId;
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
public interface OrderpartnertdsRepository extends JpaRepository<Orderpartnertds, OrderpartnertdsId> {
    @Query("select orderpartnertds from Orderpartnertds orderpartnertds where orderpartnertds.id.companycode = ?1 and orderpartnertds.id.csmsupcustomersuppliertype = ?2 and orderpartnertds.id.csmsupcustomersuppliercode = ?3 and (orderpartnertds.exemptionnumber IS NULL OR orderpartnertds.exemptionnumber<>'NOT IN USE')")
    List<Orderpartnertds> findAllBySuppliercode(String companycode, String csmsupcustomersuppliertype, String csmsupcustomersuppliercode);
}
