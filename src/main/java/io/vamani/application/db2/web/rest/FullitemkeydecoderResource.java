package io.vamani.application.db2.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.web.util.PaginationUtil;
import io.vamani.application.db2.domain.Fullitemkeydecoder;
import io.vamani.application.db2.domain.ViewDitaxglmapping;
import io.vamani.application.db2.model.FullitemkeydecoderBean;
import io.vamani.application.db2.repository.FullitemkeydecoderRepository;
import io.vamani.application.db2.repository.HsngstmappingRepository;
import io.vamani.application.model.Master;
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

import java.util.ArrayList;
import java.util.List;

/**
 * REST controller for managing {@link Fullitemkeydecoder}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class FullitemkeydecoderResource {
    private final Logger log = LoggerFactory.getLogger(FullitemkeydecoderResource.class);

    private static final String ENTITY_NAME = "fullitemkeydecoder";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FullitemkeydecoderRepository fullitemkeydecoderRepository;

    @Autowired
    private HsngstmappingRepository hsngstmappingRepository;

    public FullitemkeydecoderResource(FullitemkeydecoderRepository fullitemkeydecoderRepository) {
        this.fullitemkeydecoderRepository = fullitemkeydecoderRepository;
    }

    @GetMapping("/fullitemkeydecoders/{summarizeddescription}")
    @Timed
    public ResponseEntity<List<FullitemkeydecoderBean>> getAllEstprdemployeedetails(@PathVariable String summarizeddescription) {
        log.debug("REST request to get a page of fullitemkeydecoder");
        Pageable pageable = PageRequest.of(0, 25, Sort.by("summarizeddescription").ascending());
        Page<Fullitemkeydecoder> page = fullitemkeydecoderRepository.findAllByLongdescriptionIgnoreCaseLike("%" + summarizeddescription.toUpperCase() + "%", pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        List<FullitemkeydecoderBean> fullitemkeydecoderBeans = new ArrayList<>();
        for (Fullitemkeydecoder fullitemkeydecoder : page.getContent()) {
            FullitemkeydecoderBean fullitemkeydecoderBean = new FullitemkeydecoderBean();
            BeanUtils.copyProperties(fullitemkeydecoder, fullitemkeydecoderBean);
            // fullitemkeydecoderBean.setUom(fullitemkeydecoderRepository.fetchUOM(fullitemkeydecoder.getId().getItemtypecode(), fullitemkeydecoder.getId().getSubcode01(), fullitemkeydecoder.getId().getSubcode02(), fullitemkeydecoder.getId().getSubcode03(), fullitemkeydecoder.getId().getSubcode04(), fullitemkeydecoder.getId().getSubcode05(), fullitemkeydecoder.getId().getSubcode06(), fullitemkeydecoder.getId().getSubcode07(), fullitemkeydecoder.getId().getSubcode08()));
            // fullitemkeydecoderBean.setTariffcode(fullitemkeydecoderRepository.fetchTarrif(fullitemkeydecoder.getId().getItemtypecode(), fullitemkeydecoder.getId().getSubcode01(), fullitemkeydecoder.getId().getSubcode02(), fullitemkeydecoder.getId().getSubcode03(), fullitemkeydecoder.getId().getSubcode04(), fullitemkeydecoder.getId().getSubcode05(), fullitemkeydecoder.getId().getSubcode06(), fullitemkeydecoder.getId().getSubcode07(), fullitemkeydecoder.getId().getSubcode08()));
            fullitemkeydecoderBeans.add(fullitemkeydecoderBean);
        }
        return ResponseEntity.ok().headers(headers).body(fullitemkeydecoderBeans);
    }

    @PostMapping("/fullitemkeydecoders")
    @Timed
    public ResponseEntity<FullitemkeydecoderBean> getAllEstprdemployeedetails(@RequestBody FullitemkeydecoderBean fullitemkeydecoderBean) {
        log.debug("REST request to get a page of fullitemkeydecoder");
        fullitemkeydecoderBean.setUom(fullitemkeydecoderRepository.fetchUOM(fullitemkeydecoderBean.getId().getItemtypecode(), fullitemkeydecoderBean.getId().getSubcode01(), fullitemkeydecoderBean.getId().getSubcode02(), fullitemkeydecoderBean.getId().getSubcode03(), fullitemkeydecoderBean.getId().getSubcode04(), fullitemkeydecoderBean.getId().getSubcode05(), fullitemkeydecoderBean.getId().getSubcode06(), fullitemkeydecoderBean.getId().getSubcode07(), fullitemkeydecoderBean.getId().getSubcode08()));
        fullitemkeydecoderBean.setTariffcode(fullitemkeydecoderRepository.fetchTarrif(fullitemkeydecoderBean.getId().getItemtypecode(), fullitemkeydecoderBean.getId().getSubcode01(), fullitemkeydecoderBean.getId().getSubcode02(), fullitemkeydecoderBean.getId().getSubcode03(), fullitemkeydecoderBean.getId().getSubcode04(), fullitemkeydecoderBean.getId().getSubcode05(), fullitemkeydecoderBean.getId().getSubcode06(), fullitemkeydecoderBean.getId().getSubcode07(), fullitemkeydecoderBean.getId().getSubcode08()));

        return ResponseEntity.ok().body(fullitemkeydecoderBean);
    }

    @PostMapping("/hsnmapping-cgst")
    @Timed
    public ResponseEntity<List<ViewDitaxglmapping>> getAllCGSTDetails(@RequestBody Master master) {
        log.debug("REST request to get a page of fullitemkeydecoder");
        List<ViewDitaxglmapping> viewDitaxglmappings = hsngstmappingRepository.findAllCgstAndSgst(master.getName(), master.getDesc());
        return ResponseEntity.ok().body(viewDitaxglmappings);
    }

    @PostMapping("/hsnmapping-igst")
    @Timed
    public ResponseEntity<List<ViewDitaxglmapping>> getAllIGSTDetails(@RequestBody Master master) {
        log.debug("REST request to get a page of fullitemkeydecoder");
        List<ViewDitaxglmapping> viewDitaxglmappings = hsngstmappingRepository.findAllIgst(master.getName(), master.getDesc());
        return ResponseEntity.ok().body(viewDitaxglmappings);
    }
}
