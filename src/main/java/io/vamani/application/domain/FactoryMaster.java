package io.vamani.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A FactoryMaster.
 */
@Entity
@Table(name = "factory_master")
public class FactoryMaster implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="factoryMasterSeq", sequenceName="factory_master_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="factoryMasterSeq")
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "factory_code", length = 50, nullable = false)
    private String factoryCode;

    @NotNull
    @Size(max = 255)
    @Column(name = "factory_name", length = 255, nullable = false)
    private String factoryName;

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

    @NotNull
    @Size(max = 500)
    @Column(name = "factory_address", length = 500, nullable = false)
    private String factoryAddress;


    @Column(name = "now_factory_code")
    private String nowFactoryCode;


    @Column(name = "group_code")
    private String groupCode;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("")
    @JoinColumn(name = "company_master_id")
    private CompanyMaster companyMaster;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFactoryCode() {
        return factoryCode;
    }

    public FactoryMaster factoryCode(String factoryCode) {
        this.factoryCode = factoryCode;
        return this;
    }

    public void setFactoryCode(String factoryCode) {
        this.factoryCode = factoryCode;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public FactoryMaster factoryName(String factoryName) {
        this.factoryName = factoryName;
        return this;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public String getFlag() {
        return flag;
    }

    public FactoryMaster flag(String flag) {
        this.flag = flag;
        return this;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public FactoryMaster createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public FactoryMaster createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getFactoryAddress() {
        return factoryAddress;
    }

    public FactoryMaster factoryAddress(String factoryAddress) {
        this.factoryAddress = factoryAddress;
        return this;
    }

    public void setFactoryAddress(String factoryAddress) {
        this.factoryAddress = factoryAddress;
    }

    public CompanyMaster getCompanyMaster() {
        return companyMaster;
    }

    public FactoryMaster companyMaster(CompanyMaster companyMaster) {
        this.companyMaster = companyMaster;
        return this;
    }

    public void setCompanyMaster(CompanyMaster companyMaster) {
        this.companyMaster = companyMaster;
    }

    public String getNowFactoryCode() {
        return nowFactoryCode;
    }

    public void setNowFactoryCode(String nowFactoryCode) {
        this.nowFactoryCode = nowFactoryCode;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
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
        FactoryMaster factoryMaster = (FactoryMaster) o;
        if (factoryMaster.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), factoryMaster.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FactoryMaster{" +
            "id=" + getId() +
            ", factoryCode='" + getFactoryCode() + "'" +
            ", factoryName='" + getFactoryName() + "'" +
            ", flag='" + getFlag() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", factoryAddress='" + getFactoryAddress() + "'" +
            "}";
    }
}
