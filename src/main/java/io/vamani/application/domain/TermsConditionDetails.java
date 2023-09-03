package io.vamani.application.domain;
import javax.persistence.*;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Instant;

/**
 * A TermsConditionDetails.
 */
@Entity
@Table(name = "terms_condition_details")
public class TermsConditionDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="termsConditionDetailsSeq", sequenceName="terms_condition_details_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="termsConditionDetailsSeq")
    private Long id;

    @Size(max = 255)
    @Column(name = "terms_line", length = 255)
    private String termsLine;

    @Size(max = 50)
    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Column(name = "created_date")
    private Instant createdDate;

    @Size(max = 50)
    @Column(name = "last_updated_by", length = 50)
    private String lastUpdatedBy;

    @Column(name = "last_updated_date")
    private Instant lastUpdatedDate;

    @ManyToOne
    @JoinColumn(name="terms_condition_master_id")
    @JsonIgnoreProperties("")
    private TermsConditionMaster termsConditionMaster;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTermsLine() {
        return termsLine;
    }

    public TermsConditionDetails termsLine(String termsLine) {
        this.termsLine = termsLine;
        return this;
    }

    public void setTermsLine(String termsLine) {
        this.termsLine = termsLine;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public TermsConditionDetails createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public TermsConditionDetails createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public TermsConditionDetails lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public TermsConditionDetails lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public TermsConditionMaster getTermsConditionMaster() {
        return termsConditionMaster;
    }

    public TermsConditionDetails termsConditionMaster(TermsConditionMaster termsConditionMaster) {
        this.termsConditionMaster = termsConditionMaster;
        return this;
    }

    public void setTermsConditionMaster(TermsConditionMaster termsConditionMaster) {
        this.termsConditionMaster = termsConditionMaster;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TermsConditionDetails)) {
            return false;
        }
        return id != null && id.equals(((TermsConditionDetails) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "TermsConditionDetails{" +
            "id=" + getId() +
            ", termsLine='" + getTermsLine() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            "}";
    }
}
