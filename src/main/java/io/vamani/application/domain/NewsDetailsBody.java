package io.vamani.application.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A NewsDetailsBody.
 */
@Entity
@Table(name = "news_details_body")
public class NewsDetailsBody implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="newsDetailsBodySeq", sequenceName="news_details_body_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="newsDetailsBodySeq")
    private Long id;

    @NotNull
    @Size(max = 2000)
    @Column(name = "news_body", length = 2000, nullable = false)
    private String newsBody;

    @NotNull
    @Size(max = 50)
    @Column(name = "created_by", length = 50, nullable = false)
    private String createdBy;

    @NotNull
    @Column(name = "created_date", nullable = false)
    private Instant createdDate;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnore
    @JoinColumn(name = "news_details_id")
    private NewsDetails newsDetails;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNewsBody() {
        return newsBody;
    }

    public NewsDetailsBody newsBody(String newsBody) {
        this.newsBody = newsBody;
        return this;
    }

    public void setNewsBody(String newsBody) {
        this.newsBody = newsBody;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public NewsDetailsBody createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public NewsDetailsBody createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    public NewsDetails getNewsDetails() {
        return newsDetails;
    }

    public void setNewsDetails(NewsDetails newsDetails) {
        this.newsDetails = newsDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NewsDetailsBody newsDetailsBody = (NewsDetailsBody) o;
        if (newsDetailsBody.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), newsDetailsBody.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NewsDetailsBody{" +
            "id=" + getId() +
            ", newsBody='" + getNewsBody() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            "}";
    }
}
