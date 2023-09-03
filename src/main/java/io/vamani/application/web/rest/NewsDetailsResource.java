package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.config.ApplicationProperties;
import io.vamani.application.domain.NewsDetails;
import io.vamani.application.domain.NewsDetailsBody;
import io.vamani.application.domain.User;
import io.vamani.application.mssql.domain.EmployeeView;
import io.vamani.application.mssql.repository.EmployeeViewRepository;
import io.vamani.application.repository.NewsDetailsAttachRepository;
import io.vamani.application.repository.NewsDetailsBodyRepository;
import io.vamani.application.repository.NewsDetailsRepository;
import io.vamani.application.repository.UserRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.service.MailService;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.vamani.application.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.validation.Valid;

import java.io.Console;
import java.net.URI;
import java.net.URISyntaxException;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

/**
 * REST controller for managing NewsDetails.
 */
@RestController
@RequestMapping("/api")
public class NewsDetailsResource {

    private final Logger log = LoggerFactory.getLogger(NewsDetailsResource.class);

    private static final String ENTITY_NAME = "newsDetails";

    private final NewsDetailsRepository newsDetailsRepository;

    @Autowired
    private NewsDetailsBodyRepository newsDetailsBodyRepository;

    @Autowired
    private NewsDetailsAttachRepository newsDetailsAttachRepository;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private MailService mailService;

    @Autowired
    private ApplicationProperties applicationProperties;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmployeeViewRepository employeeViewRepository;

    public NewsDetailsResource(NewsDetailsRepository newsDetailsRepository) {
        this.newsDetailsRepository = newsDetailsRepository;
    }

    /**
     * POST  /news-details : Create a new newsDetails.
     *
     * @param newsDetails the newsDetails to create
     * @return the ResponseEntity with status 201 (Created) and with body the new newsDetails, or with status 400 (Bad Request) if the newsDetails has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/news-details")
    @Timed
    public ResponseEntity<NewsDetails> createNewsDetails(@Valid @RequestBody NewsDetails newsDetails) throws URISyntaxException {
        log.debug("REST request to save NewsDetails : {}", newsDetails);
        if (newsDetails.getId() != null) {
            throw new BadRequestAlertException("A new newsDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        newsDetails.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        newsDetails.setCreatedDate(Instant.now());

        LocalDate today = LocalDate.now();

        //adding one day to the localdate
        LocalDate endDate = today.plusDays(newsDetails.getDisplayDays());
        newsDetails.setEndDate(endDate);
        NewsDetails result = newsDetailsRepository.save(newsDetails);
        Set<NewsDetailsBody> newsDetailsBodies = newsDetails.getNewsDetailsBodies();
        for (NewsDetailsBody newsDetailsBody : newsDetailsBodies) {
            newsDetailsBody.setNewsDetails(result);
            newsDetailsBody.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
            newsDetailsBody.setCreatedDate(Instant.now());
            newsDetailsBodyRepository.save(newsDetailsBody);
        }
        return ResponseEntity.created(new URI("/api/news-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /news-details : Updates an existing newsDetails.
     *
     * @param newsDetails the newsDetails to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated newsDetails,
     * or with status 400 (Bad Request) if the newsDetails is not valid,
     * or with status 500 (Internal Server Error) if the newsDetails couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/news-details")
    @Timed
    public ResponseEntity<NewsDetails> updateNewsDetails(@Valid @RequestBody NewsDetails newsDetails) throws URISyntaxException {
        log.debug("REST request to update NewsDetails : {}", newsDetails);
        if (newsDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LocalDate today = LocalDate.now();

        //adding one day to the localdate
        LocalDate endDate = today.plusDays(newsDetails.getDisplayDays());
        newsDetails.setEndDate(endDate);
        NewsDetails result = newsDetailsRepository.save(newsDetails);
        newsDetailsBodyRepository.deleteByNewsDetails(result.getId());
        Set<NewsDetailsBody> newsDetailsBodies = newsDetails.getNewsDetailsBodies();
        for (NewsDetailsBody newsDetailsBody : newsDetailsBodies) {
            newsDetailsBody.setNewsDetails(result);
            newsDetailsBody.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
            newsDetailsBody.setCreatedDate(Instant.now());
            newsDetailsBodyRepository.save(newsDetailsBody);
        }
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, newsDetails.getId().toString()))
            .body(getNewsDetails(result.getId()).getBody());
    }
    
    
    /*
     * 
     */
	@GetMapping("/news-details-closed/{id}")
	@Timed
	public ResponseEntity<NewsDetails> shortCloseById(@PathVariable Long id) {
		NewsDetails details = newsDetailsRepository.findById(id).orElse(null);
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, -1);
		date = calendar.getTime();
		details.setEndDate(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		details.setShortClosedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
		NewsDetails result = newsDetailsRepository.save(details);
		
		return ResponseEntity.ok().body(result);
	}

