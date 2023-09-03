package io.vamani.application.repository;

import io.vamani.application.domain.MenuAccessMaster;
import io.vamani.application.domain.MenuMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;


/**
 * Spring Data  repository for the MenuAccessMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MenuAccessMasterRepository extends JpaRepository<MenuAccessMaster, Long> {
    @Query("select menuAccessMaster from MenuAccessMaster menuAccessMaster where menuAccessMaster.authorityName=?1 order by menuAccessMaster.menuMaster.id")
    List<MenuAccessMaster> findByAuthorityName(String authorityName);

    @Query("select menuMaster from MenuAccessMaster menuAccessMaster inner join MenuMaster menuMaster on menuAccessMaster.menuMaster.id = menuMaster.id where menuAccessMaster.authorityName in(?1) order by menuMaster.orderBy, menuMaster.id")
    List<MenuMaster> findAllMenuByAuthorityName(Set<String> authorityName);

    @Query("select menuAccessMaster from MenuAccessMaster menuAccessMaster where menuAccessMaster.authorityName in(?1) and coalesce(menuAccessMaster.menuMaster.mobileAccess, 'N')='Y' order by menuAccessMaster.menuMaster.orderBy, menuAccessMaster.menuMaster.id")
    List<MenuAccessMaster> findAllByAuthorityNameAndMobile(Set<String> authorityName);

    @Query("select menuAccessMaster from MenuAccessMaster menuAccessMaster where menuAccessMaster.authorityName=?1 and menuAccessMaster.menuMaster.id=?2 ")
    List<MenuAccessMaster> findByAuthorityNameAndMenuId(String authorityName, Long menuId);

    @Modifying
    @Transactional
    @Query("delete from MenuAccessMaster menuAccessMaster where menuAccessMaster.authorityName=?1")
    void deleteByAuthorityName(String authorityName);

}
