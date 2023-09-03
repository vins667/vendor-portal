package io.vamani.application.db2.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.vamani.application.db2.domain.*;
import io.vamani.application.db2.model.ChequeBean;
import io.vamani.application.db2.model.MasterParameters;
import io.vamani.application.db2.model.MrnBean;
import io.vamani.application.db2.model.OutstandingBean;
import io.vamani.application.db2.repository.DebitNoteEntryRepository;
import io.vamani.application.db2.repository.PurchaseinvoiceRepository;
import io.vamani.application.db2.repository.FinfinancialyearRepository;
import io.vamani.application.security.SecurityUtils;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link Purchaseinvoice}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class PurchaseinvoiceResource {
    private final Logger log = LoggerFactory.getLogger(PurchaseinvoiceResource.class);

    private static final String ENTITY_NAME = "purchaseinvoice";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PurchaseinvoiceRepository purchaseinvoiceRepository;

    public PurchaseinvoiceResource(PurchaseinvoiceRepository purchaseinvoiceRepository) {
        this.purchaseinvoiceRepository = purchaseinvoiceRepository;
    }

    @GetMapping("/purchaseinvoices/{supplierCode}/{purchaseinvoicecode}")
    @Timed
    public ResponseEntity<List<Purchaseinvoice>> getAllPurchaseinvoices(@PathVariable String supplierCode, @PathVariable String purchaseinvoicecode) throws UnsupportedEncodingException {
        log.debug("REST request to get a page of purchaseinvoice");
        Pageable pageable = PageRequest.of(0, 50, Sort.by("id.code").ascending());
        purchaseinvoicecode = URLDecoder.decode(purchaseinvoicecode, "UTF-8");
        Page<Purchaseinvoice> page = purchaseinvoiceRepository.findAllByPurchaseinvoiceAllIgnoreCaseLike(supplierCode.trim(), "%" + purchaseinvoicecode.toUpperCase() + "%", pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
