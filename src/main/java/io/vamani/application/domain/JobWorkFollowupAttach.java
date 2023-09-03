package io.vamani.application.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A JobWorkFollowup.
 */
@Entity
@Table(name = "job_work_followup_attach")
public class JobWorkFollowupAttach implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "jobWorkFollowupAttachSeq", sequenceName = "job_work_followup_attach_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "jobWorkFollowupAttachSeq")
    private Long id;

    @Column(name = "attachment_filename", length = 100)
    private String attachmentFilename;

    @Column(name = "original_filename", length = 100)
    private String originalFilename;

    @Column(name = "submit_date")
    private Instant submitDate;

    @Column(name = "submit_by", length = 50)
    private String submitBy;

    @ManyToOne(optional = false)
    @JoinColumn(name = "job_work_followup_details_id")
    @NotNull
    private JobWorkFollowupDetails jobWorkFollowupDetails;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAttachmentFilename() {
        return attachmentFilename;
    }

    public void setAttachmentFilename(String attachmentFilename) {
        this.attachmentFilename = attachmentFilename;
    }

    public String getOriginalFilename() {
        return originalFilename;
    }

    public void setOriginalFilename(String originalFilename) {
        this.originalFilename = originalFilename;
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

    public JobWorkFollowupDetails getJobWorkFollowupDetails() {
        return jobWorkFollowupDetails;
    }

    public void setJobWorkFollowupDetails(JobWorkFollowupDetails jobWorkFollowupDetails) {
        this.jobWorkFollowupDetails = jobWorkFollowupDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobWorkFollowupAttach that = (JobWorkFollowupAttach) o;
        return Objects.equals(id, that.id) && Objects.equals(attachmentFilename, that.attachmentFilename) && Objects.equals(originalFilename, that.originalFilename) && Objects.equals(submitDate, that.submitDate) && Objects.equals(submitBy, that.submitBy) && Objects.equals(jobWorkFollowupDetails, that.jobWorkFollowupDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, attachmentFilename, originalFilename, submitDate, submitBy, jobWorkFollowupDetails);
    }
}
