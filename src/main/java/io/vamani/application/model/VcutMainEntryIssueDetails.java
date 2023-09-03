package io.vamani.application.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class VcutMainEntryIssueDetails implements Serializable {
    private Long issueId;
    private String issueDescription;
    private String issueDescriptionLocal;
    private List<VcutMainEntryCoordinateDetails> coordinateDetails;

    public Long getIssueId() {
        return issueId;
    }

    public void setIssueId(Long issueId) {
        this.issueId = issueId;
    }

    public String getIssueDescription() {
        return issueDescription;
    }

    public void setIssueDescription(String issueDescription) {
        this.issueDescription = issueDescription;
    }

    public String getIssueDescriptionLocal() {
        return issueDescriptionLocal;
    }

    public void setIssueDescriptionLocal(String issueDescriptionLocal) {
        this.issueDescriptionLocal = issueDescriptionLocal;
    }

    public List<VcutMainEntryCoordinateDetails> getCoordinateDetails() {
        return coordinateDetails;
    }

    public void setCoordinateDetails(List<VcutMainEntryCoordinateDetails> coordinateDetails) {
        this.coordinateDetails = coordinateDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VcutMainEntryIssueDetails that = (VcutMainEntryIssueDetails) o;
        return Objects.equals(issueId, that.issueId) &&
            Objects.equals(coordinateDetails, that.coordinateDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(issueId, coordinateDetails);
    }

    @Override
    public String toString() {
        return "VcutMainEntryIssueDetails{" +
            "issueId=" + issueId +
            ", coordinateDetails=" + coordinateDetails +
            '}';
    }
}
