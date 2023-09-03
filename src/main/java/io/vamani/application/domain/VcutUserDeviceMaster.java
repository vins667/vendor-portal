package io.vamani.application.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A VcutUserDeviceMaster.
 */
@Entity
@Table(name = "vcut_user_device_master")
public class VcutUserDeviceMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="vcutUserDeviceMasterSeq", sequenceName="vcut_user_device_master_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="vcutUserDeviceMasterSeq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"email", "activated", "langKey", "imageUrl", "activationKey", "resetKey", "resetDate", "ndaActivated", "imei", "serial", "deviceId" })
    private User user;

    @Size(max = 50)
    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Column(name = "created_date")
    private Instant createdDate;
	
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "vcut_device_line_master_id")
    private VcutDeviceLineMaster vcutDeviceLineMaster;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public VcutUserDeviceMaster createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public VcutUserDeviceMaster createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public VcutDeviceLineMaster getVcutDeviceLineMaster() {
        return vcutDeviceLineMaster;
    }

    public VcutUserDeviceMaster vcutDeviceLineMaster(VcutDeviceLineMaster vcutDeviceLineMaster) {
        this.vcutDeviceLineMaster = vcutDeviceLineMaster;
        return this;
    }

    public void setVcutDeviceLineMaster(VcutDeviceLineMaster vcutDeviceLineMaster) {
        this.vcutDeviceLineMaster = vcutDeviceLineMaster;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VcutUserDeviceMaster)) {
            return false;
        }
        return id != null && id.equals(((VcutUserDeviceMaster) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "VcutUserDeviceMaster{" +
            "id=" + getId() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            "}";
    }
}
