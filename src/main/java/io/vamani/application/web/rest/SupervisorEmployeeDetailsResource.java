package io.vamani.application.web.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import io.vamani.application.mssql.domain.*;
import io.vamani.application.mssql.repository.HourtRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import io.github.jhipster.web.util.ResponseUtil;
import io.vamani.application.domain.HolidayMaster;
import io.vamani.application.model.SupervisorEmployeeDetailsBean;
import io.vamani.application.mssql.repository.DayStatusRepository;
import io.vamani.application.mssql.repository.EmployeeViewRepository;
import io.vamani.application.mssql.repository.ShiftRepository;
import io.vamani.application.repository.HolidayMasterRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.util.AttendanceUtil;

/**
 * REST controller for managing {@link io.vamani.application.domain.SupervisorEmployeeDetails}.
 */
@RestController
@RequestMapping("/api")
public class SupervisorEmployeeDetailsResource {

    private final Logger log = LoggerFactory.getLogger(SupervisorEmployeeDetailsResource.class);

    @Autowired
    private HolidayMasterRepository holidayMasterRepository;

    @Autowired
    private DayStatusRepository dayStatusRepository;

    @Autowired
    private ShiftRepository shiftRepository;

    @Autowired
    private  EmployeeViewRepository employeeViewRepository;

    @Autowired
    private HourtRepository hourtRepository;

    /**
     * {@code GET  /supervisor-employee-details} : get all the supervisorEmployeeDetails.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of supervisorEmployeeDetails in body.
     */
    @GetMapping("/supervisor-employee-details")
    public ResponseEntity<List<EmployeeView>> getAllSupervisorEmployeeDetails() {
        log.debug("REST request to get a page of SupervisorEmployeeDetails");
        List<EmployeeView> employeeViewList=null;
        String currentUser = SecurityUtils.getCurrentUserLogin().orElse(null);
        EmployeeView employeeView = employeeViewRepository.findByLogin(currentUser);
        if (employeeView != null) {
        	 employeeViewList = employeeViewRepository.findAllBySupervisor(currentUser + "%");
        }
        return ResponseEntity.ok(employeeViewList);
    }

    /**
     * {@code GET  /supervisor-employee-details/:id} : get the "id" supervisorEmployeeDetails.
     *
     * @param id the id of the supervisorEmployeeDetails to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the supervisorEmployeeDetails, or with status {@code 404 (Not Found)}.
     * @throws ParseException
     */
    @GetMapping("/supervisor-employee-details-attendace/{cardNo}/{monthYear}")
    public ResponseEntity<SupervisorEmployeeDetailsBean> getSupervisorEmployeeDetails(@PathVariable String cardNo,@PathVariable String monthYear) throws ParseException {
        log.debug("REST request to get SupervisorEmployeeDetails : {}");
        SupervisorEmployeeDetailsBean supervisorEmployeeDetailsBean = new SupervisorEmployeeDetailsBean();
        SimpleDateFormat format = new SimpleDateFormat("MMM-yyyy");
        Date date = new SimpleDateFormat("dd-MM-yyyy").parse("01-" + monthYear);
        String []arr = monthYear.split("-");
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH) + 1;

        short mm = 0;
        if (month >= 1 && month <= 3) {
            mm = (short) (9 + month);
        } else {
            mm = (short) (month - 3);
        }

        EmployeeView employeeView = employeeViewRepository.findById(cardNo).orElse(null);
        List<DayStatus> dayStatusList = dayStatusRepository.findAllByEmpCodeAndDate(employeeView.getEmpCode(), Integer.parseInt(arr[0]), Integer.parseInt(arr[1]));
        Hourt hourt = hourtRepository.findById(new HourtId(Integer.parseInt(employeeView.getEmpCode()), mm)).orElse(null);
        List<HolidayMaster> holidayMasters = holidayMasterRepository.findAllByFactoryMaster(employeeView.getFactory().trim());
        if (dayStatusList != null && hourt != null) {
            supervisorEmployeeDetailsBean.setAttendanceList(AttendanceUtil.getAllendance(dayStatusList, hourt, shiftRepository, holidayMasters));
        }
        return ResponseUtil.wrapOrNotFound(Optional.of(supervisorEmployeeDetailsBean));
    }

}
