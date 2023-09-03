package io.vamani.application.repository;

import io.vamani.application.domain.BankMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the BankMaster entity.
 */
@Repository
public interface BankMasterRepository extends JpaRepository<BankMaster, Long> {

    @Query("select bankMaster from BankMaster bankMaster order by bankMaster.bankName")
    List<BankMaster> orderedAll();
}
