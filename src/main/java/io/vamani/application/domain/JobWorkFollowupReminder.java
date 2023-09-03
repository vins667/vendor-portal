package io.vamani.application.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A JobWorkFollowup.
 */
@Entity
@Table(name = "job_work_followup_reminder")
public class JobWorkFollowupReminder implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "jobWorkFollowupReminderSeq", sequenceName = "job_work_followup_reminder_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "jobWorkFollowupReminderSeq")
    private Long id;

    @Column(name = "fin_year")
    private Long finYear;

    @Column(name = "reminder_type", length =20)
    private String reminderType;

    @Column(name = "reminder_date")
    private LocalDate reminderDate;

    @Column(name = "reminder_sent")
    private Instant reminderSent;

    @Column(name = "reminder_sent_mails", length = 1000)
    private String reminderSentMails;

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

    public String getReminderType() {
        return reminderType;
    }

    public void setReminderType(String reminderType) {
        this.reminderType = reminderType;
    }

    public LocalDate getReminderDate() {
        return reminderDate;
    }

    public void setReminderDate(LocalDate reminderDate) {
        this.reminderDate = reminderDate;
    }

    public Instant getReminderSent() {
        return reminderSent;
    }

    public void setReminderSent(Instant reminderSent) {
        this.reminderSent = reminderSent;
    }

    public String getReminderSentMails() {
        return reminderSentMails;
    }

    public void setReminderSentMails(String reminderSentMails) {
        this.reminderSentMails = reminderSentMails;
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
        JobWorkFollowupReminder that = (JobWorkFollowupReminder) o;
        return Objects.equals(id, that.id) && Objects.equals(finYear, that.finYear) && Objects.equals(reminderDate, that.reminderDate) && Objects.equals(reminderSent, that.reminderSent) && Objects.equals(reminderSentMails, that.reminderSentMails) && Objects.equals(jobWorkFollowup, that.jobWorkFollowup);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, finYear, reminderDate, reminderSent, reminderSentMails, jobWorkFollowup);
    }
}
