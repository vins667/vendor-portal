package io.vamani.application.db2.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.config.Constants;
import io.vamani.application.db2.domain.Finfinancialyear;
import io.vamani.application.db2.domain.FinfinancialyearId;
import io.vamani.application.db2.model.DaysBean;
import io.vamani.application.db2.model.MonthlyBean;
import io.vamani.application.db2.repository.FinfinancialyearRepository;
import io.vamani.application.domain.JobWorkFollowup;
import io.vamani.application.domain.JobWorkFollowupDetails;
import io.vamani.application.domain.JobWorkFollowupSchedule;
import io.vamani.application.model.JobWorkFollowupScheduleBean;
import io.vamani.application.repository.JobWorkFollowupDetailsRepository;
import io.vamani.application.repository.JobWorkFollowupScheduleRepository;
import io.vamani.application.util.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/api")
public class FinfinancialyearResource {
    private final Logger log = LoggerFactory.getLogger(FinfinancialyearResource.class);
    private static final String ENTITY_NAME = "FINFINANCIALYEAR";
    private final FinfinancialyearRepository finfinancialyearRepository;

    @Autowired
    private JobWorkFollowupScheduleRepository jobWorkFollowupScheduleRepository;

    @Autowired
    private JobWorkFollowupDetailsRepository jobWorkFollowupDetailsRepository;

    public FinfinancialyearResource(FinfinancialyearRepository finfinancialyearRepository) {
        this.finfinancialyearRepository = finfinancialyearRepository;
    }

    @GetMapping("/db2-financialyears")
    @Timed
    public List<Finfinancialyear> getAllYear() {
        log.debug("REST request to get all financial year");
        return finfinancialyearRepository.findAll();
    }

    @GetMapping("/db2-financialmonths/{finyearcode}/{jobWorkFollowupId}")
    public JobWorkFollowupScheduleBean findAllMonth(@PathVariable Long finyearcode, @PathVariable Long jobWorkFollowupId) throws URISyntaxException {
        log.debug("Rest request to get all months from fromDate to toDate");
        JobWorkFollowupScheduleBean jobWorkFollowupScheduleBean = new JobWorkFollowupScheduleBean();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM-YYYY");
        List<MonthlyBean> monthlyBeans = new ArrayList<>();

        JobWorkFollowupSchedule jobWorkFollowupSchedule = jobWorkFollowupScheduleRepository.findByJobWorkFollowupIdAndFinYear(jobWorkFollowupId, finyearcode);
        List<String> dates = new ArrayList<>();
        if(jobWorkFollowupSchedule != null) {
            BeanUtils.copyProperties(jobWorkFollowupSchedule, jobWorkFollowupScheduleBean);
            List<JobWorkFollowupDetails> jobWorkFollowupDetails = jobWorkFollowupDetailsRepository.findAllByJobWorkFollowupIdAndFinYear(jobWorkFollowupId, finyearcode);
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
            for(JobWorkFollowupDetails jobWorkFollowupDetail : jobWorkFollowupDetails) {
                dates.add(sdf.format(DateUtils.asDate(jobWorkFollowupDetail.getJobWorkDate())));
            }
        }

        Finfinancialyear finfinancialyear = finfinancialyearRepository.findById(new FinfinancialyearId(Constants.COMPANY_CODE, finyearcode)).orElse(null);
        if (finfinancialyear != null) {
            Calendar beginCalendar = Calendar.getInstance();
            Calendar finishCalendar = Calendar.getInstance();
            beginCalendar.setTime(finfinancialyear.getFromdate());
            finishCalendar.setTime(finfinancialyear.getTodate());
            long daysBetween = 0;
            while (beginCalendar.before(finishCalendar)) {
                String date = simpleDateFormat.format(beginCalendar.getTime());
                int daysInMonth = beginCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                MonthlyBean monthlyBean = new MonthlyBean();
                monthlyBean.setMonth(date);
                int monthStartNubmer = beginCalendar.get(Calendar.MONTH);
                int monthEndNumber = finishCalendar.get(Calendar.MONTH);
                List<DaysBean> daysBeans = new ArrayList<>();
                for (int i = 0; i < daysInMonth; i++) {
                    DaysBean daysBean = new DaysBean();
                    beginCalendar.set(Calendar.DAY_OF_MONTH, i + 1);
                    daysBean.setDay(beginCalendar.get(Calendar.DAY_OF_MONTH));
                    daysBean.setDate(StringUtils.leftPad(beginCalendar.get(Calendar.DAY_OF_MONTH) + "", 2, "0") + "-" + date);
                    if (dates.contains(daysBean.getDate())) {
                        daysBean.setSelectDay(true);
                    }
                    daysBeans.add(daysBean);
                }
                monthlyBean.setDaysBeans(daysBeans);
                monthlyBeans.add(monthlyBean);
                beginCalendar.add(Calendar.MONTH, 1);
            }
        }
        jobWorkFollowupScheduleBean.setMonthlyBeans(monthlyBeans);
        return jobWorkFollowupScheduleBean;
    }
}
