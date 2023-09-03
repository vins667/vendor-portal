package io.vamani.application.vendorportal.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.web.util.ResponseUtil;
import io.vamani.application.config.ApplicationProperties;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.service.MailService;
import io.vamani.application.vendorportal.domain.*;
import io.vamani.application.vendorportal.repository.*;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.vamani.application.web.rest.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.Base64;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

/**
 * REST controller for managing EmailInvitation.
 */
@RestController
@RequestMapping("/api")
public class EmailInvitationResource {

    private final Logger log = LoggerFactory.getLogger(EmailInvitationResource.class);

    private static final String ENTITY_NAME = "emailInvitation";

    private final EmailInvitationRepository emailInvitationRepository;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private MailService mailService;

    @Autowired
    private ApplicationProperties applicationProperties;

    @Autowired
    private UserVendorRepository userVendorRepository;

    @Autowired
    private VendorsRepository vendorsRepository;

    @Autowired
    private EmailInvitationSkipRepository emailInvitationSkipRepository;

    public EmailInvitationResource(EmailInvitationRepository emailInvitationRepository) {
        this.emailInvitationRepository = emailInvitationRepository;
    }

    /**
     * POST  /email-invitations : Create a new emailInvitation.
     *
     * @param emailInvitation the emailInvitation to create
     * @return the ResponseEntity with status 201 (Created) and with body the new emailInvitation, or with status 400 (Bad Request) if the emailInvitation has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/email-invitations")
    @Timed
    public ResponseEntity<EmailInvitation> createEmailInvitation(@Valid @RequestBody EmailInvitation emailInvitation) throws URISyntaxException {
        log.debug("REST request to save EmailInvitation : {}", emailInvitation);
        if (emailInvitation.getId() != null) {
            throw new BadRequestAlertException("A new emailInvitation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EmailInvitation invitation = emailInvitationRepository.findByEmailId(emailInvitation.getEmailId().trim().toLowerCase());
        if(invitation != null){
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, invitation.getId().toString(), "Email Id already Exist!!!"))
                .body(invitation);
        } else {
            String email = emailInvitation.getEmailId().trim().toLowerCase();

            String domain = email.substring(email.lastIndexOf("@") + 1, email.length());

            EmailInvitationSkip emailInvitationSkip = emailInvitationSkipRepository.findById(domain).orElse(null);

            if (emailInvitationSkip != null) {
            } else {
                List<Vendors> vendors = vendorsRepository.findByEmailDomain(domain);
                if (vendors != null && vendors.size() > 0) {
                    return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "", "Vendor already register with \'" + domain + "\'."))
                        .body(new EmailInvitation());
                }
            }

            UserVendor userVendor = userVendorRepository.findByEmail(emailInvitation.getEmailId().trim().toLowerCase());
            if(userVendor != null && userVendor.getId() != null){
                return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "", "Email Id already Exist!!!"))
                    .body(new EmailInvitation());
            } else {
                emailInvitation.setEmailId(emailInvitation.getEmailId().trim().toLowerCase());
                emailInvitation.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                emailInvitation.setCreatedDate(Instant.now());
                EmailInvitation result = emailInvitationRepository.save(emailInvitation);
                if (result != null) {
                    Locale locale = Locale.forLanguageTag("en");
                    Context context = new Context(locale);
                    context.setVariable("url", applicationProperties.getVendorPortalUrl() + "#/register-vendor/" + result.getId());
                    String content = null;
                    String subject = "Vendor portal register request";
                    try {
                        content = templateEngine.process("mail/invite_mail", context);
                        mailService.sendEmail(result.getEmailId(), subject, content, false, true);
                    } catch (Exception e) {
                    }
                }
                return ResponseEntity.created(new URI("/api/email-invitations/" + result.getId()))
                    .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                    .body(result);
            }
        }
    }

    /**
     * PUT  /email-invitations : Updates an existing emailInvitation.
     *
     * @param emailInvitation the emailInvitation to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated emailInvitation,
     * or with status 400 (Bad Request) if the emailInvitation is not valid,
     * or with status 500 (Internal Server Error) if the emailInvitation couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/email-invitations")
    @Timed
    public ResponseEntity<EmailInvitation> updateEmailInvitation(@Valid @RequestBody EmailInvitation emailInvitation) throws URISyntaxException {
        log.debug("REST request to update EmailInvitation : {}", emailInvitation);
        if (emailInvitation.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EmailInvitation result = emailInvitationRepository.save(emailInvitation);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, emailInvitation.getId().toString()))
            .body(result);
    }

    /**
     * GET  /email-invitations : get all the emailInvitations.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of emailInvitations in body
     */
    @GetMapping("/email-invitations")
    @Timed
    public ResponseEntity<List<EmailInvitation>> getAllEmailInvitations(Pageable pageable) {
        log.debug("REST request to get a page of EmailInvitations");
        Page<EmailInvitation> page = emailInvitationRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/email-invitations");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /email-invitations/:id : get the "id" emailInvitation.
     *
     * @param id the id of the emailInvitation to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the emailInvitation, or with status 404 (Not Found)
     */
    @GetMapping("/email-invitations/{id}")
    @Timed
    public ResponseEntity<EmailInvitation> getEmailInvitation(@PathVariable Long id) {
        log.debug("REST request to get EmailInvitation : {}", id);
        Optional<EmailInvitation> emailInvitation = emailInvitationRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(emailInvitation);
    }

    /**
     * GET  /email-invitations/:id : get the "id" emailInvitation.
     *
     * @param id the id of the emailInvitation to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the emailInvitation, or with status 404 (Not Found)
     */
    @GetMapping("/email-invitations-resend/{id}")
    @Timed
    public ResponseEntity<EmailInvitation> getEmailInvitationResend(@PathVariable Long id) {
        log.debug("REST request to get EmailInvitation : {}", id);
        EmailInvitation emailInvitation = emailInvitationRepository.findById(id).orElse(null);
        if (emailInvitation != null) {
            Locale locale = Locale.forLanguageTag("en");
            Context context = new Context(locale);
            context.setVariable("url", applicationProperties.getVendorPortalUrl() + "#/register-vendor/" + emailInvitation.getId());
            String content = null;
            String subject = "Vendor portal register request";
            try {
                content = templateEngine.process("mail/invite_mail", context);
                mailService.sendEmail(emailInvitation.getEmailId(), subject, content, false, true);
            } catch (Exception e) {
            }
        }
        return ResponseUtil.wrapOrNotFound(Optional.of(emailInvitation));
    }

    /**
     * DELETE  /email-invitations/:id : delete the "id" emailInvitation.
     *
     * @param id the id of the emailInvitation to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/email-invitations/{id}")
    @Timed
    public ResponseEntity<Void> deleteEmailInvitation(@PathVariable Long id) {
        log.debug("REST request to delete EmailInvitation : {}", id);

        emailInvitationRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
