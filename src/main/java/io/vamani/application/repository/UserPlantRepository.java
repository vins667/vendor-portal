package io.vamani.application.repository;

import io.vamani.application.domain.User;
import io.vamani.application.domain.UserPlant;
import io.vamani.application.domain.UserPlantId;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import javax.transaction.Transactional;

/**
 * Spring Data JPA repository for the {@link User} entity.
 */
@Repository
public interface UserPlantRepository extends JpaRepository<UserPlant, UserPlantId> {

	@Query("select userplant from UserPlant userplant where userplant.id.login = ?1 order by userplant.id.plantCode")
	List<UserPlant> findAllByLogin(String login);

	@Query("select userplant from UserPlant userplant where userplant.id.login = ?1 and userplant.id.plantCode = ?2 order by userplant.id.plantCode")
	List<UserPlant> findAllByLoginAndPlantCode(String login, String plantCode);

    @Query("select userplant from UserPlant userplant where userplant.id.login = ?1 and userplant.id.plantCode <> ?2 order by userplant.id.plantCode")
    List<UserPlant> findAllByLoginAndNotPlantCode(String login, String plantCode);

	@Modifying
	@Transactional
	@Query("delete from UserPlant userPlant where userPlant.id.login = ?1")
	void deleteAllLogins(String login);


	@Modifying
	@Transactional
	@Query("delete from UserPlant userPlant where userPlant.id.login = ?1 and userPlant.id.plantCode = ?2 ")
	void deleteByLoginAndPlantCode(String login, String ids);

	@Query("select userplant from UserPlant userplant ")
	List<UserPlant> getAllPlantList();

}
