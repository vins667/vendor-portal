package io.vamani.application.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * A JobWorkFollowup.
 */
@Entity
@Table(name = "job_work_followup_schedule")
public class JobWorkFollowupSchedule implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "jobWorkFollowupScheduleSeq", sequenceName = "job_work_followup_schedule_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "jobWorkFollowupScheduleSeq")
    private Long id;

    @Column(name = "fin_year")
    private Long finYear;

    @Size(max = 1)
    @Column(name = "sch_type", length = 1, nullable = true)
    private String schType;

    @Size(max = 5)
    @Column(name = "on_date", length = 5, nullable = true)
    private String onDate;

    @Size(max = 5)
    @Column(name = "on_date_second", length = 5, nullable = true)
    private String onDateSecond;

    @Column(name = "resp_reminder")
    private Long respReminder;

    @Column(name = "level1_reminder")
    private Long level1Reminder;

    @Column(name = "level2_reminder")
    private Long level2Reminder;

    @ManyToOne(optional = false)
    @JoinColumn(name = "job_work_followup_id")
    @NotNull
    private JobWorkFollowup jobWorkFollowup;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFinYear() {
        return finYear;
    }

    public void setFinYear(Long finYear) {
        this.finYear = finYear;
    }

    public String getSchType() {
        return schType;
    }

    public void setSchType(String schType) {
        this.schType = schType;
    }

    public String getOnDate() {
        return onDate;
    }

    public void setOnDate(String onDate) {
        this.onDate = onDate;
    }

    public String getOnDateSecond() {
        return onDateSecond;
    }

    public void setOnDateSecond(String onDateSecond) {
        this.onDateSecond = onDateSecond;
    }

    public Long getRespReminder() {
        return respReminder;
    }

    public void setRespReminder(Long respReminder) {
        this.respReminder = respReminder;
    }

    public Long getLevel1Reminder() {
        return level1Reminder;
    }

    public void setLevel1Reminder(Long level1Reminder) {
        this.level1Reminder = level1Reminder;
    }

    public Long getLevel2Reminder() {
        return level2Reminder;
    }

    public void setLevel2Reminder(Long level2Reminder) {
        this.level2Reminder = level2Reminder;
    }

    public JobWorkFollowup getJobWorkFollowup() {
        return jobWorkFollowup;
    }

    public void setJobWorkFollowup(JobWorkFollowup jobWorkFollowup) {
        this.jobWorkFollowup = jobWorkFollowup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobWorkFollowupSchedule that = (JobWorkFollowupSchedule) o;
        return Objects.equals(id, that.id) && Objects.equals(finYear, that.finYear) && Objects.equals(schType, that.schType) && Objects.equals(onDate, that.onDate) && Objects.equals(respReminder, that.respReminder) && Objects.equals(level1Reminder, that.level1Reminder) && Objects.equals(level2Reminder, that.level2Reminder) && Objects.equals(jobWorkFollowup, that.jobWorkFollowup);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, finYear, schType, onDate, respReminder, level1Reminder, level2Reminder, jobWorkFollowup);
    }
}
