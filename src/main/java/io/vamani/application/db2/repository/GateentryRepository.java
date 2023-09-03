package io.vamani.application.db2.repository;

import io.vamani.application.db2.domain.Gateentry;
import io.vamani.application.db2.domain.GateentryId;
import io.vamani.application.db2.domain.Gateentry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;

@SuppressWarnings("unused")
@Repository
@Transactional("db2TransactionManager")
@PersistenceContext(name = "db2EntityManagerFactory")
public interface GateentryRepository extends JpaRepository<Gateentry, GateentryId> {
    @Query("select gateentry from Gateentry gateentry inner join Usergenericgroup usergenericgroup on gateentry.uggusergengrptypecompanycode = usergenericgroup.id.usergengrouptypecompanycode and gateentry.uggusergenericgrouptypecode = usergenericgroup.id.usergenericgrouptypecode and gateentry.uggcode = usergenericgroup.id.code where usergenericgroup.shortdescription like ?1 and upper(gateentry.id.maingateentrysrno) like ?2")
    Page<Gateentry> findAllByMaingateentrysrnoIgnoreCaseLike(String factorycode, String maingateentrysrno, Pageable pageable);

    @Query("select gateentry from Gateentry gateentry inner join Usergenericgroup usergenericgroup on gateentry.uggusergengrptypecompanycode = usergenericgroup.id.usergengrouptypecompanycode and gateentry.uggusergenericgrouptypecode = usergenericgroup.id.usergenericgrouptypecode and gateentry.uggcode = usergenericgroup.id.code where usergenericgroup.shortdescription like ?1 and upper(gateentry.gatepassno) like ?2")
    Page<Gateentry> findAllByGatepassnoIgnoreCaseLike(String factorycode,String gatepassno, Pageable pageable);
}
