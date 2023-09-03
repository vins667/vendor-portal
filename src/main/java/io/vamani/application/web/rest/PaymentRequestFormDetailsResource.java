package io.vamani.application.web.rest;

import io.vamani.application.domain.PaymentRequestForm;
import io.vamani.application.domain.PaymentRequestFormDetails;
import io.vamani.application.mssql.domain.EmployeeView;
import io.vamani.application.mssql.repository.EmployeeViewRepository;
import io.vamani.application.repository.PaymentRequestFormDetailsRepository;
import io.vamani.application.repository.PaymentRequestFormRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;

/**
 * REST controller for managing {@link PaymentRequestFormDetails}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class PaymentRequestFormDetailsResource {

  private final Logger log = LoggerFactory.getLogger(PaymentRequestFormDetailsResource.class);

  private static final String ENTITY_NAME = "paymentRequestFormDetails";

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  @Autowired
  private EmployeeViewRepository employeeViewRepository;

  @Autowired
  private PaymentRequestFormRepository paymentRequestFormRepository;

  private final PaymentRequestFormDetailsRepository paymentRequestFormDetailsRepository;

  public PaymentRequestFormDetailsResource(PaymentRequestFormDetailsRepository paymentRequestFormDetailsRepository) {
    this.paymentRequestFormDetailsRepository = paymentRequestFormDetailsRepository;
  }

  /**
   * {@code POST  /payment-request-form-details} : Create a new paymentRequestFormDetails.
   *
   * @param paymentRequestFormDetails the paymentRequestFormDetails to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new paymentRequestFormDetails, or with status {@code 400 (Bad Request)} if the paymentRequestFormDetails has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/payment-request-form-details")
  public ResponseEntity<PaymentRequestFormDetails> createPaymentRequestFormDetails(
    @Valid @RequestBody PaymentRequestFormDetails paymentRequestFormDetails
  ) throws URISyntaxException {
      log.debug("REST request to save PaymentRequestFormDetails : {}", paymentRequestFormDetails);
      paymentRequestFormDetails.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
      paymentRequestFormDetails.setCreatedDate(Instant.now());
      paymentRequestFormDetails.setEmpCode(SecurityUtils.getCurrentUserLogin().orElse(null));
      EmployeeView employeeView = employeeViewRepository.findByLogin(SecurityUtils.getCurrentUserLogin().orElse(null));
      if (employeeView != null) {
          paymentRequestFormDetails.setEmpName(employeeView.getName());
      }

      PaymentRequestFormDetails result = paymentRequestFormDetailsRepository.save(paymentRequestFormDetails);
      if (result != null && result.getFlag().equalsIgnoreCase("F")) {
          PaymentRequestForm paymentRequestForm = paymentRequestFormRepository.findById(result.getPaymentRequestFormId()).orElse(null);
          paymentRequestForm.setForwardCode(result.getForwardCode());
          paymentRequestForm.setForwardName(result.getForwardName());
          paymentRequestFormRepository.save(paymentRequestForm);

          PaymentRequestFormDetails paymentRequestFormDetail = new PaymentRequestFormDetails();
          paymentRequestFormDetail.setEmpCode(result.getForwardCode());
          paymentRequestFormDetail.setEmpName(result.getForwardName());
          paymentRequestFormDetail.setPaymentRequestFormId(result.getPaymentRequestFormId());
          paymentRequestFormDetailsRepository.save(paymentRequestFormDetail);
      } else {
          PaymentRequestForm paymentRequestForm = paymentRequestFormRepository.findById(result.getPaymentRequestFormId()).orElse(null);
          paymentRequestForm.setApprovedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
          paymentRequestForm.setApprovedDate(Instant.now());
          paymentRequestForm.setStatus(result.getFlag());
          paymentRequestFormRepository.save(paymentRequestForm);
      }
      return ResponseEntity
          .created(new URI("/api/payment-request-form-details/" + result.getId()))
          .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
          .body(result);
  }
}
