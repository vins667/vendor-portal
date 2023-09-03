package io.vamani.application.web.rest;

import io.github.jhipster.web.util.ResponseUtil;
import io.vamani.application.domain.PaymentRequestForward;
import io.vamani.application.model.MasterSearch;
import io.vamani.application.repository.PaymentRequestForwardRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

import io.vamani.application.web.rest.util.HeaderUtil;
import io.vamani.application.web.rest.util.PaginationUtil;
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
import org.springframework.web.bind.annotation.*;


/**
 * REST controller for managing {@link PaymentRequestForward}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class PaymentRequestForwardResource {

  private final Logger log = LoggerFactory.getLogger(PaymentRequestForwardResource.class);

  private static final String ENTITY_NAME = "paymentRequestForward";

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  private final PaymentRequestForwardRepository paymentRequestForwardRepository;

  public PaymentRequestForwardResource(PaymentRequestForwardRepository paymentRequestForwardRepository) {
    this.paymentRequestForwardRepository = paymentRequestForwardRepository;
  }

  /**
   * {@code POST  /payment-request-forwards} : Create a new paymentRequestForward.
   *
   * @param paymentRequestForward the paymentRequestForward to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new paymentRequestForward, or with status {@code 400 (Bad Request)} if the paymentRequestForward has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/payment-request-forwards")
  public ResponseEntity<PaymentRequestForward> createPaymentRequestForward(@Valid @RequestBody PaymentRequestForward paymentRequestForward)
      throws URISyntaxException {
      log.debug("REST request to save PaymentRequestForward : {}", paymentRequestForward);
      if (paymentRequestForward.getId() != null) {
          throw new BadRequestAlertException("A new paymentRequestForward cannot already have an ID", ENTITY_NAME, "idexists");
      }
      paymentRequestForward.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
      paymentRequestForward.setCreatedDate(Instant.now());
      PaymentRequestForward result = paymentRequestForwardRepository.save(paymentRequestForward);
      return ResponseEntity
          .created(new URI("/api/payment-request-forwards/" + result.getId()))
          .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
          .body(result);
  }

  /**
   * {@code PUT  /payment-request-forwards/:id} : Updates an existing paymentRequestForward.
   *
   * @param id the id of the paymentRequestForward to save.
   * @param paymentRequestForward the paymentRequestForward to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated paymentRequestForward,
   * or with status {@code 400 (Bad Request)} if the paymentRequestForward is not valid,
   * or with status {@code 500 (Internal Server Error)} if the paymentRequestForward couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/payment-request-forwards")
  public ResponseEntity<PaymentRequestForward> updatePaymentRequestForward(
    @Valid @RequestBody PaymentRequestForward paymentRequestForward
  ) throws URISyntaxException {
    log.debug("REST request to update PaymentRequestForward : {}, {}", paymentRequestForward);
    if (paymentRequestForward.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }

    if (!paymentRequestForwardRepository.existsById(paymentRequestForward.getId())) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    PaymentRequestForward result = paymentRequestForwardRepository.save(paymentRequestForward);
    return ResponseEntity
      .ok()
      .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, paymentRequestForward.getId().toString()))
      .body(result);
  }

  /**
   * {@code GET  /payment-request-forwards} : get all the paymentRequestForwards.
   *
   * @param pageable the pagination information.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of paymentRequestForwards in body.
   */
  @GetMapping("/payment-request-forwards")
  public ResponseEntity<List<PaymentRequestForward>> getAllPaymentRequestForwards(
    Pageable pageable
  ) {
    log.debug("REST request to get a page of PaymentRequestForwards");
    Page<PaymentRequestForward> page = paymentRequestForwardRepository.findAll(pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "");
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }

    /**
     * {@code GET  /payment-request-forwards} : get all the paymentRequestForwards.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of paymentRequestForwards in body.
     */
    @PostMapping("/payment-request-forwards-filter")
    public ResponseEntity<List<PaymentRequestForward>> getAllPaymentRequestForwardsFilter(@RequestBody MasterSearch search) {
        log.debug("REST request to get a page of PaymentRequestForwards");
        String code = "%";
        if (search.getCode() != null && search.getCode().length() > 0) {
            code = "%" + search.getCode().toUpperCase() + "%";
        }
        String desc = "%";
        if (search.getDescription() != null && search.getDescription().length() > 0) {
            desc = "%" + search.getDescription() + "%";
        }

        search.setPage(
            PageRequest.of(
                search.getPageNo(),
                search.getSize(),
                search.getSortType() != null && search.getSortType().equalsIgnoreCase("desc")
                    ? Sort.by(search.getSort()).descending()
                    : Sort.by(search.getSort()).ascending()
            )
        );
        Page<PaymentRequestForward> page = paymentRequestForwardRepository.findAllByEmpCodeAndForwardCode(code, code, desc, desc, search.getPage());
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /payment-request-forwards/:id} : get the "id" paymentRequestForward.
     *
     * @param id the id of the paymentRequestForward to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the paymentRequestForward, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/payment-request-forwards/{id}")
    public ResponseEntity<PaymentRequestForward> getPaymentRequestForward(@PathVariable Long id) {
        log.debug("REST request to get PaymentRequestForward : {}", id);
        Optional<PaymentRequestForward> paymentRequestForward = paymentRequestForwardRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(paymentRequestForward);
    }


    /**
     * {@code GET  /payment-request-forwards/:id} : get the "id" paymentRequestForward.
     *
     * @param id the id of the paymentRequestForward to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the paymentRequestForward, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/payment-request-forwards-by-type/{type}")
    public ResponseEntity<List<PaymentRequestForward>> getPaymentRequestForwardByType(@PathVariable String type) {
        log.debug("REST request to get PaymentRequestForward : {}", type);
        List<PaymentRequestForward> paymentRequestForwards = paymentRequestForwardRepository.findAllByEmpCodeAndForwardType(SecurityUtils.getCurrentUserLogin().orElse(null), type);
        return ResponseUtil.wrapOrNotFound(Optional.of(paymentRequestForwards));
    }
  /**
   * {@code DELETE  /payment-request-forwards/:id} : delete the "id" paymentRequestForward.
   *
   * @param id the id of the paymentRequestForward to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/payment-request-forwards/{id}")
  public ResponseEntity<Void> deletePaymentRequestForward(@PathVariable Long id) {
    log.debug("REST request to delete PaymentRequestForward : {}", id);
    paymentRequestForwardRepository.deleteById(id);
    return ResponseEntity
      .noContent()
      .headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString()))
      .build();
  }
}
