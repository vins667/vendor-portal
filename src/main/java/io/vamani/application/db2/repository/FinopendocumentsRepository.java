package io.vamani.application.db2.repository;

import io.vamani.application.db2.domain.Finopendocuments;
import io.vamani.application.db2.domain.FinopendocumentsId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.math.BigDecimal;

@SuppressWarnings("unused")
@Repository
@Transactional("db2TransactionManager")
@PersistenceContext(name = "db2EntityManagerFactory")
public interface FinopendocumentsRepository extends JpaRepository<Finopendocuments, FinopendocumentsId> {

    @Modifying
    @Transactional
    @Query("update Finopendocuments finopendocuments set finopendocuments.clearedamount=?1 where finopendocuments.id.businessunitcode = ?2 and finopendocuments.id.financialyearcode = ?3 and finopendocuments.id.code = ?4")
    void updateClearedamount(BigDecimal clearedamount, String businessunitcode, Integer financialyearcode, String code);
}
