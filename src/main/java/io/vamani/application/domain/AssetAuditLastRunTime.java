package io.vamani.application.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A AssetAuditRunTime.
 */
@Entity
@Table(name = "asset_audit_last_run_time")
public class AssetAuditLastRunTime implements Serializable {
    @Id
    @Column(name = "run_time")
    private Instant runTime;

    public Instant getRunTime() {
        return runTime;
    }

    public void setRunTime(Instant runTime) {
        this.runTime = runTime;
    }

    public AssetAuditLastRunTime() {
    }

    public AssetAuditLastRunTime(Instant runTime) {
        this.runTime = runTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssetAuditLastRunTime that = (AssetAuditLastRunTime) o;
        return Objects.equals(runTime, that.runTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(runTime);
    }

    @Override
    public String toString() {
        return "AssetAuditLastRunTime{" +
            "runTime=" + runTime +
            '}';
    }
}
