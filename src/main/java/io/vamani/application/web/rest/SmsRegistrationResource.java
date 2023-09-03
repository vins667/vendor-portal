package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.config.SmsConfiguration;
import io.vamani.application.domain.SmsRegistration;
import io.vamani.application.domain.User;
import io.vamani.application.model.OtpSystem;
import io.vamani.application.model.ValidateOtp;
import io.vamani.application.mssql.domain.EmployeeView;
import io.vamani.application.mssql.repository.EmployeeViewRepository;
import io.vamani.application.repository.SmsRegistrationRepository;
import io.vamani.application.repository.UserRepository;
import io.vamani.application.service.UserService;
import io.vamani.application.service.dto.UserDTO;
import io.vamani.application.service.util.RandomUtil;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import java.time.Instant;
import java.util.*;

/**
 * REST controller for managing SmsRegistration.
 */
@RestController
@RequestMapping("/api")
public class SmsRegistrationResource {

    private final Logger log = LoggerFactory.getLogger(SmsRegistrationResource.class);

    private static final String ENTITY_NAME = "smsRegistration";

    private final SmsRegistrationRepository smsRegistrationRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmployeeViewRepository employeeViewRepository;

    @Autowired
    private SmsConfiguration smsConfiguration;

    private static final Random generator = new Random();

    public SmsRegistrationResource(SmsRegistrationRepository smsRegistrationRepository) {
        this.smsRegistrationRepository = smsRegistrationRepository;
    }

