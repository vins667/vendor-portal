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
@Table(name = "job_work_followup_details")
public class JobWorkFollowupDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "jobWorkFollowupDetailsSeq", sequenceName = "job_work_followup_details_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "jobWorkFollowupDetailsSeq")
    private Long id;

    @Column(name = "fin_year")
    private Long finYear;

    @NotNull
    @Column(name = "job_work_date")
    private LocalDate jobWorkDate;

    @Column(name = "submit_date")
    private Instant submitDate;

    @Column(name = "submit_by", length = 50)
    private String submitBy;

    @Column(name = "remarks", length = 2000)
    private String remarks;

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

    public LocalDate getJobWorkDate() {
        return jobWorkDate;
    }

    public void setJobWorkDate(LocalDate jobWorkDate) {
        this.jobWorkDate = jobWorkDate;
    }

    public Instant getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Instant submitDate) {
        this.submitDate = submitDate;
    }

    public String getSubmitBy() {
        return submitBy;
    }

    public void setSubmitBy(String submitBy) {
        this.submitBy = submitBy;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public JobWorkFollowup getJobWorkFollowup() {
        return jobWorkFollowup;
    }

    public void setJobWorkFollowup(JobWorkFollowup jobWorkFollowup) {
        this.jobWorkFollowup = jobWorkFollowup;
    }
}
