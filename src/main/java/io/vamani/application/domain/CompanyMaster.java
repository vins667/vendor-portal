package io.vamani.application.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A CompanyMaster.
 */
@Entity
@Table(name = "company_master")
public class CompanyMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="companyMasterSeq", sequenceName="company_master_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="companyMasterSeq")
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "company_code", length = 50, nullable = false)
    private String companyCode;

    @NotNull
    @Size(max = 255)
    @Column(name = "company_name", length = 255, nullable = false)
    private String companyName;

    @NotNull
    @Size(max = 1)
    @Column(name = "flag", length = 1, nullable = false)
    private String flag;

    @NotNull
    @Size(max = 50)
    @Column(name = "created_by", length = 50, nullable = false)
    private String createdBy;

    @NotNull
    @Column(name = "created_date", nullable = false)
    private Instant createdDate;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public CompanyMaster companyCode(String companyCode) {
        this.companyCode = companyCode;
        return this;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public CompanyMaster companyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getFlag() {
        return flag;
    }

    public CompanyMaster flag(String flag) {
        this.flag = flag;
        return this;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public CompanyMaster createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public CompanyMaster createdDate(Instant createdDate) {
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
        CompanyMaster companyMaster = (CompanyMaster) o;
        if (companyMaster.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), companyMaster.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CompanyMaster{" +
            "id=" + getId() +
            ", companyCode='" + getCompanyCode() + "'" +
            ", companyName='" + getCompanyName() + "'" +
            ", flag='" + getFlag() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            "}";
    }
}
