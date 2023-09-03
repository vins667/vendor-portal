package io.vamani.application.db2.repository;

import io.vamani.application.db2.domain.Resources;
import io.vamani.application.db2.domain.ResourcesId;
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
public interface ResourcesRepository extends JpaRepository<Resources, ResourcesId> {
    @Query("select resources from Workcenterdetail workcenterdetail"
        +" inner join Workcenter workcenter on workcenterdetail.id.workcentercode = workcenter.id.code inner join Resources resources on"
        +" workcenterdetail.id.mainresourcecode=resources.id.code where  resources.type = '2' and"
        +" workcenter.plantcode =?1 and workcenter.id.code like '%CT00%'")
    List<Resources> findAllResourcesByPlantcode(String plantcode);

    @Query("select resources from Workcenterdetail workcenterdetail"
        +" inner join Workcenter workcenter on workcenterdetail.id.workcentercode = workcenter.id.code inner join Resources resources on"
        +" workcenterdetail.id.mainresourcecode=resources.id.code where  resources.type = '2' and"
        +" workcenter.plantcode =?1 and workcenter.id.code like '%SL%' and workcenter.id.code not like '%SLR&D%'")
    List<Resources> findAllStitchResourcesByPlantcode(String plantcode);

    @Query("select resources from Workcenterdetail workcenterdetail"
        +" inner join Workcenter workcenter on workcenterdetail.id.workcentercode = workcenter.id.code inner join Resources resources on"
        +" workcenterdetail.id.mainresourcecode=resources.id.code where  resources.type = '2' and"
        +" workcenter.plantcode =?1 and workcenter.id.code like '%PAK%' and workcenter.id.code not like '%SLR&D%'")
    List<Resources> findAllPackingResourcesByPlantcode(String plantcode);

    @Query("select resources from Workcenterdetail workcenterdetail"
        +" inner join Workcenter workcenter on workcenterdetail.id.workcentercode = workcenter.id.code inner join Resources resources on"
        +" workcenterdetail.id.mainresourcecode=resources.id.code where  resources.type = '2' and"
        +" workcenter.plantcode =?1 and workcenter.id.code like '%FIN%' and resources.longdescription like '%UNIT%' and workcenter.id.code not like '%SLR&D%'")
    List<Resources> findAllFinishResourcesByPlantcode(String plantcode);


    @Query("select workcenter.costcentercode from Workcenterdetail workcenterdetail"
        +" inner join Workcenter workcenter on workcenterdetail.id.workcentercode = workcenter.id.code"
        +" where  workcenterdetail.id.mainresourcecode =?1")
    String findCostcenterByResourcecode(String resourceCode);


    @Query("select max(workcenterdetail.id.workcentercode) from Workcenterdetail workcenterdetail"
        +" where  workcenterdetail.id.mainresourcecode =?1")
    String findWorkcentercodeByResourcecode(String resourceCode);
}
