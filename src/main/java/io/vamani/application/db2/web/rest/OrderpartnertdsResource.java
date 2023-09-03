package io.vamani.application.db2.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.db2.domain.Orderpartnertds;
import io.vamani.application.db2.repository.OrderpartnertdsRepository;
import io.vamani.application.model.Master;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@Transactional
public class OrderpartnertdsResource {
    private final Logger log = LoggerFactory.getLogger(OrderpartnertdsResource.class);

    private static final String ENTITY_NAME = "orderpartnertds";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OrderpartnertdsRepository orderpartnertdsRepository;

    public OrderpartnertdsResource(OrderpartnertdsRepository orderpartnertdsRepository) {
        this.orderpartnertdsRepository = orderpartnertdsRepository;
    }

    @PostMapping("/db2-orderpartnertds")
    @Timed
    public ResponseEntity<List<Orderpartnertds>> getAllOrderPartnerTds(@RequestBody Master master) {
        log.debug("REST request to get a page of orderpartnertds");
        List<Orderpartnertds> page = orderpartnertdsRepository.findAllBySuppliercode(master.getId(), master.getName(), master.getDesc().trim());
        return ResponseEntity.ok().body(page);
    }
}
