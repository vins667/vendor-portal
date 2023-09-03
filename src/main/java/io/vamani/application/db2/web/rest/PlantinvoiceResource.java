package io.vamani.application.db2.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.web.util.PaginationUtil;
import io.vamani.application.db2.domain.Plantinvoice;
import io.vamani.application.db2.domain.PlantinvoiceView;
import io.vamani.application.db2.model.PlantinvoiceSearch;
import io.vamani.application.db2.repository.PlantinvoiceRepository;
import io.vamani.application.db2.repository.PlantinvoiceViewRepository;
import io.vamani.application.domain.BillRegister;
import io.vamani.application.domain.BillRegisterDetails;
import io.vamani.application.model.BillRegisterBean;
import io.vamani.application.model.BillRegisterTemp;
import io.vamani.application.model.MasterSearch;
import io.vamani.application.repository.BillRegisterDetailsRepository;
import io.vamani.application.repository.BillRegisterRepository;
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
import org.springframework.web.util.UriUtils;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link Plantinvoice}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class PlantinvoiceResource {
    private final Logger log = LoggerFactory.getLogger(PlantinvoiceResource.class);

    private static final String ENTITY_NAME = "plantinvoice";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PlantinvoiceRepository plantinvoiceRepository;

    @Autowired
    private PlantinvoiceViewRepository plantinvoiceViewRepository;

    @Autowired
    private BillRegisterRepository billRegisterRepository;

    @Autowired
    private BillRegisterDetailsRepository billRegisterDetailsRepository;

    public PlantinvoiceResource(PlantinvoiceRepository plantinvoiceRepository) {
        this.plantinvoiceRepository = plantinvoiceRepository;
    }

    @GetMapping("/plantinvoices/{plantinvoicecode}")
    @Timed
    public ResponseEntity<List<PlantinvoiceView>> getAllPlantinvoice(@PathVariable String plantinvoicecode) throws UnsupportedEncodingException {
        log.debug("REST request to get a page of plantinvoice");
        Pageable pageable = PageRequest.of(0, 50, Sort.by("challanno").ascending());
        plantinvoicecode = UriUtils.decode(plantinvoicecode, "UTF-8").toUpperCase();
        // plantinvoicecode = URLDecoder.decode(plantinvoicecode, "UTF-8").toUpperCase();
        Page<PlantinvoiceView> page = plantinvoiceViewRepository.findAllByPlantinvoiceIgnoreCaseLike("%"+plantinvoicecode+"%", pageable);

        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @PostMapping("/plantinvoices-filter")
    @Timed
    public ResponseEntity<List<PlantinvoiceView>> getAllPlantinvoiceFilter(@RequestBody PlantinvoiceSearch search) throws UnsupportedEncodingException {
        log.debug("REST request to get a page of plantinvoice");
        search.setPage(PageRequest.of(search.getPageNo(), search.getSize(), Sort.by("id.code")));
        String code = "%";
        if (search.getCode() != null) {
            code = "%" + search.getCode().toUpperCase() + "%";
        }
        Page<PlantinvoiceView> page = plantinvoiceViewRepository.findAllByPlantinvoiceIgnoreCaseLike(code, search.getPage());
        HttpHeaders headers = io.vamani.application.web.rest.util.PaginationUtil.generatePaginationHttpHeaders(page, "/api/productionorders");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @PostMapping("/plantinvoices-bill-register")
    @Timed
    public ResponseEntity<Optional<BillRegisterTemp>> getBillDetail(@RequestBody MasterSearch search) throws UnsupportedEncodingException {
        log.debug("REST request to get a page of plantinvoice");
        BillRegisterTemp billRegisterBean = new BillRegisterTemp();
        // plantinvoicecode = java.net.URLDecoder.decode(plantinvoicecode, StandardCharsets.UTF_8.name());
        List<Object[]> objects = plantinvoiceRepository.fetchDetailsByInvoiceCode(search.getCode());
        if (objects != null && objects.size()>0) {
            Object[] object = objects.get(0);
            billRegisterBean.setCustomercode(object[0].toString());
            billRegisterBean.setCustomername(object[1].toString());
            billRegisterBean.setStyle(object[2].toString());
            billRegisterBean.setQuantity((BigDecimal) object[3]);
        }
        return ResponseEntity.ok().body(Optional.of(billRegisterBean));
    }
}
