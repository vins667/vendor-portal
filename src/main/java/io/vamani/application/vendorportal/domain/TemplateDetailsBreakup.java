package io.vamani.application.vendorportal.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "template_details_breakup")
public class TemplateDetailsBreakup implements Serializable {
	
    @EmbeddedId
    private TemplateDetailsBreakupId id;

    @Column(name = "description")
    private String description;

    public TemplateDetailsBreakupId getId() {
        return id;
    }

    public void setId(TemplateDetailsBreakupId id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TemplateDetailsBreakup that = (TemplateDetailsBreakup) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description);
    }

    @Override
    public String toString() {
        return "TemplateDetailsBreakup{" +
            "id=" + id +
            ", description='" + description + '\'' +
            '}';
    }
}
