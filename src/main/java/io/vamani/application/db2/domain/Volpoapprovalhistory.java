package io.vamani.application.db2.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "volpoapprovalhistory")
public class Volpoapprovalhistory {
    @EmbeddedId
    private VolpoapprovalhistoryId id;
    @Column(name = "FIRSTAPPROVERIDUSERID")
    private String firstapproveriduserid;
    @Column(name = "APPROVERSTATUS")
    private String approverstatus;

    public VolpoapprovalhistoryId getId() {
        return id;
    }

    public void setId(VolpoapprovalhistoryId id) {
        this.id = id;
    }

    public String getFirstapproveriduserid() {
        return firstapproveriduserid;
    }

    public void setFirstapproveriduserid(String firstapproveriduserid) {
        this.firstapproveriduserid = firstapproveriduserid;
    }

    public String getApproverstatus() {
        return approverstatus;
    }

    public void setApproverstatus(String approverstatus) {
        this.approverstatus = approverstatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Volpoapprovalhistory that = (Volpoapprovalhistory) o;
        return Objects.equals(id, that.id) && Objects.equals(firstapproveriduserid, that.firstapproveriduserid) && Objects.equals(approverstatus, that.approverstatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstapproveriduserid, approverstatus);
    }

    @Override
    public String toString() {
        return "Volpoapprovalhistory{" +
            "id=" + id +
            ", firstapproveriduserid='" + firstapproveriduserid + '\'' +
            ", approverstatus='" + approverstatus + '\'' +
            '}';
    }
}
