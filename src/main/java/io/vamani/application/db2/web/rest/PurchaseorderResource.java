package io.vamani.application.db2.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.web.util.PaginationUtil;
import io.vamani.application.db2.domain.Purchaseorder;
import io.vamani.application.db2.domain.ViewDiextopline;
import io.vamani.application.db2.repository.PurchaseorderRepository;
import io.vamani.application.db2.repository.ViewDiextoplineRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * REST controller for managing {@link Purchaseorder}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class PurchaseorderResource {
    private final Logger log = LoggerFactory.getLogger(PurchaseorderResource.class);

    private static final String ENTITY_NAME = "purchaseorder";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PurchaseorderRepository purchaseorderRepository;

    @Autowired
    private ViewDiextoplineRepository viewDiextoplineRepository;

    public PurchaseorderResource(PurchaseorderRepository purchaseorderRepository) {
        this.purchaseorderRepository = purchaseorderRepository;
    }

    @GetMapping("/db2-purchaseorders/{supplierCode}/{purchaseordercode}")
    @Timed
    public ResponseEntity<List<Purchaseorder>> getAllPurchaseorder(@PathVariable String supplierCode, @PathVariable String purchaseordercode) throws UnsupportedEncodingException {
        log.debug("REST request to get a page of purchaseorder");
        Pageable pageable = PageRequest.of(0, 50, Sort.by("id.code").ascending());
        purchaseordercode = URLDecoder.decode(purchaseordercode, "UTF-8");
        Page<Purchaseorder> page = purchaseorderRepository.findAllByPurchaseorderIgnoreCaseLike(supplierCode.trim(), "%" + purchaseordercode.toUpperCase() + "%", pageable);

        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/db2-jobworks/{supplierCode}/{purchaseordercode}")
    @Timed
    public ResponseEntity<List<ViewDiextopline>> getJobworks(@PathVariable String supplierCode, @PathVariable String purchaseordercode) throws UnsupportedEncodingException {
        log.debug("REST request to get a page of purchaseorder");
        Pageable pageable = PageRequest.of(0, 50, Sort.by("id.code").ascending());
        purchaseordercode = URLDecoder.decode(purchaseordercode, "UTF-8");
        Page<ViewDiextopline> page = viewDiextoplineRepository.findAllByViewDiextoplineIgnoreCaseLike(supplierCode.trim(), "%" + purchaseordercode.toUpperCase() + "%", pageable);

        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
