package io.vamani.application.db2.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.web.util.PaginationUtil;
import io.vamani.application.config.Constants;
import io.vamani.application.db2.domain.FullitemkeydecoderId;
import io.vamani.application.db2.domain.Glmaster;
import io.vamani.application.db2.domain.GlmasterId;
import io.vamani.application.db2.domain.Itemvseventglmap;
import io.vamani.application.db2.model.ItemvseventglmapSearch;
import io.vamani.application.db2.repository.GlmasterRepository;
import io.vamani.application.db2.repository.ItemvseventglmapRepository;
import io.vamani.application.model.ItemvseventglmapBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

/**
 * REST controller for managing {@link Itemvseventglmap}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ItemvseventglmapResource {
    private final Logger log = LoggerFactory.getLogger(ItemvseventglmapResource.class);

    private static final String ENTITY_NAME = "itemvseventglmap";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ItemvseventglmapRepository itemvseventglmapRepository;

    @Autowired
    private GlmasterRepository glmasterRepository;

    public ItemvseventglmapResource(ItemvseventglmapRepository itemvseventglmapRepository) {
        this.itemvseventglmapRepository = itemvseventglmapRepository;
    }

    @PostMapping("/db2-itemvseventglmaps")
    @Timed
    public ResponseEntity<ItemvseventglmapBean> getAllEstprdemployeedetails(@RequestBody ItemvseventglmapSearch search) {
        log.debug("REST request to get a page of itemvseventglmaps");
        ItemvseventglmapBean itemvseventglmapBean = null;
        if (search.getItemtypecode() != null && search.getItemtypecode().equalsIgnoreCase("GEN")) {
            List<Itemvseventglmap> itemvseventglmaps = itemvseventglmapRepository.findDetailByEventAndItemtypeAndSubcode01("41", search.getDivision(), search.getItemtypecode().trim(), search.getSubcode01().trim(), search.getCustomersuppliertype(), "1");
            if (itemvseventglmaps != null && itemvseventglmaps.size() > 0) {
                Itemvseventglmap itemvseventglmap = itemvseventglmaps.get(0);
                itemvseventglmapBean = new ItemvseventglmapBean();
                BeanUtils.copyProperties(itemvseventglmap, itemvseventglmapBean);
                if (itemvseventglmap.getDebitglcode() != null) {
                    Glmaster glmaster = glmasterRepository.findById(new GlmasterId(Constants.COMPANY_CODE, itemvseventglmap.getDebitglcode().trim())).orElse(null);
                    itemvseventglmapBean.setDebitgldescription(glmaster.getLongdescription());
                }
            }
        } else {
            List<Itemvseventglmap> itemvseventglmaps = itemvseventglmapRepository.findDetailByEventAndItemtype("41", search.getDivision(), search.getItemtypecode().trim(), search.getCustomersuppliertype(), "1");
            if (itemvseventglmaps != null && itemvseventglmaps.size() > 0) {
                Itemvseventglmap itemvseventglmap = itemvseventglmaps.get(0);
                itemvseventglmapBean = new ItemvseventglmapBean();
                BeanUtils.copyProperties(itemvseventglmap, itemvseventglmapBean);
                if (itemvseventglmap.getDebitglcode() != null) {
                    Glmaster glmaster = glmasterRepository.findById(new GlmasterId(Constants.COMPANY_CODE, itemvseventglmap.getDebitglcode().trim())).orElse(null);
                    itemvseventglmapBean.setDebitgldescription(glmaster.getLongdescription());
                }
            }
        }
        return ResponseEntity.ok().body(itemvseventglmapBean);
    }
}
