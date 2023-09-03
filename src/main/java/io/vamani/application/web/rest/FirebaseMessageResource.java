package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.domain.FirebaseMessage;
import io.vamani.application.repository.FirebaseMessageRepository;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.vamani.application.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing FirebaseMessage.
 */
@RestController
@RequestMapping("/api")
public class FirebaseMessageResource {

    private final Logger log = LoggerFactory.getLogger(FirebaseMessageResource.class);

    private static final String ENTITY_NAME = "firebaseMessage";

    private final FirebaseMessageRepository firebaseMessageRepository;

    public FirebaseMessageResource(FirebaseMessageRepository firebaseMessageRepository) {
        this.firebaseMessageRepository = firebaseMessageRepository;
    }

    /**
     * POST  /firebase-messages : Create a new firebaseMessage.
     *
     * @param firebaseMessage the firebaseMessage to create
     * @return the ResponseEntity with status 201 (Created) and with body the new firebaseMessage, or with status 400 (Bad Request) if the firebaseMessage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/firebase-messages")
    @Timed
    public ResponseEntity<FirebaseMessage> createFirebaseMessage(@Valid @RequestBody FirebaseMessage firebaseMessage) throws URISyntaxException {
        log.debug("REST request to save FirebaseMessage : {}", firebaseMessage);
        if (firebaseMessage.getId() != null) {
            throw new BadRequestAlertException("A new firebaseMessage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FirebaseMessage result = firebaseMessageRepository.save(firebaseMessage);
        return ResponseEntity.created(new URI("/api/firebase-messages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /firebase-messages : Updates an existing firebaseMessage.
     *
     * @param firebaseMessage the firebaseMessage to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated firebaseMessage,
     * or with status 400 (Bad Request) if the firebaseMessage is not valid,
     * or with status 500 (Internal Server Error) if the firebaseMessage couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/firebase-messages")
    @Timed
    public ResponseEntity<FirebaseMessage> updateFirebaseMessage(@Valid @RequestBody FirebaseMessage firebaseMessage) throws URISyntaxException {
        log.debug("REST request to update FirebaseMessage : {}", firebaseMessage);
        if (firebaseMessage.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FirebaseMessage result = firebaseMessageRepository.save(firebaseMessage);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, firebaseMessage.getId().toString()))
            .body(result);
    }

    /**
     * GET  /firebase-messages : get all the firebaseMessages.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of firebaseMessages in body
     */
    @GetMapping("/firebase-messages")
    @Timed
    public ResponseEntity<List<FirebaseMessage>> getAllFirebaseMessages(Pageable pageable) {
        log.debug("REST request to get a page of FirebaseMessages");
        Page<FirebaseMessage> page = firebaseMessageRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/firebase-messages");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /firebase-messages/:id : get the "id" firebaseMessage.
     *
     * @param id the id of the firebaseMessage to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the firebaseMessage, or with status 404 (Not Found)
     */
    @GetMapping("/firebase-messages/{id}")
    @Timed
    public ResponseEntity<FirebaseMessage> getFirebaseMessage(@PathVariable Long id) {
        log.debug("REST request to get FirebaseMessage : {}", id);
        Optional<FirebaseMessage> firebaseMessage = firebaseMessageRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(firebaseMessage);
    }

    /**
     * DELETE  /firebase-messages/:id : delete the "id" firebaseMessage.
     *
     * @param id the id of the firebaseMessage to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/firebase-messages/{id}")
    @Timed
    public ResponseEntity<Void> deleteFirebaseMessage(@PathVariable Long id) {
        log.debug("REST request to delete FirebaseMessage : {}", id);

        firebaseMessageRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
