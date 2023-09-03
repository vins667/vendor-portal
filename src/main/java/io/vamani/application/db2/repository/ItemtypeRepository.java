package io.vamani.application.db2.repository;
import java.util.Optional;
import javax.persistence.PersistenceContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import io.vamani.application.db2.domain.Itemtype;
import io.vamani.application.db2.domain.ItemtypeId;

@SuppressWarnings("unused")
@Repository
@Transactional("db2TransactionManager")
@PersistenceContext(name = "db2EntityManagerFactory")
public interface ItemtypeRepository extends JpaRepository<Itemtype, ItemtypeId> {

	 @Query("select itemtype from Itemtype itemtype where itemtype.id.companycode = ?1  and itemtype.id.code like ?2 and upper(itemtype.longdescription) like ?3")
	 Page<Itemtype> findAllByCode(String companycode, String code, String description, Pageable pageable);

	 @Query("select itemtype from Itemtype itemtype where itemtype.id.companycode = ?1  and itemtype.id.code=?2")
	Optional<Itemtype> findByCode(String companycode, String code);

}
