package io.vamani.application.repository;

import io.vamani.application.domain.MenuMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the MenuMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MenuMasterRepository extends JpaRepository<MenuMaster, Long> {
    @Query("select menuMaster from MenuMaster menuMaster where menuMaster.id not in (select menuAccessMaster.menuMaster.id from MenuAccessMaster menuAccessMaster where menuAccessMaster.authorityName='ROLE_USER') order by menuMaster.orderBy, menuMaster.id")
    List<MenuMaster> findAllByNonDefault();

    @Query("select menuMaster from MenuMaster menuMaster where menuMaster.qlikType = ?1 and menuMaster.menuLink = ?2")
    MenuMaster findByTypeAndMenuLink(String qlikType, String menuLink);
}
