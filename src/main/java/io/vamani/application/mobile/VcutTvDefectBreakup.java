package io.vamani.application.mobile;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class VcutTvDefectBreakup implements Serializable {
    private Long id;
    private String description;
    private String descriptionLocal;
    private Long defectCount;
    private List<VcutTvDefectBreakupSummary> summaries;

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

    public Long getDefectCount() {
        return defectCount;
    }

    public void setDefectCount(Long defectCount) {
        this.defectCount = defectCount;
    }

    public List<VcutTvDefectBreakupSummary> getSummaries() {
        return summaries;
    }

    public void setSummaries(List<VcutTvDefectBreakupSummary> summaries) {
        this.summaries = summaries;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VcutTvDefectBreakup that = (VcutTvDefectBreakup) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(description, that.description) &&
            Objects.equals(descriptionLocal, that.descriptionLocal) &&
            Objects.equals(defectCount, that.defectCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, descriptionLocal, defectCount);
    }

    @Override
    public String toString() {
        return "VcutTvDefectBreakup{" +
            "id=" + id +
            ", description='" + description + '\'' +
            ", descriptionLocal='" + descriptionLocal + '\'' +
            ", defectCount=" + defectCount +
            '}';
    }
}
