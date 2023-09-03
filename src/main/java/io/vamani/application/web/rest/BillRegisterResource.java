package io.vamani.application.web.rest;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.vamani.application.domain.BillRegister;
import io.vamani.application.domain.BillRegisterDetails;
import io.vamani.application.model.BillRegisterBean;
import io.vamani.application.model.BillRegisterSearch;
import io.vamani.application.repository.BillRegisterDetailsRepository;
import io.vamani.application.repository.BillRegisterRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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

/**
 * REST controller for managing {@link BillRegister}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class BillRegisterResource {

    private final Logger log = LoggerFactory.getLogger(BillRegisterResource.class);

    private static final String ENTITY_NAME = "billRegister";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BillRegisterRepository billRegisterRepository;

    @Autowired
    private BillRegisterDetailsRepository billRegisterDetailsRepository;

    public BillRegisterResource(BillRegisterRepository billRegisterRepository) {
        this.billRegisterRepository = billRegisterRepository;
    }
}
