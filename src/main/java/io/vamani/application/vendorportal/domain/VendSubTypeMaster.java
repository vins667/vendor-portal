package io.vamani.application.vendorportal.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A VendSubTypeMaster.
 */
@Entity
@Table(name = "vend_sub_type_master")
public class VendSubTypeMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="vendSubTypeMasterSeq", sequenceName="vend_sub_type_master_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="vendSubTypeMasterSeq")
    private Long id;

    @NotNull
    @Size(max = 45)
    @Column(name = "description", length = 45, nullable = false)
    private String description;

    @Size(max = 3)
    @Column(name = "m_3_code", length = 3)
    private String m3Code;

    @Size(max = 1)
    @Column(name = "status", length = 1)
    private String status;

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

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("")
    @JoinColumn(name = "vend_type_master_id")
    private VendTypeMaster vendTypeMaster;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public VendSubTypeMaster description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getm3Code() {
        return m3Code;
    }

    public VendSubTypeMaster m3Code(String m3Code) {
        this.m3Code = m3Code;
        return this;
    }

    public void setm3Code(String m3Code) {
        this.m3Code = m3Code;
    }

    public String getStatus() {
        return status;
    }

    public VendSubTypeMaster status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public VendSubTypeMaster createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public VendSubTypeMaster createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public VendSubTypeMaster lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public VendSubTypeMaster lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public VendTypeMaster getVendTypeMaster() {
        return vendTypeMaster;
    }

    public VendSubTypeMaster vendTypeMaster(VendTypeMaster vendTypeMaster) {
        this.vendTypeMaster = vendTypeMaster;
        return this;
    }

    public void setVendTypeMaster(VendTypeMaster vendTypeMaster) {
        this.vendTypeMaster = vendTypeMaster;
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
        VendSubTypeMaster vendSubTypeMaster = (VendSubTypeMaster) o;
        if (vendSubTypeMaster.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), vendSubTypeMaster.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "VendSubTypeMaster{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", m3Code='" + getm3Code() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            "}";
    }
}
