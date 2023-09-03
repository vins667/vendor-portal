package io.vamani.application.web.rest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.vamani.application.db2.domain.Itemtype;
import io.vamani.application.db2.domain.Usergenericgroup;
import io.vamani.application.db2.repository.ItemtypeRepository;
import io.vamani.application.db2.repository.UnitOfMeasureRepository;
import io.vamani.application.db2.repository.UsergenericgroupRepository;
import io.vamani.application.domain.BuyerCosting;
import io.vamani.application.domain.CostingGroupMaster;
import io.vamani.application.model.BuyerCostingDetailsBean;
import io.vamani.application.model.BuyerCostingSubDetailsBean;
import io.vamani.application.repository.BuyerCostingRepository;
import io.vamani.application.repository.CostingGroupDetailsRepository;
import io.vamani.application.repository.CostingGroupMasterRepository;
import io.vamani.application.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link io.vamani.application.domain.BuyerCosting}.
 */
@RestController
@RequestMapping("/api")
public class BuyerCostingResource {

    private final Logger log = LoggerFactory.getLogger(BuyerCostingResource.class);

    private static final String ENTITY_NAME = "buyerCosting";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;
    
    @Autowired
    private  CostingGroupMasterRepository costingGroupMasterRepository;
    
    @Autowired
    private  CostingGroupDetailsRepository costingGroupDetailsRepository;
    
    @Autowired
    private UnitOfMeasureRepository unitOfMeasureRepository;
    
    @Autowired
    private ItemtypeRepository itemtypeRepository;
    
    @Autowired
    private UsergenericgroupRepository usergenericgroupRepository;

    private final BuyerCostingRepository buyerCostingRepository;

    public BuyerCostingResource(BuyerCostingRepository buyerCostingRepository) {
        this.buyerCostingRepository = buyerCostingRepository;
    }

    /**
     * {@code POST  /buyer-costings} : Create a new buyerCosting.
     *
     * @param buyerCosting the buyerCosting to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new buyerCosting, or with status {@code 400 (Bad Request)} if the buyerCosting has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/buyer-costings")
    public ResponseEntity<BuyerCosting> createBuyerCosting(@RequestBody BuyerCosting buyerCosting) throws URISyntaxException {
        log.debug("REST request to save BuyerCosting : {}", buyerCosting);
        if (buyerCosting.getId() != null) {
            throw new BadRequestAlertException("A new buyerCosting cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BuyerCosting result = buyerCostingRepository.save(buyerCosting);
        return ResponseEntity.created(new URI("/api/buyer-costings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /buyer-costings} : Updates an existing buyerCosting.
     *
     * @param buyerCosting the buyerCosting to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated buyerCosting,
     * or with status {@code 400 (Bad Request)} if the buyerCosting is not valid,
     * or with status {@code 500 (Internal Server Error)} if the buyerCosting couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/buyer-costings")
    public ResponseEntity<BuyerCosting> updateBuyerCosting(@RequestBody BuyerCosting buyerCosting) throws URISyntaxException {
        log.debug("REST request to update BuyerCosting : {}", buyerCosting);
        if (buyerCosting.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BuyerCosting result = buyerCostingRepository.save(buyerCosting);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, buyerCosting.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /buyer-costings} : get all the buyerCostings.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of buyerCostings in body.
     */
    @GetMapping("/buyer-costings")
    public ResponseEntity<List<BuyerCosting>> getAllBuyerCostings(Pageable pageable) {
        log.debug("REST request to get a page of BuyerCostings");
        Page<BuyerCosting> page = buyerCostingRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /buyer-costings/:id} : get the "id" buyerCosting.
     *
     * @param id the id of the buyerCosting to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the buyerCosting, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/buyer-costings/{id}")
    public ResponseEntity<BuyerCosting> getBuyerCosting(@PathVariable Long id) {
        log.debug("REST request to get BuyerCosting : {}", id);
        Optional<BuyerCosting> buyerCosting = buyerCostingRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(buyerCosting);
    }

    /**
     * {@code DELETE  /buyer-costings/:id} : delete the "id" buyerCosting.
     *
     * @param id the id of the buyerCosting to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/buyer-costings/{id}")
    public ResponseEntity<Void> deleteBuyerCosting(@PathVariable Long id) {
        log.debug("REST request to delete BuyerCosting : {}", id);
        buyerCostingRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
    
    /**
     * {@code GET  /buyer-costings/:id} : get the "id" buyerCosting.
     *
     * @param id the id of the buyerCosting to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the buyerCosting, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/buyer-costings-master")
    public ResponseEntity<List<BuyerCostingDetailsBean>> getBuyerCosting() {
        log.debug("REST request to get BuyerCosting : {}");
        List<BuyerCostingDetailsBean> buyerCostingDetailsBean  = new ArrayList<>();
        List<CostingGroupMaster> costingGroupMaster = costingGroupMasterRepository.findAllCostingGroupMaster();
     
        for(CostingGroupMaster master:costingGroupMaster) {
        	BuyerCostingDetailsBean bean = new BuyerCostingDetailsBean();
        	bean.setGroupCode(master.getCode());
            List<BuyerCostingSubDetailsBean> buyerCostingSubDetailsBean = new ArrayList<>();
            BuyerCostingSubDetailsBean details = new BuyerCostingSubDetailsBean();
        	details.setCostingGroupDetails(costingGroupDetailsRepository.findAllByCostingGroupMasterId(master.getId()));
        	details.setUnitOfMeasures(unitOfMeasureRepository.findAll());
        	bean.setBuyerCostingSubDetails(buyerCostingSubDetailsBean);
        	buyerCostingSubDetailsBean.add(details);
        	buyerCostingDetailsBean.add(bean);
        }
        return ResponseUtil.wrapOrNotFound(Optional.of(buyerCostingDetailsBean));
    }
    
    @GetMapping("/buyer-costings-generic/{code}")
    public ResponseEntity<List<Usergenericgroup>> getBuyerCostingUserGenericGroup(@PathVariable String code) {
        List<Usergenericgroup> usergenericgroup = usergenericgroupRepository.findByCode(code);
        return ResponseUtil.wrapOrNotFound(Optional.of(usergenericgroup));
    }
    
    @GetMapping("/buyer-costings-itemtype/{code}") 
    public ResponseEntity<Itemtype> getItemTypes(@PathVariable String code) {
        Optional<Itemtype> itemtype = itemtypeRepository.findByCode("100", code.toUpperCase().trim());
        return ResponseUtil.wrapOrNotFound(itemtype);
    }
}