    /**
     * GET  /news-details : get all the newsDetails.
     *
     * @param pageable the pagination information
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many)
     * @return the ResponseEntity with status 200 (OK) and the list of newsDetails in body
     */
    @GetMapping("/news-details")
    @Timed
    public ResponseEntity<List<NewsDetails>> getAllNewsDetails(@PageableDefault(value = Integer.MAX_VALUE) Pageable pageable, @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get a page of NewsDetails");
        Page<NewsDetails> page;
        if (eagerload) {
            page = newsDetailsRepository.findAllWithEagerRelationships(pageable);
        } else {
            page = newsDetailsRepository.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, String.format("/api/news-details?eagerload=%b", eagerload));
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /news-details : get all the newsDetails.
     *
     * @param pageable the pagination information
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many)
     * @return the ResponseEntity with status 200 (OK) and the list of newsDetails in body
     */
    @GetMapping("/news-details-dashboard")
    @Timed
    public ResponseEntity<List<NewsDetails>> getAllNewsDetailsDashboard(Pageable pageable, @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get a page of NewsDetails");
        List<NewsDetails> newsDetailsList = newsDetailsRepository.findAll(LocalDate.now());
        List<NewsDetails> detailsList = new ArrayList<>();
        for(NewsDetails newsDetails : newsDetailsList) {
            if (newsDetails.getEmpCode() != null && newsDetails.getEmpCode().length() > 0) {
                EmployeeView employeeView = employeeViewRepository.findByCardNo(newsDetails.getEmpCode()).orElse(new EmployeeView());
                newsDetails.setImageUrl(employeeView.getImagePath());
            }
            newsDetails.setNewsDetailsBodies(newsDetailsBodyRepository.findAllByNewsDetailsOrderByIdAsc(newsDetails));
            newsDetails.setNewsDetailsAttaches(newsDetailsAttachRepository.findAllByNewsDetailsOrderByIdAsc(newsDetails));
            detailsList.add(newsDetails);
        }
        return ResponseEntity.ok().body(detailsList);
    }

    /**
     * GET  /news-details/:id : get the "id" newsDetails.
     *
     * @param id the id of the newsDetails to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the newsDetails, or with status 404 (Not Found)
     */
    @GetMapping("/news-details/{id}")
    @Timed
    public ResponseEntity<NewsDetails> getNewsDetails(@PathVariable Long id) {
        log.debug("REST request to get NewsDetails : {}", id);
        NewsDetails newsDetails = newsDetailsRepository.findOneWithEagerRelationships(id).orElse(null);
        newsDetails.setNewsDetailsBodies(newsDetailsBodyRepository.findAllByNewsDetailsOrderByIdAsc(newsDetails));
        newsDetails.setNewsDetailsAttaches(newsDetailsAttachRepository.findAllByNewsDetailsOrderByIdAsc(newsDetails));
        return ResponseUtil.wrapOrNotFound(Optional.of(newsDetails));
    }

    /**
     * GET  /news-details/:id : get the "id" newsDetails.
     *
     * @param id the id of the newsDetails to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the newsDetails, or with status 404 (Not Found)
     */
    @GetMapping("/news-details-approve/{id}")
    @Timed
    public ResponseEntity<NewsDetails> approveNewsDetails(@PathVariable Long id) {
        log.debug("REST request to get NewsDetails : {}", id);
        NewsDetails newsDetails = newsDetailsRepository.findOneWithEagerRelationships(id).orElse(null);
        newsDetails.setApprovedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        newsDetails.setApprovedDate(Instant.now());
        newsDetails.setFlag("A");
        NewsDetails result = newsDetailsRepository.save(newsDetails);
        result.setNewsDetailsBodies(newsDetailsBodyRepository.findAllByNewsDetailsOrderByIdAsc(newsDetails));
        result.setNewsDetailsAttaches(newsDetailsAttachRepository.findAllByNewsDetailsOrderByIdAsc(newsDetails));
        if (result.isMailFlag() != null && result.isMailFlag().booleanValue() == true) {
            List<String> users = userRepository.findAllActivatedUser();
            List<EmployeeView> employeeViews = employeeViewRepository.findAllByLogins(users);
            if (employeeViews != null && employeeViews.size() > 0) {
                for (EmployeeView employeeView : employeeViews) {
                    if (employeeView.getEmail() != null && employeeView.getEmail().trim().length() > 0) {
                        Locale locale = Locale.forLanguageTag("en");
                        Context context = new Context(locale);
                        context.setVariable("fullName", employeeView.getName());
                        context.setVariable("newsSubject", result.getNewsTitle());
                        String content = null;
                        String subject = "Urgent message on IntraNet!!!";
                        try {
                            content = templateEngine.process("mail/news_mail", context);
                            mailService.sendEmail(employeeView.getEmail(), subject, content, false, true);
                            // mailService.sendEmail("vivekjaiswal@vamanioverseas.com", subject, content, false, true);
                            // mailService.sendEmail("sudeepsingh@vamanioverseas.com", subject, content, false, true);
                        } catch (Exception e) {
                        }
                    }
                }
            }
        }
        return ResponseUtil.wrapOrNotFound(Optional.of(result));
    }

    /**
     * DELETE  /news-details/:id : delete the "id" newsDetails.
     *
     * @param id the id of the newsDetails to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/news-details/{id}")
    @Timed
    public ResponseEntity<Void> deleteNewsDetails(@PathVariable Long id) {
        log.debug("REST request to delete NewsDetails : {}", id);

        newsDetailsRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
