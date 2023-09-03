package io.vamani.application.mssql.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.web.util.ResponseUtil;
import io.vamani.application.mssql.domain.DayStatus;
import io.vamani.application.mssql.domain.EmployeeView;
import io.vamani.application.mssql.domain.Shift;
import io.vamani.application.mssql.model.DayStatusBean;
import io.vamani.application.mssql.repository.DayStatusRepository;
import io.vamani.application.mssql.repository.EmployeeViewRepository;
import io.vamani.application.mssql.repository.ShiftRepository;
import io.vamani.application.security.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing EmployeeView.
 */
@RestController
@RequestMapping("/api")
public class DayStatusResource {
    private final Logger log = LoggerFactory.getLogger(EmployeeViewResource.class);

    private static final String ENTITY_NAME = "shift";

    private final DayStatusRepository dayStatusRepository;

    @Autowired
    private EmployeeViewRepository employeeViewRepository;

    public DayStatusResource(DayStatusRepository dayStatusRepository) {
        this.dayStatusRepository = dayStatusRepository;
    }

    /**
     * GET  /employee-views/:id : get the "id" employeeView.
     *
     * @param @id the id of the shift to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the shift, or with status 404 (Not Found)
     */
    @GetMapping("/day-status/{dayNo}")
    @Timed
    public ResponseEntity<DayStatusBean> getDayStatus(@PathVariable String dayNo) throws ParseException {
        DayStatusBean dayStatusBean = new DayStatusBean();
        log.debug("REST request to get Shift : {}", dayNo);
        Date date =  new SimpleDateFormat("dd.MM.yyyy").parse(dayNo);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        EmployeeView employeeView = employeeViewRepository.findById(SecurityUtils.getCurrentUserLogin().orElse(null)).orElse(null);
        List<Object[]> dayStatus = dayStatusRepository.findByIdAndDayNo(employeeView.getEmpCode(), calendar.get(Calendar.DATE), calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.YEAR));
        if(dayStatus != null && dayStatus.size()>0) {
            if(dayStatus.get(0)[0] != null) {
                String inTime = dayStatus.get(0)[0].toString();
                inTime = inTime.replaceAll("\\s", inTime);
                dayStatusBean.setInTm(inTime.trim());
            }
            if(dayStatus.get(0)[1] != null) {
                String outTime = dayStatus.get(0)[1].toString();
                outTime = outTime.replaceAll("\\s", outTime);
                dayStatusBean.setOutTm(outTime.trim());
            }
        }
        return ResponseUtil.wrapOrNotFound(Optional.of(dayStatusBean));
    }
}
