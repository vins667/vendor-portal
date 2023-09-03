package io.vamani.application.mobile;

import java.io.Serializable;
import java.util.Objects;

public class VcutOperationIssueMasterBean implements Serializable {
    private Long id;
    private String description;
    private String descriptionLocal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionLocal() {
        return descriptionLocal;
    }

    public void setDescriptionLocal(String descriptionLocal) {
        this.descriptionLocal = descriptionLocal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VcutOperationIssueMasterBean that = (VcutOperationIssueMasterBean) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(description, that.description) &&
            Objects.equals(descriptionLocal, that.descriptionLocal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, descriptionLocal);
    }
}
