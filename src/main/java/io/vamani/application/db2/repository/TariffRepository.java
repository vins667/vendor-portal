package io.vamani.application.db2.repository;

import javax.persistence.PersistenceContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import io.vamani.application.db2.domain.Tariff;

@SuppressWarnings("unused")
@Repository
@Transactional("db2TransactionManager")
@PersistenceContext(name = "db2EntityManagerFactory")
public interface TariffRepository extends JpaRepository<Tariff, String>{
 @Query("select tariff from Tariff tariff where tariff.code like ?1")
 Page<Tariff> findAllTariff(String code, Pageable page);
}
