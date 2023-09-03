package io.vamani.application.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A NewsDetailsAttach.
 */
@Entity
@Table(name = "news_details_attach")
public class NewsDetailsAttach implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="newsDetailsAttachSeq", sequenceName="news_details_attach_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="newsDetailsAttachSeq")
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "attach_file", length = 100, nullable = false)
    private String attachFile;

    @NotNull
    @Size(max = 100)
    @Column(name = "attach_display_file", length = 100, nullable = false)
    private String attachDisplayFile;

    @NotNull
    @Size(max = 50)
    @Column(name = "created_by", length = 50, nullable = false)
    private String createdBy;

    @NotNull
    @Column(name = "created_date", nullable = false)
    private Instant createdDate;

    @JsonIgnore
    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name = "news_details_id")
    private NewsDetails newsDetails;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAttachFile() {
        return attachFile;
    }

    public NewsDetailsAttach attachFile(String attachFile) {
        this.attachFile = attachFile;
        return this;
    }

    public void setAttachFile(String attachFile) {
        this.attachFile = attachFile;
    }

    public String getAttachDisplayFile() {
        return attachDisplayFile;
    }

    public NewsDetailsAttach attachDisplayFile(String attachDisplayFile) {
        this.attachDisplayFile = attachDisplayFile;
        return this;
    }

    public void setAttachDisplayFile(String attachDisplayFile) {
        this.attachDisplayFile = attachDisplayFile;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public NewsDetailsAttach createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public NewsDetailsAttach createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public NewsDetails getNewsDetails() {
        return newsDetails;
    }

    public void setNewsDetails(NewsDetails newsDetails) {
        this.newsDetails = newsDetails;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NewsDetailsAttach newsDetailsAttach = (NewsDetailsAttach) o;
        if (newsDetailsAttach.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), newsDetailsAttach.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NewsDetailsAttach{" +
            "id=" + getId() +
            ", attachFile='" + getAttachFile() + "'" +
            ", attachDisplayFile='" + getAttachDisplayFile() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            "}";
    }
}
