package io.vamani.application.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A WorkerRecruitment.
 */
@Entity
@Table(name = "worker_recruitment")
public class WorkerRecruitment implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @SequenceGenerator(name="workerRecruitmentSeq", sequenceName="worker_recruitment_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="workerRecruitmentSeq")
    private Long id;

    @NotNull
    @Size(max = 20)
    @Column(name = "aadhar_no", length = 20, nullable = false)
    private String aadharNo;

    @NotNull
    @Size(max = 60)
    @Column(name = "name", length = 60, nullable = false)
    private String name;

    @NotNull
    @Column(name = "dob", nullable = false)
    private LocalDate dob;

    @NotNull
    @Size(max = 60)
    @Column(name = "father_name", length = 60, nullable = false)
    private String fatherName;

    @NotNull
    @Size(max = 500)
    @Column(name = "address", length = 500, nullable = false)
    private String address;

    @Size(max = 20)
    @Column(name = "pan_no", length = 20)
    private String panNo;

    @NotNull
    @Size(max = 50)
    @Column(name = "bank_branch", length = 50, nullable = false)
    private String bankBranch;

    @NotNull
    @Size(max = 20)
    @Column(name = "bank_acc_no", length = 20, nullable = false)
    private String bankAccNo;

    @Size(max = 1)
    @Column(name = "status", length = 1)
    private String status;

    @Size(max = 60)
    @Column(name = "created_by", length = 60)
    private String createdBy;

    @Column(name = "created_date")
    private Instant createdDate;

    @Size(max = 60)
    @Column(name = "last_updated_by", length = 60)
    private String lastUpdatedBy;

    @Column(name = "last_updated_date")
    private Instant lastUpdatedDate;

    @Size(max = 100)
    @Column(name = "file_name", length = 100)
    private String fileName;

    @NotNull
    @Size(max = 500)
    @Column(name = "corespond_address", length = 500, nullable = false)
    private String corespondAddress;

    @Size(max = 15)
    @Column(name = "factory_code", length = 15)
    private String factoryCode;

    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name = "bank_master_id")
    @JsonIgnoreProperties("workerRecruitments")
    private BankMaster bankMaster;

    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name = "designation_master_id")
    @JsonIgnoreProperties("workerRecruitments")
    private DesignationMaster designationMaster;

    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name = "department_master_id")
    @JsonIgnoreProperties("")
    private DepartmentMaster departmentMaster;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAadharNo() {
        return aadharNo;
    }

    public WorkerRecruitment aadharNo(String aadharNo) {
        this.aadharNo = aadharNo;
        return this;
    }

    public void setAadharNo(String aadharNo) {
        this.aadharNo = aadharNo;
    }

    public String getName() {
        return name;
    }

    public WorkerRecruitment name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public WorkerRecruitment dob(LocalDate dob) {
        this.dob = dob;
        return this;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getFatherName() {
        return fatherName;
    }

    public WorkerRecruitment fatherName(String fatherName) {
        this.fatherName = fatherName;
        return this;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getAddress() {
        return address;
    }

    public WorkerRecruitment address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPanNo() {
        return panNo;
    }

    public WorkerRecruitment panNo(String panNo) {
        this.panNo = panNo;
        return this;
    }

    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public WorkerRecruitment bankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
        return this;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }

    public String getBankAccNo() {
        return bankAccNo;
    }

    public WorkerRecruitment bankAccNo(String bankAccNo) {
        this.bankAccNo = bankAccNo;
        return this;
    }

    public void setBankAccNo(String bankAccNo) {
        this.bankAccNo = bankAccNo;
    }

    public String getStatus() {
        return status;
    }

    public WorkerRecruitment status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public WorkerRecruitment createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public WorkerRecruitment createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public WorkerRecruitment lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public WorkerRecruitment lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getFileName() {
        return fileName;
    }

    public WorkerRecruitment fileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getCorespondAddress() {
        return corespondAddress;
    }

    public WorkerRecruitment corespondAddress(String corespondAddress) {
        this.corespondAddress = corespondAddress;
        return this;
    }

    public void setCorespondAddress(String corespondAddress) {
        this.corespondAddress = corespondAddress;
    }

    public BankMaster getBankMaster() {
        return bankMaster;
    }

    public WorkerRecruitment bankMaster(BankMaster bankMaster) {
        this.bankMaster = bankMaster;
        return this;
    }

    public void setBankMaster(BankMaster bankMaster) {
        this.bankMaster = bankMaster;
    }

    public DesignationMaster getDesignationMaster() {
        return designationMaster;
    }

    public WorkerRecruitment designationMaster(DesignationMaster designationMaster) {
        this.designationMaster = designationMaster;
        return this;
    }

    public void setDesignationMaster(DesignationMaster designationMaster) {
        this.designationMaster = designationMaster;
    }

    public DepartmentMaster getDepartmentMaster() {
        return departmentMaster;
    }

    public WorkerRecruitment departmentMaster(DepartmentMaster departmentMaster) {
        this.departmentMaster = departmentMaster;
        return this;
    }

    public void setDepartmentMaster(DepartmentMaster departmentMaster) {
        this.departmentMaster = departmentMaster;
    }

    public String getFactoryCode() {
        return factoryCode;
    }

    public void setFactoryCode(String factoryCode) {
        this.factoryCode = factoryCode;
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
        WorkerRecruitment workerRecruitment = (WorkerRecruitment) o;
        if (workerRecruitment.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), workerRecruitment.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "WorkerRecruitment{" +
            "id=" + getId() +
            ", aadharNo='" + getAadharNo() + "'" +
            ", name='" + getName() + "'" +
            ", dob='" + getDob() + "'" +
            ", fatherName='" + getFatherName() + "'" +
            ", address='" + getAddress() + "'" +
            ", panNo='" + getPanNo() + "'" +
            ", bankBranch='" + getBankBranch() + "'" +
            ", bankAccNo='" + getBankAccNo() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            ", fileName='" + getFileName() + "'" +
            ", corespondAddress='" + getCorespondAddress() + "'" +
            "}";
    }
}
