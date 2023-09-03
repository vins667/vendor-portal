package io.vamani.application.vendorportal.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A TemplateMaster.
 */
@Entity
@Table(name = "template_master")
public class TemplateMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="templateMasterSeq", sequenceName="template_master_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="templateMasterSeq")
    private Long id;

    @NotNull
    @Size(max = 200)
    @Column(name = "template_name", length = 200, nullable = false)
    private String templateName;

    @NotNull
    @Size(max = 1)
    @Column(name = "flag", length = 1, nullable = false)
    private String flag;

    @Size(max = 50)
    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Column(name = "created_date")
    private Instant createdDate;

    @Size(max = 50)
    @Column(name = "last_update_by", length = 50)
    private String lastUpdateBy;

    @Column(name = "last_updated_date")
    private Instant lastUpdatedDate;

    @OneToMany(mappedBy = "templateMaster", fetch = FetchType.EAGER)
    private Set<TemplateDetails> templateDetails = new HashSet<>();

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("")
    @JoinColumn(name = "category_master_id")
    private CategoryMaster categoryMaster;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTemplateName() {
        return templateName;
    }

    public TemplateMaster templateName(String templateName) {
        this.templateName = templateName;
        return this;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName!=null ? templateName.toUpperCase() : "";
    }

    public String getFlag() {
        return flag;
    }

    public TemplateMaster flag(String flag) {
        this.flag = flag;
        return this;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public TemplateMaster createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public TemplateMaster createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    public TemplateMaster lastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
        return this;
    }

    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public TemplateMaster lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public Set<TemplateDetails> getTemplateDetails() {
        return templateDetails;
    }

    public TemplateMaster templateDetails(Set<TemplateDetails> templateDetails) {
        this.templateDetails = templateDetails;
        return this;
    }

    public TemplateMaster addTemplateDetails(TemplateDetails templateDetails) {
        this.templateDetails.add(templateDetails);
        templateDetails.setTemplateMaster(this);
        return this;
    }

    public TemplateMaster removeTemplateDetails(TemplateDetails templateDetails) {
        this.templateDetails.remove(templateDetails);
        templateDetails.setTemplateMaster(null);
        return this;
    }

    public void setTemplateDetails(Set<TemplateDetails> templateDetails) {
        this.templateDetails = templateDetails;
    }

    public CategoryMaster getCategoryMaster() {
        return categoryMaster;
    }

    public TemplateMaster categoryMaster(CategoryMaster categoryMaster) {
        this.categoryMaster = categoryMaster;
        return this;
    }

    public void setCategoryMaster(CategoryMaster categoryMaster) {
        this.categoryMaster = categoryMaster;
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
        TemplateMaster templateMaster = (TemplateMaster) o;
        if (templateMaster.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), templateMaster.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TemplateMaster{" +
            "id=" + getId() +
            ", templateName='" + getTemplateName() + "'" +
            ", flag='" + getFlag() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastUpdateBy='" + getLastUpdateBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            "}";
    }
}
