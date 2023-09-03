package io.vamani.application.mssql.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.web.util.ResponseUtil;
import io.vamani.application.model.ShiftBean;
import io.vamani.application.mssql.domain.EmployeeView;
import io.vamani.application.mssql.domain.Hourt;
import io.vamani.application.mssql.domain.HourtId;
import io.vamani.application.mssql.domain.Shift;
import io.vamani.application.mssql.repository.EmployeeViewRepository;
import io.vamani.application.mssql.repository.HourtRepository;
import io.vamani.application.mssql.repository.ShiftRepository;
import io.vamani.application.security.SecurityUtils;
import java.time.Instant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

/**
 * REST controller for managing EmployeeView.
 */
@RestController
@RequestMapping("/api")
public class ShiftResource {
    private final Logger log = LoggerFactory.getLogger(EmployeeViewResource.class);

    private static final String ENTITY_NAME = "shift";

    private final ShiftRepository shiftRepository;

    @Autowired
    private EmployeeViewRepository employeeViewRepository;

    @Autowired
    private HourtRepository hourtRepository;

    public ShiftResource(ShiftRepository shiftRepository) {
        this.shiftRepository = shiftRepository;
    }

    /**
     * GET  /employee-views/:id : get the "id" employeeView.
     *
     * @param id the id of the shift to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the shift, or with status 404 (Not Found)
     */
    @GetMapping("/shift/{id}")
    @Timed
    public ResponseEntity<Shift> getShift(@PathVariable Long id) {
        log.debug("REST request to get Shift : {}", id);
        String userCode = SecurityUtils.getCurrentUserLogin().orElse(null);
        EmployeeView employeeView = employeeViewRepository.findByCardNo(userCode).orElse(null);
        Optional<Shift> shift = shiftRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(shift);
    }/**
     * GET  /employee-views/:id : get the "id" employeeView.
     *
     * @param id the id of the shift to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the shift, or with status 404 (Not Found)
     */
    @PostMapping("/shift-current")
    @Timed
    public ResponseEntity<Shift> getShiftCurrent(@RequestBody ShiftBean shiftBean) {
        log.debug("REST request to get Shift : {}", shiftBean);
        if (shiftBean.getTodate() == null) {
            shiftBean.setTodate(Instant.now());
        }
        String userCode = SecurityUtils.getCurrentUserLogin().orElse(null);
        EmployeeView employeeView = employeeViewRepository.findByCardNo(userCode).orElse(null);
        Date date = Date.from(shiftBean.getTodate());
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int day = cal.get(Calendar.DATE);
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);