    /**
     * POST  /sms-registrations : Create a new smsRegistration.
     *
     * @param smsRegistration the smsRegistration to create
     * @return the ResponseEntity with status 201 (Created) and with body the new smsRegistration, or with status 400 (Bad Request) if the smsRegistration has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/sms-registrations")
    @Timed
    public ResponseEntity<io.vamani.application.mobile.SmsRegistration> createSmsRegistration(@Valid @RequestBody SmsRegistration smsRegistration) throws URISyntaxException {
        log.debug("REST request to save SmsRegistration : {}", smsRegistration);
        if (smsRegistration.getId() != null) {
            throw new BadRequestAlertException("A new smsRegistration cannot already have an ID", ENTITY_NAME, "idexists");
        }
        io.vamani.application.mobile.SmsRegistration mRegistration = new io.vamani.application.mobile.SmsRegistration();
        SmsRegistration registration = smsRegistrationRepository.findOneByCardNo(smsRegistration.getCardNo());
        EmployeeView employeeView = employeeViewRepository.findByCardNo(smsRegistration.getCardNo()).orElse(null);
        if(employeeView.getPhone() != null &&
            employeeView.getPhone().startsWith(smsRegistration.getMobileNumber().substring(0,2)) &&
            employeeView.getPhone().endsWith(smsRegistration.getMobileNumber().substring(smsRegistration.getMobileNumber().length()-3, smsRegistration.getMobileNumber().length()))) {
        } else {
            mRegistration.setExist(false);
            mRegistration.setErrorMessage("Invalid credentials");
            return ResponseEntity.ok()
                .body(mRegistration);
        }
        User user = userRepository.findOneByLogin(employeeView.getLogin().toLowerCase()).orElse(null);
        if(user != null){
            mRegistration.setExist(false);
            mRegistration.setErrorMessage("Already registered.");
            return ResponseEntity.ok()
                .body(mRegistration);
        }
        else {
            int otp = generatePin();
            if(registration != null) {
                registration.setOtp(otp+"");
                registration.setMobileNumber(employeeView.getPhone());
                registration.createdDate(Instant.now());
            } else {
                registration = smsRegistration;
                registration.setOtp(otp+"");
                registration.setMobileNumber(employeeView.getPhone());
                registration.createdDate(Instant.now());
            }
            String smsBody = "Dear "+employeeView.getName()+", OTP for registration on vamani portal is "+otp+".";
            try {
                 smsConfiguration.postSms(new OtpSystem(employeeView.getPhone(), smsBody));
            } catch(Exception e) {log.debug("Error : {}", e.getMessage());}
            SmsRegistration result = smsRegistrationRepository.save(registration);
            BeanUtils.copyProperties(result, mRegistration);
            mRegistration.setExist(true);
            return ResponseEntity.created(new URI("/api/sms-registrations/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(mRegistration);
        }
    }

    /**
     * POST  /sms-registrations : Create a new smsRegistration.
     *
     * @param smsRegistration the smsRegistration to create
     * @return the ResponseEntity with status 201 (Created) and with body the new smsRegistration, or with status 400 (Bad Request) if the smsRegistration has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/sms-force-logout")
    @Timed
    public ResponseEntity<io.vamani.application.mobile.SmsRegistration> createSmsForceRegistration(@Valid @RequestBody SmsRegistration smsRegistration) throws URISyntaxException {
        log.debug("REST request to save SmsRegistration : {}", smsRegistration);
        if (smsRegistration.getId() != null) {
            throw new BadRequestAlertException("A new smsRegistration cannot already have an ID", ENTITY_NAME, "idexists");
        }
        io.vamani.application.mobile.SmsRegistration mRegistration = new io.vamani.application.mobile.SmsRegistration();
        SmsRegistration registration = smsRegistrationRepository.findOneByCardNo(smsRegistration.getCardNo());
        EmployeeView employeeView = employeeViewRepository.findByCardNo(smsRegistration.getCardNo()).orElse(null);
        if(employeeView.getPhone() != null &&
            employeeView.getPhone().startsWith(smsRegistration.getMobileNumber().substring(0,2)) &&
            employeeView.getPhone().endsWith(smsRegistration.getMobileNumber().substring(smsRegistration.getMobileNumber().length()-3, smsRegistration.getMobileNumber().length()))) {
        } else {
            mRegistration.setExist(false);
            mRegistration.setErrorMessage("Invalid credentials");
            return ResponseEntity.ok()
                .body(mRegistration);
        }
        //User user = userRepository.findOneByLogin(employeeView.getLogin().toLowerCase()).orElse(null);

        int otp = generatePin();
        if (registration != null) {
            registration.setOtp(otp + "");
            registration.setMobileNumber(employeeView.getPhone());
            registration.createdDate(Instant.now());
        } else {
            registration = smsRegistration;
            registration.setOtp(otp + "");
            registration.createdDate(Instant.now());
        }
        String smsBody = "Dear " + employeeView.getName() + ", OTP for force logout on vamani portal is " + otp + ".";
        try {
            smsConfiguration.postSms(new OtpSystem(employeeView.getPhone(), smsBody));
        } catch (Exception e) {
            log.debug("Error : {}", e.getMessage());
        }
        SmsRegistration result = smsRegistrationRepository.save(registration);
        BeanUtils.copyProperties(result, mRegistration);
        mRegistration.setExist(true);
        return ResponseEntity.created(new URI("/api/sms-force-logout/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(mRegistration);
    }

    @PostMapping("/otp-validate-force-logout")
    @Timed
    public ResponseEntity<io.vamani.application.mobile.User> validateOtpForceLogout(@Valid @RequestBody ValidateOtp validateOtp) throws URISyntaxException, IOException {
        io.vamani.application.mobile.User user = new io.vamani.application.mobile.User();
        SmsRegistration registration = smsRegistrationRepository.findOneByCardNo(validateOtp.getCardNo());
        if(registration != null) {
            if (registration.getMobileNumber() != null &&
                registration.getMobileNumber().startsWith(validateOtp.getMobileNumber().substring(0,2)) &&
                registration.getMobileNumber().endsWith(validateOtp.getMobileNumber().substring(validateOtp.getMobileNumber().length()-3,validateOtp.getMobileNumber().length()))
                && registration.getOtp() != null && registration.getOtp().equalsIgnoreCase(validateOtp.getOtp())) {
                User userValidate = userRepository.findOneByLogin(validateOtp.getCardNo()).orElse(null);
                userValidate.setImei(null);
                userValidate.setSerial(null);
                userValidate.setDeviceId(null);
                User result = userRepository.save(userValidate);
                BeanUtils.copyProperties(userValidate, user);
                user.setExist(true);
                return ResponseEntity.ok()
                    .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                    .body(user);

            } else {
                user.setExist(false);
                user.setErrorMessage("Invalid Otp.");
                return ResponseEntity.ok()
                    .headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "", "Invalid Otp"))
                    .body(user);
            }
        } else {
            user.setExist(false);
            user.setErrorMessage("Invalid Otp.");
            return ResponseEntity.ok()
                .headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "", "Invalid Otp"))
                .body(user);
        }
    }

    @PostMapping("/otp-validate")
    @Timed
    public ResponseEntity<io.vamani.application.mobile.User> validateOtp(@Valid @RequestBody ValidateOtp validateOtp) throws URISyntaxException, IOException {
        io.vamani.application.mobile.User user = new io.vamani.application.mobile.User();
        SmsRegistration registration = smsRegistrationRepository.findOneByCardNo(validateOtp.getCardNo());
        if(registration != null) {
            if (registration.getMobileNumber() != null &&
                registration.getMobileNumber().startsWith(validateOtp.getMobileNumber().substring(0,2)) &&
                registration.getMobileNumber().endsWith(validateOtp.getMobileNumber().substring(validateOtp.getMobileNumber().length()-3,validateOtp.getMobileNumber().length()))
                && registration.getOtp() != null && registration.getOtp().equalsIgnoreCase(validateOtp.getOtp())) {
                String firstName = "";
                String lastName = "";
                EmployeeView employeeView = employeeViewRepository.findByCardNo(validateOtp.getCardNo()).orElse(null);
                if(employeeView.getName().lastIndexOf(" ") != -1) {
                    firstName = employeeView.getName().substring(0, employeeView.getName().lastIndexOf(" "));
                    lastName = employeeView.getName().substring(employeeView.getName().lastIndexOf(" ") + 1, employeeView.getName().length());
                } else {
                    firstName = employeeView.getName();
                    lastName = "";
                }
                UserDTO userDTO = new UserDTO();
                userDTO.setLogin(employeeView.getLogin());
                userDTO.setFirstName(firstName.toUpperCase());
                userDTO.setLastName(lastName.toUpperCase());
                if (employeeView.getEmail() != null && employeeView.getEmail().length()>0 && userRepository.findOneByEmailIgnoreCase(employeeView.getEmail()).isPresent()) {
                } else if(employeeView.getEmail() != null && employeeView.getEmail().length()>0){
                    userDTO.setEmail(employeeView.getEmail().toLowerCase());
                }
                userDTO.setLangKey("en");
                userDTO.setActivated(true);
                userDTO.setCreatedBy("anonymousUser");
                userDTO.setCreatedDate(Instant.now());
                String role = "ROLE_USER";
                Set authorities = new HashSet();
                authorities.add(role);
                userDTO.setAuthorities(authorities);
                String encryptedPassword = RandomUtil.generatePassword().toUpperCase();
                User newUser = userService.createUser(userDTO, encryptedPassword);
                BeanUtils.copyProperties(newUser, user);
                user.setExist(true);
                String smsBody = "Dear "+employeeView.getName()+", You have successfully registered on vamani portal. Your portal password is "+encryptedPassword+".";
                smsConfiguration.postSms(new OtpSystem(registration.getMobileNumber(), smsBody));
                return ResponseEntity.ok()
                    .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, newUser.getId().toString()))
                    .body(user);

            } else {
                user.setExist(false);
                user.setErrorMessage("Invalid Otp.");
                return ResponseEntity.ok()
                    .headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "", "Invalid Otp"))
                    .body(user);
            }
        } else {
            user.setExist(false);
            user.setErrorMessage("Invalid Otp.");
            return ResponseEntity.ok()
                .headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "", "Invalid Otp"))
                .body(user);
        }
    }

    @PostMapping("/reset-user-password")
    @Timed
    public ResponseEntity<io.vamani.application.mobile.User> resetPassword(@Valid @RequestBody ValidateOtp validateOtp) throws URISyntaxException, IOException {
        EmployeeView employeeView = employeeViewRepository.findByCardNo(validateOtp.getCardNo()).orElse(null);
        User user = userRepository.findOneByLogin(employeeView.getLogin().toLowerCase()).orElse(null);
        io.vamani.application.mobile.User user1 = new io.vamani.application.mobile.User();
        if (user != null) {
            if(employeeView.getPhone() != null &&
                employeeView.getPhone().startsWith(validateOtp.getMobileNumber().substring(0,2)) &&
                employeeView.getPhone().endsWith(validateOtp.getMobileNumber().substring(validateOtp.getMobileNumber().length()-3, validateOtp.getMobileNumber().length()))) {
                String encryptedPassword = RandomUtil.generatePassword().toUpperCase();
                userService.changePassword(user, encryptedPassword);
                String smsBody = "Dear " + employeeView.getName() + ", You have successfully reset your password on vamani portal. Your new portal password is " + encryptedPassword + ".";
                smsConfiguration.postSms(new OtpSystem(employeeView.getPhone(), smsBody));
                BeanUtils.copyProperties(user, user1);
                user1.setExist(true);
                return ResponseEntity.ok()
                    .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, user.getId().toString()))
                    .body(user1);
            } else {
                user1.setExist(false);
                user1.setErrorMessage("Invalid credentials.");
                return ResponseEntity.ok()
                    .headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "", "You are not a registered user."))
                    .body(user1);
            }
        } else {
            user1.setExist(false);
            user1.setErrorMessage("You are not a registered user.");
            return ResponseEntity.ok()
                .headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "", "You are not a registered user."))
                .body(user1);
        }
    }


    /**
     * PUT  /sms-registrations : Updates an existing smsRegistration.
     *
     * @param smsRegistration the smsRegistration to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated smsRegistration,
     * or with status 400 (Bad Request) if the smsRegistration is not valid,
     * or with status 500 (Internal Server Error) if the smsRegistration couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/sms-registrations")
    @Timed
    public ResponseEntity<SmsRegistration> updateSmsRegistration(@Valid @RequestBody SmsRegistration smsRegistration) throws URISyntaxException {
        log.debug("REST request to update SmsRegistration : {}", smsRegistration);
        if (smsRegistration.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SmsRegistration result = smsRegistrationRepository.save(smsRegistration);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, smsRegistration.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sms-registrations : get all the smsRegistrations.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of smsRegistrations in body
     */
    @GetMapping("/sms-registrations")
    @Timed
    public List<SmsRegistration> getAllSmsRegistrations() {
        log.debug("REST request to get all SmsRegistrations");
        return smsRegistrationRepository.findAll();
    }

    /**
     * GET  /sms-registrations/:id : get the "id" smsRegistration.
     *
     * @param id the id of the smsRegistration to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the smsRegistration, or with status 404 (Not Found)
     */
    @GetMapping("/sms-registrations/{id}")
    @Timed
    public ResponseEntity<SmsRegistration> getSmsRegistration(@PathVariable Long id) {
        log.debug("REST request to get SmsRegistration : {}", id);
        Optional<SmsRegistration> smsRegistration = smsRegistrationRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(smsRegistration);
    }

    /**
     * DELETE  /sms-registrations/:id : delete the "id" smsRegistration.
     *
     * @param id the id of the smsRegistration to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/sms-registrations/{id}")
    @Timed
    public ResponseEntity<Void> deleteSmsRegistration(@PathVariable Long id) {
        log.debug("REST request to delete SmsRegistration : {}", id);

        smsRegistrationRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    public static int generatePin() {
        return 100000 + generator.nextInt(900000);
    }
}
