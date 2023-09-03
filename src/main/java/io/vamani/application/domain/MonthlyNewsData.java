package io.vamani.application.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A MonthlyNewsData.
 */
@Entity
@Table(name = "monthly_news_letter")
public class MonthlyNewsData implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="monthlyNewsDataSeq", sequenceName="monthly_news_letter_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="monthlyNewsDataSeq")
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "file_name", length = 100, nullable = false)
    private String fileName;

    @Size(max = 50)
    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Column(name = "created_date")
    private Instant createdDate;

    @Column(name = "closed_date")
    private Instant closedDate;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public MonthlyNewsData fileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public MonthlyNewsData createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public MonthlyNewsData createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public Instant getClosedDate() {
        return closedDate;
    }

    public MonthlyNewsData closedDate(Instant closedDate) {
        this.closedDate = closedDate;
        return this;
    }

    public void setClosedDate(Instant closedDate) {
        this.closedDate = closedDate;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MonthlyNewsData)) {
            return false;
        }
        return id != null && id.equals(((MonthlyNewsData) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MonthlyNewsData{" +
            "id=" + getId() +
            ", fileName='" + getFileName() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", closedDate='" + getClosedDate() + "'" +
            "}";
    }
}
