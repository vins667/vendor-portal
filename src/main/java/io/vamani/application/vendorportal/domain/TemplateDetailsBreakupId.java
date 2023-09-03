package io.vamani.application.vendorportal.domain;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Objects;

public class TemplateDetailsBreakupId implements Serializable {
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "template_details_id", nullable = false)
    private Long templateDetailsId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTemplateDetailsId() {
        return templateDetailsId;
    }

    public void setTemplateDetailsId(Long templateDetailsId) {
        this.templateDetailsId = templateDetailsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TemplateDetailsBreakupId that = (TemplateDetailsBreakupId) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(templateDetailsId, that.templateDetailsId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, templateDetailsId);
    }

    @Override
    public String toString() {
        return "TemplateDetailsBreakupId{" +
            "id=" + id +
            ", templateDetailsId=" + templateDetailsId +
            '}';
    }

    public TemplateDetailsBreakupId() {
    }

    public TemplateDetailsBreakupId(Long id, Long templateDetailsId) {
        this.id = id;
        this.templateDetailsId = templateDetailsId;
    }
}
