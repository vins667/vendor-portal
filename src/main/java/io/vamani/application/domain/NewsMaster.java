package io.vamani.application.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A NewsMaster.
 */
@Entity
@Table(name = "news_master")
public class NewsMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="newsMasterSeq", sequenceName="news_master_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="newsMasterSeq")
    private Long id;

    @NotNull
    @Size(max = 255)
    @Column(name = "news_name", length = 255, nullable = false)
    private String newsName;

    @Size(max = 1)
    @Column(name = "flag", length = 1)
    private String flag;

    @Size(max = 50)
    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Column(name = "created_date")
    private Instant createdDate;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNewsName() {
        return newsName;
    }

    public NewsMaster newsName(String newsName) {
        this.newsName = newsName;
        return this;
    }

    public void setNewsName(String newsName) {
        this.newsName = newsName;
    }

    public String getFlag() {
        return flag;
    }

    public NewsMaster flag(String flag) {
        this.flag = flag;
        return this;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public NewsMaster createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public NewsMaster createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
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
        NewsMaster newsMaster = (NewsMaster) o;
        if (newsMaster.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), newsMaster.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NewsMaster{" +
            "id=" + getId() +
            ", newsName='" + getNewsName() + "'" +
            ", flag='" + getFlag() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            "}";
    }
}