        short mm = 0;
        if (month >= 1 && month <= 3) {
            mm = (short) (9 + month);
        } else {
            mm = (short) (month - 3);
        }
        Hourt hourt = hourtRepository.findById(new HourtId(Integer.parseInt(employeeView.getEmpCode()), mm)).orElse(null);
        if(hourt != null) {
            String shiftCode = "";
            if (hourt != null) {
                if (day == 1) {
                    shiftCode = hourt.getSf1();
                } else if (day == 2) {
                    shiftCode = hourt.getSf2();
                } else if (day == 3) {
                    shiftCode = hourt.getSf3();
                } else if (day == 4) {
                    shiftCode = hourt.getSf4();
                } else if (day == 5) {
                    shiftCode = hourt.getSf5();
                } else if (day == 6) {
                    shiftCode = hourt.getSf6();
                } else if (day == 7) {
                    shiftCode = hourt.getSf7();
                } else if (day == 8) {
                    shiftCode = hourt.getSf8();
                } else if (day == 9) {
                    shiftCode = hourt.getSf9();
                } else if (day == 10) {
                    shiftCode = hourt.getSf10();
                } else if (day == 11) {
                    shiftCode = hourt.getSf11();
                } else if (day == 12) {
                    shiftCode = hourt.getSf12();
                } else if (day == 13) {
                    shiftCode = hourt.getSf13();
                } else if (day == 14) {
                    shiftCode = hourt.getSf14();
                } else if (day == 15) {
                    shiftCode = hourt.getSf15();
                } else if (day == 16) {
                    shiftCode = hourt.getSf16();
                } else if (day == 17) {
                    shiftCode = hourt.getSf17();
                } else if (day == 18) {
                    shiftCode = hourt.getSf18();
                } else if (day == 19) {
                    shiftCode = hourt.getSf19();
                } else if (day == 20) {
                    shiftCode = hourt.getSf20();
                } else if (day == 21) {
                    shiftCode = hourt.getSf21();
                } else if (day == 22) {
                    shiftCode = hourt.getSf22();
                } else if (day == 23) {
                    shiftCode = hourt.getSf23();
                } else if (day == 24) {
                    shiftCode = hourt.getSf24();
                } else if (day == 25) {
                    shiftCode = hourt.getSf25();
                } else if (day == 26) {
                    shiftCode = hourt.getSf26();
                } else if (day == 27) {
                    shiftCode = hourt.getSf27();
                } else if (day == 28) {
                    shiftCode = hourt.getSf28();
                } else if (day == 29) {
                    shiftCode = hourt.getSf29();
                } else if (day == 30) {
                    shiftCode = hourt.getSf30();
                } else if (day == 31) {
                    shiftCode = hourt.getSf31();
                }
            }
            Shift shift = null;
            if (shiftCode != null && shiftCode.trim().length() > 0) {
                shift = shiftRepository.findBySftUcode(shiftCode);
            }
            return ResponseUtil.wrapOrNotFound(Optional.of(shift));
        } else {
            date = new Date();
            cal = Calendar.getInstance();
            cal.setTime(date);
            day = cal.get(Calendar.DATE);
            month = cal.get(Calendar.MONTH) + 1;
            year = cal.get(Calendar.YEAR);

            mm = 0;
            if (month >= 1 && month <= 3) {
                mm = (short) (9 + month);
            } else {
                mm = (short) (month - 3);
            }
            hourt = hourtRepository.findById(new HourtId(Integer.parseInt(employeeView.getEmpCode()), mm)).orElse(null);
            String shiftCode = "";
            if (hourt != null) {
                if (day == 1) {
                    shiftCode = hourt.getSf1();
                } else if (day == 2) {
                    shiftCode = hourt.getSf2();
                } else if (day == 3) {
                    shiftCode = hourt.getSf3();
                } else if (day == 4) {
                    shiftCode = hourt.getSf4();
                } else if (day == 5) {
                    shiftCode = hourt.getSf5();
                } else if (day == 6) {
                    shiftCode = hourt.getSf6();
                } else if (day == 7) {
                    shiftCode = hourt.getSf7();
                } else if (day == 8) {
                    shiftCode = hourt.getSf8();
                } else if (day == 9) {
                    shiftCode = hourt.getSf9();
                } else if (day == 10) {
                    shiftCode = hourt.getSf10();
                } else if (day == 11) {
                    shiftCode = hourt.getSf11();
                } else if (day == 12) {
                    shiftCode = hourt.getSf12();
                } else if (day == 13) {
                    shiftCode = hourt.getSf13();
                } else if (day == 14) {
                    shiftCode = hourt.getSf14();
                } else if (day == 15) {
                    shiftCode = hourt.getSf15();
                } else if (day == 16) {
                    shiftCode = hourt.getSf16();
                } else if (day == 17) {
                    shiftCode = hourt.getSf17();
                } else if (day == 18) {
                    shiftCode = hourt.getSf18();
                } else if (day == 19) {
                    shiftCode = hourt.getSf19();
                } else if (day == 20) {
                    shiftCode = hourt.getSf20();
                } else if (day == 21) {
                    shiftCode = hourt.getSf21();
                } else if (day == 22) {
                    shiftCode = hourt.getSf22();
                } else if (day == 23) {
                    shiftCode = hourt.getSf23();
                } else if (day == 24) {
                    shiftCode = hourt.getSf24();
                } else if (day == 25) {
                    shiftCode = hourt.getSf25();
                } else if (day == 26) {
                    shiftCode = hourt.getSf26();
                } else if (day == 27) {
                    shiftCode = hourt.getSf27();
                } else if (day == 28) {
                    shiftCode = hourt.getSf28();
                } else if (day == 29) {
                    shiftCode = hourt.getSf29();
                } else if (day == 30) {
                    shiftCode = hourt.getSf30();
                } else if (day == 31) {
                    shiftCode = hourt.getSf31();
                }
            }
            Shift shift = null;
            if (shiftCode != null && shiftCode.trim().length() > 0) {
                shift = shiftRepository.findBySftUcode(shiftCode);
            }
            return ResponseUtil.wrapOrNotFound(Optional.of(shift));
        }
    }
}
