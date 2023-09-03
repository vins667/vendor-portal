package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.web.util.ResponseUtil;
import io.vamani.application.db2.domain.Factory;
import io.vamani.application.db2.repository.FactoryRepository;
import io.vamani.application.domain.UserPlant;
import io.vamani.application.domain.UserPlantId;
import io.vamani.application.model.Master;
import io.vamani.application.model.UserPlantBean;
import io.vamani.application.model.UserPlantDetailsBean;
import io.vamani.application.repository.UserPlantRepository;
import io.vamani.application.security.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserPlantResource {
    private final Logger log = LoggerFactory.getLogger(UserPlantResource.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;


    private static final String ENTITY_NAME = "userPlant";

    private final UserPlantRepository userPlantRepository;

    @Autowired
    private FactoryRepository factoryRepository;

    public UserPlantResource(UserPlantRepository userPlantRepository) {
        this.userPlantRepository = userPlantRepository;
    }

    @GetMapping("/user-plants")
    public List<UserPlant> getAllUserPlantsAll() {
        return userPlantRepository.findAllByLogin(SecurityUtils.getCurrentUserLogin().orElse(null));
    }

    /**
     * {@code GET /users} : get all users.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body all users.
     */
    @GetMapping("/user-plants-new")
    public List<UserPlantBean> getAllUserPlants() {
    	List<UserPlantBean> userPlantBean = new ArrayList<UserPlantBean>();
    	List<UserPlant> userPlant = userPlantRepository.getAllPlantList();
    	//Page<UserPlant> page = userPlantRepository.findAll(Pageable);

    	for(UserPlant bean:  userPlant) {
    		UserPlantBean userBean = new UserPlantBean();
    		List<UserPlantDetailsBean> lineBeanlist = new ArrayList<UserPlantDetailsBean>();
    		UserPlantDetailsBean detailBean = new UserPlantDetailsBean();
    		userBean.setLogin(bean.getId().getLogin());
    		detailBean.setPlantCode(bean.getId().getPlantCode());
    		detailBean.setPlantDescription(bean.getPlantDescription());
    		lineBeanlist.add(detailBean);
    		userBean.setUserPlantDetailsNew(lineBeanlist);
    		userPlantBean.add(userBean);
    	}
    	return userPlantBean;

    }

    @GetMapping("/user-plants-new/{ids}")
    @Timed
    public ResponseEntity<UserPlantBean> getAssetMaster(@PathVariable String ids) {
    	UserPlantBean userPlantBean = new UserPlantBean();
    	List<UserPlantDetailsBean> lineList = new ArrayList<UserPlantDetailsBean>();
    	log.debug("REST request to get UserPlant : {}", ids);
	    List<UserPlant> userPlant = userPlantRepository.findAllByLogin(ids);
	    for(UserPlant plants: userPlant) {
	    	UserPlantDetailsBean userPlantDetailsBean = new UserPlantDetailsBean();
	    	userPlantBean.setLogin(plants.getId().getLogin());
	    	userPlantDetailsBean.setPlantCode(plants.getId().getPlantCode());
	    	userPlantDetailsBean.setPlantDescription(plants.getPlantDescription());
	    	lineList.add(userPlantDetailsBean);
	    	userPlantBean.setUserPlantDetailsNew(lineList);
	    }
        return ResponseUtil.wrapOrNotFound(Optional.of(userPlantBean));
    }

    @GetMapping("/user-plants-all")
    public List<UserPlantDetailsBean> getAllUserPlantList() {
		List<UserPlantDetailsBean> lineBeanlist = new ArrayList<UserPlantDetailsBean>();
    	List<Factory> factories = factoryRepository.findAll();
    	for(Factory bean: factories) {
    		UserPlantDetailsBean lineBean = new UserPlantDetailsBean();
    		lineBean.setPlantCode(bean.getFactCode());
    		lineBean.setPlantDescription(bean.getSearchdescription());
    		lineBeanlist.add(lineBean);
    	}
        return lineBeanlist;
    }

    /**
     * {@code GET /users} : get all users.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body all users.
     */
    @PostMapping("/user-plants")
    public List<UserPlant> getAllUserPlants(@RequestBody Master master) {
        List<UserPlant> userPlants = new ArrayList<>();
        String user = SecurityUtils.getCurrentUserLogin().orElse(null);
        if (master.getBtnType() != null && master.getBtnType().equalsIgnoreCase("I")) {
            userPlants = userPlantRepository.findAllByLoginAndPlantCode(SecurityUtils.getCurrentUserLogin().orElse(null), master.getPlantCode());
        } else if (master.getBtnType() != null && master.getBtnType().equalsIgnoreCase("O")) {
            List<Factory> factories = factoryRepository.findAllAndNotPlant(master.getPlantCode());
            for (Factory factory : factories) {
                UserPlant userPlant = new UserPlant();
                userPlant.setId(new UserPlantId(user, factory.getFactCode()));
                userPlant.setPlantDescription(factory.getFactDescription());
                userPlants.add(userPlant);
            }
        }
        return userPlants;
    }

	@PostMapping("/user-plants-new")
	@Timed
	public ResponseEntity<UserPlantBean> saveUserPlantByLoginId(@RequestBody UserPlantBean userPlantBean) {
		UserPlant plantss = new UserPlant();
		List<UserPlant> userPlants = new ArrayList<>();
		if (userPlantBean.getLogin() != null) {
			userPlants = userPlantRepository.findAllByLogin(userPlantBean.getLogin());
			if(userPlants.size()>0) {
				userPlantRepository.deleteAllLogins(userPlantBean.getLogin());
				for(UserPlantDetailsBean beans : userPlantBean.getUserPlantDetailsNew()) {
					UserPlant plant = new UserPlant();
					UserPlantId id = new UserPlantId();
					id.setLogin(userPlantBean.getLogin());
					id.setPlantCode(beans.getPlantCode());
					plant.setId(id);
					plant.setPlantDescription(beans.getPlantDescription());
					if(beans.getPlantCode().length()>0) {
						UserPlant result = userPlantRepository.save(plant);
					}
				}
			}else {
				for(UserPlantDetailsBean beans : userPlantBean.getUserPlantDetailsNew()) {
					UserPlant plant = new UserPlant();
					UserPlantId id = new UserPlantId();
					id.setLogin(userPlantBean.getLogin());
					id.setPlantCode(beans.getPlantCode());
					plant.setId(id);
					plant.setPlantDescription(beans.getPlantDescription());
					if(beans.getPlantCode().length()>0) {
						UserPlant result = userPlantRepository.save(plant);
					}
				}
			}
		}
		return ResponseEntity.ok().body(userPlantBean);
	}

	@DeleteMapping("/user-plants-delete/{ids}/{login}")
    public ResponseEntity<Void> deleteUserPlantDetail(@PathVariable String ids,@PathVariable String login) {
        log.debug("REST request to delete UserPlant : {}", ids);
        userPlantRepository.deleteByLoginAndPlantCode(login, ids);
        return ResponseEntity.ok().build();
    }
}
