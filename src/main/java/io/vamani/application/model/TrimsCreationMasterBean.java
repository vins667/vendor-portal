package io.vamani.application.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A TrimsCreationMaster.
 */
public class TrimsCreationMasterBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String code;

    private String description;

    private String flag;

    private String createdBy;

    private Instant createdDate;

    private TrimsTemplateMasterBean trimsTemplateMaster;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public TrimsTemplateMasterBean getTrimsTemplateMaster() {
        return trimsTemplateMaster;
    }

    public void setTrimsTemplateMaster(TrimsTemplateMasterBean trimsTemplateMaster) {
        this.trimsTemplateMaster = trimsTemplateMaster;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrimsCreationMasterBean that = (TrimsCreationMasterBean) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(code, that.code) &&
            Objects.equals(description, that.description) &&
            Objects.equals(flag, that.flag) &&
            Objects.equals(createdBy, that.createdBy) &&
            Objects.equals(createdDate, that.createdDate) &&
            Objects.equals(trimsTemplateMaster, that.trimsTemplateMaster);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, description, flag, createdBy, createdDate, trimsTemplateMaster);
    }

    @Override
    public String toString() {
        return "TrimsCreationMasterBean{" +
            "id=" + id +
            ", code='" + code + '\'' +
            ", description='" + description + '\'' +
            ", flag='" + flag + '\'' +
            ", createdBy='" + createdBy + '\'' +
            ", createdDate=" + createdDate +
            ", trimsTemplateMaster=" + trimsTemplateMaster +
            '}';
    }
}
