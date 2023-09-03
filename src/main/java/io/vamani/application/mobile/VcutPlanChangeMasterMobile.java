package io.vamani.application.mobile;

import java.io.Serializable;
import java.util.Objects;

public class VcutPlanChangeMasterMobile implements Serializable {
    private Long id;
    private String description;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VcutPlanChangeMasterMobile that = (VcutPlanChangeMasterMobile) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description);
    }

    @Override
    public String toString() {
        return "VcutPlanChangeMasterMobile{" +
            "id=" + id +
            ", description='" + description + '\'' +
            '}';
    }
}
