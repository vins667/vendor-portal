package io.vamani.application.db2.repository;

import io.vamani.application.db2.domain.DebitNoteEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional("db2TransactionManager")
@PersistenceContext(name = "db2EntityManagerFactory")
public interface DebitNoteEntryRepository  extends JpaRepository<DebitNoteEntry, Long> {

    @Query("select debitNoteEntry from DebitNoteEntry debitNoteEntry where upper(debitNoteEntry.companycode)=?1 AND upper(debitNoteEntry.businessunitcode)=?2 AND upper(debitNoteEntry.financialyearcode)=?3 AND upper(debitNoteEntry.documenttemplatecode)=?4 AND upper(debitNoteEntry.code)=?5 ")
    DebitNoteEntry findByCode(String companycode, String businessunit, String finyearcode, String documenttemplatecode, String code);

    @Query("select debitNoteEntry from DebitNoteEntry debitNoteEntry where upper(debitNoteEntry.companycode)=?1 AND upper(debitNoteEntry.businessunitcode)=?2 AND upper(debitNoteEntry.financialyearcode)=?3 AND upper(debitNoteEntry.documenttemplatecode)=?4 AND upper(debitNoteEntry.code)=?5 ")
    List<DebitNoteEntry> findAllDebitNoteByCode(String companycode, String businessunitcode, String financialyearcode, String documenttemplatecode, String code);
}
