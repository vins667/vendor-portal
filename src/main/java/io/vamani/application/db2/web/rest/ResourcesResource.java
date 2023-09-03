package io.vamani.application.db2.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.web.util.PaginationUtil;
import io.vamani.application.config.Constants;
import io.vamani.application.db2.domain.Paymentmethod;
import io.vamani.application.db2.domain.Resources;
import io.vamani.application.db2.domain.ResourcesId;
import io.vamani.application.db2.model.ResourcesBean;
import io.vamani.application.db2.repository.ResourcesRepository;
import io.vamani.application.db2.repository.SalesorderlineRepository;
import io.vamani.application.model.Master;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ResourcesResource{
    private final Logger log = LoggerFactory.getLogger(ResourcesResource.class);

    private static final String ENTITY_NAME = "resources";

    private final ResourcesRepository resourcesRepository;

    public ResourcesResource(ResourcesRepository resourcesRepository) {
        this.resourcesRepository = resourcesRepository;
    }

    @GetMapping("/resources-byplantcode/{plantcode}")
    @Timed
    public List<Resources> getAllByPlantCode(@PathVariable String plantcode) {
        log.debug("REST request to get a page of resources");
        return resourcesRepository.findAllResourcesByPlantcode(plantcode);
    }

    @PostMapping("/resources-byplantcode")
    @Timed
    public List<ResourcesBean> getAllByPlantCode(@RequestBody Master master) {
        log.debug("REST request to get a page of resources");
        List<ResourcesBean> resourcesBeans = new ArrayList<>();
        if (master.getCode().equalsIgnoreCase("CUTTING")) {
            for(Resources resource : resourcesRepository.findAllResourcesByPlantcode(master.getPlantCode())) {
                ResourcesBean resourcesBean = new ResourcesBean();
                BeanUtils.copyProperties(resource, resourcesBean, "id");
                resourcesBean.setCode(resource.getId().getCode());
                resourcesBeans.add(resourcesBean);
            }
            return resourcesBeans;
        } else if (master.getCode().equalsIgnoreCase("STITCHING")) {
            for(Resources resource : resourcesRepository.findAllStitchResourcesByPlantcode(master.getPlantCode())) {
                ResourcesBean resourcesBean = new ResourcesBean();
                BeanUtils.copyProperties(resource, resourcesBean, "id");
                resourcesBean.setCode(resource.getId().getCode());
                resourcesBeans.add(resourcesBean);
            }
            return resourcesBeans;
        } else if (master.getCode().equalsIgnoreCase("FINISHING")) {
            for(Resources resource : resourcesRepository.findAllFinishResourcesByPlantcode(master.getPlantCode())) {
                ResourcesBean resourcesBean = new ResourcesBean();
                BeanUtils.copyProperties(resource, resourcesBean, "id");
                resourcesBean.setCode(resource.getId().getCode());
                resourcesBeans.add(resourcesBean);
            }
            return resourcesBeans;
        } else {
            ResourcesBean resources = new ResourcesBean();
            resources.setCode(master.getPlantCode());
            resources.setLongdescription(master.getPlantCode());
            resources.setSearchdescription(master.getPlantCode());
            resources.setShortdescription(master.getPlantCode());
            resourcesBeans.add(resources);
            return resourcesBeans;
        }
    }

    @GetMapping("/sewing-resources-byplantcode/{plantcode}")
    @Timed
    public List<Resources> getAllStitchByPlantCode(@PathVariable String plantcode) {
        log.debug("REST request to get a page of resources");
        return resourcesRepository.findAllStitchResourcesByPlantcode(plantcode);
    }

    @GetMapping("/packing-resources-byplantcode/{plantcode}")
    @Timed
    public List<Resources> getAllPackingByPlantCode(@PathVariable String plantcode) {
        log.debug("REST request to get a page of resources");
        return resourcesRepository.findAllPackingResourcesByPlantcode(plantcode);
    }
}
