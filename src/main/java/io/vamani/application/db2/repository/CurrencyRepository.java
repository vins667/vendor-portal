package io.vamani.application.db2.repository;

import io.vamani.application.db2.domain.Currency;
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
public interface CurrencyRepository extends JpaRepository<Currency, String> {
    @Override
    @Query("select currency from Currency currency order by currency.longdescription")
    List<Currency> findAll();
}
