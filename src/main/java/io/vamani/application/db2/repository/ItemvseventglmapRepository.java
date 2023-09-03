package io.vamani.application.db2.repository;

import io.vamani.application.db2.domain.Itemvseventglmap;
import io.vamani.application.db2.domain.ItemvseventglmapId;
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
public interface ItemvseventglmapRepository extends JpaRepository<Itemvseventglmap, ItemvseventglmapId> {
    @Query("select itemvseventglmap from Itemvseventglmap itemvseventglmap where itemvseventglmap.id.eventcode = ?1 and itemvseventglmap.id.divisioncode = ?2 and itemvseventglmap.id.itemtypecode = ?3 and itemvseventglmap.bookingfor = ?4 and itemvseventglmap.id.document = ?5 and itemvseventglmap.effectivetodate is null")
    List<Itemvseventglmap> findDetailByEventAndItemtype(String eventtype, String division, String itemtype, String bookingFor, String documentType);

    @Query("select itemvseventglmap from Itemvseventglmap itemvseventglmap where itemvseventglmap.id.eventcode = ?1 and itemvseventglmap.id.divisioncode = ?2 and itemvseventglmap.id.itemtypecode = ?3 and itemvseventglmap.id.usergenericgrpnamecode = ?4 and itemvseventglmap.bookingfor = ?5 and itemvseventglmap.id.document = ?6 and itemvseventglmap.effectivetodate is null")
    List<Itemvseventglmap> findDetailByEventAndItemtypeAndSubcode01(String eventtype, String division, String itemtype, String subcode01, String bookingFor, String documentType);
}
