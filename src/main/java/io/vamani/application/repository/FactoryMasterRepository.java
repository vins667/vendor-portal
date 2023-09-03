package io.vamani.application.repository;

import io.vamani.application.domain.FactoryMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.util.List;


/**
 * Spring Data  repository for the FactoryMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FactoryMasterRepository extends JpaRepository<FactoryMaster, Long> {
    @Query("select factoryMaster from FactoryMaster factoryMaster where factoryMaster.nowFactoryCode is not null order by factoryMaster.factoryName")
    List<FactoryMaster> findAllNowFactory();
    @Query("select factoryMaster from FactoryMaster factoryMaster where factoryMaster.groupCode in(?1) order by factoryMaster.factoryName")
    List<FactoryMaster> findAllByGroup(List<String> groups);
    @Query("select distinct factoryMaster.groupCode from FactoryMaster factoryMaster order by factoryMaster.groupCode")
    List<String> findAllGroupCode();
    @Query("select distinct factoryMaster.factoryCode from FactoryMaster factoryMaster where factoryMaster.groupCode in(?1)")
    List<String> findAllFactoryAndGroupCode(List<String> groupCodes);

    @Override
    @Query("select factoryMaster from FactoryMaster factoryMaster order by factoryMaster.factoryName")
    List<FactoryMaster> findAll();
}
