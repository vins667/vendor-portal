package io.vamani.application.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A JobProfile.
 */
@Entity
@Table(name = "job_profile")
public class JobProfile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="jobProfileSeq", sequenceName="job_profile_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="jobProfileSeq")
    private Long id;

    @Size(max = 50)
    @Column(name = "department", length = 50)
    private String department;

    @Size(max = 50)
    @Column(name = "designation", length = 50)
    private String designation;

    @Size(max = 200)
    @Column(name = "department_desc", length = 200)
    private String departmentDesc;

    @Size(max = 200)
    @Column(name = "designation_desc", length = 200)
    private String designationDesc;

    @Size(max = 10)
    @Column(name = "factory", length = 10)
    private String factory;

    @Size(max = 200)
    @Column(name = "file_name", length = 200)
    private String fileName;

    @Size(max = 100)
    @Column(name = "file_path", length = 100)
    private String filePath;

    @Column(name = "ordering")
    private Integer ordering;

    @Size(max = 10)
    @Column(name = "sw_code", length = 10)
    private String swCode;

    @Size(max = 10)
    @Column(name = "flow_type", length = 10)
    private String flowType;

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

    public String getDepartment() {
        return department;
    }

    public JobProfile department(String department) {
        this.department = department;
        return this;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDesignation() {
        return designation;
    }

    public JobProfile designation(String designation) {
        this.designation = designation;
        return this;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDepartmentDesc() {
        return departmentDesc;
    }

    public JobProfile departmentDesc(String departmentDesc) {
        this.departmentDesc = departmentDesc;
        return this;
    }

    public void setDepartmentDesc(String departmentDesc) {
        this.departmentDesc = departmentDesc;
    }

    public String getDesignationDesc() {
        return designationDesc;
    }

    public JobProfile designationDesc(String designationDesc) {
        this.designationDesc = designationDesc;
        return this;
    }

    public void setDesignationDesc(String designationDesc) {
        this.designationDesc = designationDesc;
    }

    public String getFileName() {
        return fileName;
    }

    public JobProfile fileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public JobProfile createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public JobProfile createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getOrdering() {
        return ordering;
    }

    public void setOrdering(Integer ordering) {
        this.ordering = ordering;
    }

    public String getSwCode() {
        return swCode;
    }

    public void setSwCode(String swCode) {
        this.swCode = swCode;
    }

    public String getFlowType() {
        return flowType;
    }

    public void setFlowType(String flowType) {
        this.flowType = flowType;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
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
        JobProfile jobProfile = (JobProfile) o;
        if (jobProfile.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), jobProfile.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "JobProfile{" +
            "id=" + getId() +
            ", department='" + getDepartment() + "'" +
            ", designation='" + getDesignation() + "'" +
            ", fileName='" + getFileName() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            "}";
    }
}
