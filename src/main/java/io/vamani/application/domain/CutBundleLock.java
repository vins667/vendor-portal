package io.vamani.application.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.Instant;
/**
 * A CutBundleLock.
 */
@Entity
@Table(name = "cut_bundle_lock")
public class CutBundleLock implements Serializable {
    @EmbeddedId
    private CutBundleLockId id;

    @Column(name = "locked_date")
    private Instant lockedDate;

    @Column(name = "locked_by", length = 50)
    private String lockedBy;

    public CutBundleLockId getId() {
        return id;
    }

    public void setId(CutBundleLockId id) {
        this.id = id;
    }

    public Instant getLockedDate() {
        return lockedDate;
    }

    public void setLockedDate(Instant lockedDate) {
        this.lockedDate = lockedDate;
    }

    public String getLockedBy() {
        return lockedBy;
    }

    public void setLockedBy(String lockedBy) {
        this.lockedBy = lockedBy;
    }
}
