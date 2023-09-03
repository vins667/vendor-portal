package io.vamani.application.db2.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.vamani.application.config.Constants;
import io.vamani.application.db2.domain.Paymentmethod;
import io.vamani.application.db2.domain.PaymentmethodId;
import io.vamani.application.db2.repository.PaymentmethodRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link Paymentmethod}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class PaymentmethodResource {
    private final Logger log = LoggerFactory.getLogger(PaymentmethodResource.class);

    private static final String ENTITY_NAME = "paymentmethod";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PaymentmethodRepository paymentmethodRepository;

    public PaymentmethodResource(PaymentmethodRepository paymentmethodRepository) {
        this.paymentmethodRepository = paymentmethodRepository;
    }

    @GetMapping("/paymentmethods/{longdescription}")
    @Timed
    public ResponseEntity<List<Paymentmethod>> getAllEstprdemployeedetails(@PathVariable String longdescription) {
        log.debug("REST request to get a page of paymentmethod");
        Pageable pageable = PageRequest.of(0, 50, Sort.by("longdescription").ascending());
        Page<Paymentmethod> page = paymentmethodRepository.findAllByLongdescriptionIgnoreCaseLike("%" + longdescription.toUpperCase() + "%", pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/db2-paymentmethods")
    @Timed
    public ResponseEntity<List<Paymentmethod>> getPaymentmethod() {
        log.debug("REST request to get a page of paymentmethod");
        return ResponseEntity.ok().body(paymentmethodRepository.findAll());
    }

    @GetMapping("/db2-paymentmethods/{paymenttermcode}")
    @Timed
    public ResponseEntity<Paymentmethod> getPaymentmethod(@PathVariable String paymenttermcode) {
        log.debug("REST request to get a page of paymentmethod");
        Optional<Paymentmethod> paymentmethod = paymentmethodRepository.findById(new PaymentmethodId(Constants.COMPANY_CODE, paymenttermcode));
        return ResponseUtil.wrapOrNotFound(paymentmethod);
    }
}
