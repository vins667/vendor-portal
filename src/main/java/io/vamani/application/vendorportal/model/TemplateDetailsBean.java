package io.vamani.application.vendorportal.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.vamani.application.vendorportal.domain.TemplateMaster;

import java.io.Serializable;
import java.util.Objects;

/**
 * A TemplateDetails.
 */
public class TemplateDetailsBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String specification;

    private Boolean required;

    private Boolean showInBid;

    private String fieldType;

    private String fieldValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public Boolean getShowInBid() {
        return showInBid;
    }

    public void setShowInBid(Boolean showInBid) {
        this.showInBid = showInBid;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TemplateDetailsBean that = (TemplateDetailsBean) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "TemplateDetailsBean{" +
            "id=" + id +
            ", specification='" + specification + '\'' +
            ", required=" + required +
            ", showInBid=" + showInBid +
            ", fieldType='" + fieldType + '\'' +
            ", fieldValue='" + fieldValue + '\'' +
            '}';
    }
}
