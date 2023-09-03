package io.vamani.application.db2.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.web.util.PaginationUtil;
import io.vamani.application.db2.domain.Glmaster;
import io.vamani.application.db2.model.GlmasterBean;
import io.vamani.application.db2.repository.GlmasterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * REST controller for managing {@link Glmaster}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class GlmasterResource {
    private final Logger log = LoggerFactory.getLogger(GlmasterResource.class);

    private static final String ENTITY_NAME = "glmaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GlmasterRepository glmasterRepository;

    public GlmasterResource(GlmasterRepository glmasterRepository) {
        this.glmasterRepository = glmasterRepository;
    }

    @GetMapping("/glmasters/{longdescription}")
    @Timed
    public ResponseEntity<List<GlmasterBean>> getAllEstprdemployeedetails(@PathVariable String longdescription) {
        log.debug("REST request to get a page of glmaster");
        Pageable pageable = PageRequest.of(0, 50, Sort.by("longdescription").ascending());
        Page<Glmaster> page = glmasterRepository.findAllByLongdescriptionIgnoreCaseLike("%" + longdescription.toUpperCase() + "%", "%" + longdescription.toUpperCase() + "%", pageable);
        List<GlmasterBean> glmasterBeans = new ArrayList<>();
        for (Glmaster glmaster : page.getContent()) {
            GlmasterBean glmasterBean = new GlmasterBean();
            BeanUtils.copyProperties(glmaster, glmasterBean);
            glmasterBean.setLongdescription(glmaster.getId().getCode().trim() + "-" + glmaster.getLongdescription().trim());
            glmasterBeans.add(glmasterBean);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(glmasterBeans);
    }
    @GetMapping("/glmasters-code/{code}")
    @Timed
    public ResponseEntity<List<GlmasterBean>> getAllEstprdemployeedetailsAll(@PathVariable String code) {
        log.debug("REST request to get a page of glmaster");
        Pageable pageable = PageRequest.of(0, 50, Sort.by("id.code").ascending());
        Page<Glmaster> page = glmasterRepository.findAllByCodeIgnoreCaseLike("%" + code.toUpperCase() + "%", pageable);
        List<GlmasterBean> glmasterBeans = new ArrayList<>();
        for (Glmaster glmaster : page.getContent()) {
            GlmasterBean glmasterBean = new GlmasterBean();
            BeanUtils.copyProperties(glmaster, glmasterBean);
            glmasterBeans.add(glmasterBean);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(glmasterBeans);
    }

}
