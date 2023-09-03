package io.vamani.application.db2.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.web.util.PaginationUtil;
import io.vamani.application.db2.domain.Gateentry;
import io.vamani.application.db2.domain.Gateentry;
import io.vamani.application.db2.repository.GateentryRepository;
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

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * REST controller for managing {@link Gateentry}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class GateentryResource {
    private final Logger log = LoggerFactory.getLogger(GateentryResource.class);

    private static final String ENTITY_NAME = "gateentry";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GateentryRepository gateentryRepository;

    public GateentryResource(GateentryRepository gateentryRepository) {
        this.gateentryRepository = gateentryRepository;
    }

    @GetMapping("/gateentries/{factorycode}/{maingateentrysrno}")
    @Timed
    public ResponseEntity<List<Gateentry>> getAllMaingateentrysrno(@PathVariable String factorycode, @PathVariable String maingateentrysrno) throws UnsupportedEncodingException {
        log.debug("REST request to get a page of gateentry");
        Pageable pageable = PageRequest.of(0, 50, Sort.by("id.maingateentrysrno").ascending());
        maingateentrysrno = URLDecoder.decode(maingateentrysrno, "UTF-8");
        Page<Gateentry> page = gateentryRepository.findAllByMaingateentrysrnoIgnoreCaseLike("%" + factorycode + "%", "%" + maingateentrysrno.toUpperCase() + "%", pageable);

        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/gateentries-by-passno/{factorycode}/{gatepassno}")
    @Timed
    public ResponseEntity<List<Gateentry>> getAllGatepassno(@PathVariable String factorycode, @PathVariable String gatepassno) throws UnsupportedEncodingException {
        log.debug("REST request to get a page of gateentry");
        Pageable pageable = PageRequest.of(0, 50, Sort.by("gatepassno").ascending());
        gatepassno = URLDecoder.decode(gatepassno, "UTF-8");
        Page<Gateentry> page = gateentryRepository.findAllByGatepassnoIgnoreCaseLike("%" + factorycode + "%", "%" + gatepassno.toUpperCase() + "%", pageable);

        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
