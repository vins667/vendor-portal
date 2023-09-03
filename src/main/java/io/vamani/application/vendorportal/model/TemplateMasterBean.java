package io.vamani.application.vendorportal.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.vamani.application.vendorportal.domain.CategoryMaster;
import io.vamani.application.vendorportal.domain.TemplateDetails;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * A TemplateMaster.
 */

public class TemplateMasterBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String templateName;

    private String flag;

    private List<TemplateDetailsBean> templateDetails;

    private CategoryMaster categoryMaster;

    private String createdBy;

    private Instant createdDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public List<TemplateDetailsBean> getTemplateDetails() {
        return templateDetails;
    }

    public void setTemplateDetails(List<TemplateDetailsBean> templateDetails) {
        this.templateDetails = templateDetails;
    }

    public CategoryMaster getCategoryMaster() {
        return categoryMaster;
    }

    public void setCategoryMaster(CategoryMaster categoryMaster) {
        this.categoryMaster = categoryMaster;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TemplateMasterBean that = (TemplateMasterBean) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "TemplateMasterBean{" +
            "id=" + id +
            ", templateName='" + templateName + '\'' +
            ", flag='" + flag + '\'' +
            ", templateDetails=" + templateDetails +
            ", categoryMaster=" + categoryMaster +
            '}';
    }
}
