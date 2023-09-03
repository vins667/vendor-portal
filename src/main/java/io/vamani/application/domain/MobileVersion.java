package io.vamani.application.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A MobileVersion.
 */
@Entity
@Table(name = "mobile_version")
public class MobileVersion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="mobileVersionSeq", sequenceName="mobile_version_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="mobileVersionSeq")
    private Long id;

    @NotNull
    @Size(max = 10)
    @Column(name = "version", length = 10, nullable = false)
    private String version;

    @NotNull
    @Size(max = 15)
    @Column(name = "mobile_type", length = 15, nullable = false)
    private String mobileType;

    @Column(name = "closed_date")
    private Instant closedDate;

    @Column(name = "created_date")
    private Instant createdDate;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public MobileVersion version(String version) {
        this.version = version;
        return this;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getMobileType() {
        return mobileType;
    }

    public MobileVersion mobileType(String mobileType) {
        this.mobileType = mobileType;
        return this;
    }

    public void setMobileType(String mobileType) {
        this.mobileType = mobileType;
    }

    public Instant getClosedDate() {
        return closedDate;
    }

    public MobileVersion closedDate(Instant closedDate) {
        this.closedDate = closedDate;
        return this;
    }

    public void setClosedDate(Instant closedDate) {
        this.closedDate = closedDate;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public MobileVersion createdDate(Instant createdDate) {
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
        MobileVersion mobileVersion = (MobileVersion) o;
        if (mobileVersion.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mobileVersion.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MobileVersion{" +
            "id=" + getId() +
            ", version='" + getVersion() + "'" +
            ", mobileType='" + getMobileType() + "'" +
            ", closedDate='" + getClosedDate() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            "}";
    }
}
