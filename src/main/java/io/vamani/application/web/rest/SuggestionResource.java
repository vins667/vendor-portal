package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.domain.Suggestion;
import io.vamani.application.mssql.domain.EmployeeView;
import io.vamani.application.mssql.repository.EmployeeViewRepository;
import io.vamani.application.repository.SuggestionRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.service.MailService;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.time.Instant;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

/**
 * REST controller for managing Suggestion.
 */
@RestController
@RequestMapping("/api")
public class SuggestionResource {

    private final Logger log = LoggerFactory.getLogger(SuggestionResource.class);

    private static final String ENTITY_NAME = "suggestion";

    private final SuggestionRepository suggestionRepository;

    @Autowired
    private EmployeeViewRepository employeeViewRepository;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private MailService mailService;

    public SuggestionResource(SuggestionRepository suggestionRepository) {
        this.suggestionRepository = suggestionRepository;
    }

    /**
     * POST  /suggestions : Create a new suggestion.
     *
     * @param suggestion the suggestion to create
     * @return the ResponseEntity with status 201 (Created) and with body the new suggestion, or with status 400 (Bad Request) if the suggestion has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/suggestions")
    @Timed
    public ResponseEntity<Suggestion> createSuggestion(@Valid @RequestBody Suggestion suggestion) throws URISyntaxException {
        log.debug("REST request to save Suggestion : {}", suggestion);
        if (suggestion.getId() != null) {
            throw new BadRequestAlertException("A new suggestion cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EmployeeView requester = employeeViewRepository.findById(SecurityUtils.getCurrentUserLogin().orElse(null)).orElse(null);
        suggestion.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        suggestion.setCreatedDate(Instant.now());
        Suggestion result = suggestionRepository.save(suggestion);
        if(requester != null){
            Locale locale = Locale.forLanguageTag("en");
            Context context = new Context(locale);
            context.setVariable("suggestionText", suggestion.getSuggestionText());
            context.setVariable("fromName", requester.getName());
            context.setVariable("fromEmail", requester.getEmail());
            String content = null;
            String subject = "Suggestion from Pulse(Vamani Portal) By "+requester.getName();
            try {
                content = templateEngine.process("mail/suggestion_mail", context);
                mailService.sendEmail("suggestions@vamanioverseas.com", subject, content, false, true);
            } catch (Exception e) {
            }
        }
        return ResponseEntity.created(new URI("/api/suggestions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /suggestions : Updates an existing suggestion.
     *
     * @param suggestion the suggestion to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated suggestion,
     * or with status 400 (Bad Request) if the suggestion is not valid,
     * or with status 500 (Internal Server Error) if the suggestion couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/suggestions")
    @Timed
    public ResponseEntity<Suggestion> updateSuggestion(@Valid @RequestBody Suggestion suggestion) throws URISyntaxException {
        log.debug("REST request to update Suggestion : {}", suggestion);
        if (suggestion.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Suggestion result = suggestionRepository.save(suggestion);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, suggestion.getId().toString()))
            .body(result);
    }

    /**
     * GET  /suggestions : get all the suggestions.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of suggestions in body
     */
    @GetMapping("/suggestions")
    @Timed
    public List<Suggestion> getAllSuggestions() {
        log.debug("REST request to get all Suggestions");
        return suggestionRepository.findAll();
    }

    /**
     * GET  /suggestions/:id : get the "id" suggestion.
     *
     * @param id the id of the suggestion to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the suggestion, or with status 404 (Not Found)
     */
    @GetMapping("/suggestions/{id}")
    @Timed
    public ResponseEntity<Suggestion> getSuggestion(@PathVariable Long id) {
        log.debug("REST request to get Suggestion : {}", id);
        Optional<Suggestion> suggestion = suggestionRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(suggestion);
    }

    /**
     * DELETE  /suggestions/:id : delete the "id" suggestion.
     *
     * @param id the id of the suggestion to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/suggestions/{id}")
    @Timed
    public ResponseEntity<Void> deleteSuggestion(@PathVariable Long id) {
        log.debug("REST request to delete Suggestion : {}", id);

        suggestionRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
