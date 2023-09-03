package io.vamani.application.model;

import io.vamani.application.domain.TrimsTemplateDetailsBreakup;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class TrimsTemplateDetailsBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String specification;

    private Boolean required;

    private String fieldType;

    private Boolean display;

    private String fieldValue;

    private String fieldValueDropDown;

    private Set<TrimsTemplateDetailsBreakup> trimTemplateDetailsBreakup = new HashSet<>();

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

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public Boolean getDisplay() {
        return display;
    }

    public void setDisplay(Boolean display) {
        this.display = display;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

    public String getFieldValueDropDown() {
        return fieldValueDropDown;
    }

    public void setFieldValueDropDown(String fieldValueDropDown) {
        this.fieldValueDropDown = fieldValueDropDown;
    }

    public Set<TrimsTemplateDetailsBreakup> getTrimTemplateDetailsBreakup() {
        return trimTemplateDetailsBreakup;
    }

    public void setTrimTemplateDetailsBreakup(Set<TrimsTemplateDetailsBreakup> trimTemplateDetailsBreakup) {
        this.trimTemplateDetailsBreakup = trimTemplateDetailsBreakup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrimsTemplateDetailsBean that = (TrimsTemplateDetailsBean) o;
        return display == that.display &&
            Objects.equals(id, that.id) &&
            Objects.equals(specification, that.specification) &&
            Objects.equals(required, that.required) &&
            Objects.equals(fieldType, that.fieldType) &&
            Objects.equals(fieldValue, that.fieldValue) &&
            Objects.equals(fieldValueDropDown, that.fieldValueDropDown) &&
            Objects.equals(trimTemplateDetailsBreakup, that.trimTemplateDetailsBreakup);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, specification, required, fieldType, display, fieldValue, fieldValueDropDown, trimTemplateDetailsBreakup);
    }

    @Override
    public String toString() {
        return "TrimsTemplateDetailsBean{" +
            "id=" + id +
            ", specification='" + specification + '\'' +
            ", required='" + required + '\'' +
            ", fieldType='" + fieldType + '\'' +
            ", display=" + display +
            ", fieldValue='" + fieldValue + '\'' +
            ", fieldValueDropDown='" + fieldValueDropDown + '\'' +
            ", trimTemplateDetailsBreakup=" + trimTemplateDetailsBreakup +
            '}';
    }
}
