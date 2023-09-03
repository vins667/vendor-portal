package io.vamani.application.domain;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * A VcutDeviceLineMaster.
 */
@Entity
@Table(name = "vcut_device_line_master")
public class VcutDeviceLineMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="vcutDeviceLineMasterSeq", sequenceName="vcut_device_line_master_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="vcutDeviceLineMasterSeq")
    private Long id;

    @Size(max = 50)
    @Column(name = "line", length = 50)
    private String line;

    @Column(name = "device_id")
    private String deviceId;

    @Size(max = 50)
    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Column(name = "created_date")
    private Instant createdDate;

    @OneToMany(mappedBy="vcutDeviceLineMaster")
    private Set<VcutUserDeviceMaster> vcutUserDeviceMaster = new HashSet<>();
    
    public Long getId() {
        return id;
    }

    public Set<VcutUserDeviceMaster> getVcutUserDeviceMaster() {
		return vcutUserDeviceMaster;
	}

	public void setVcutUserDeviceMaster(Set<VcutUserDeviceMaster> vcutUserDeviceMaster) {
		this.vcutUserDeviceMaster = vcutUserDeviceMaster;
	}

	public void setId(Long id) {
        this.id = id;
    }

    public String getLine() {
        return line;
    }

    public VcutDeviceLineMaster line(String line) {
        this.line = line != null ? line.toUpperCase().trim() : "";
        return this;
    }

    public void setLine(String line) {
        this.line = line != null ? line.toUpperCase().trim() : "";
    }

    public String getDeviceId() {
        return deviceId;
    }

    public VcutDeviceLineMaster deviceId(String deviceId) {
        this.deviceId = deviceId != null ? deviceId.toUpperCase().trim() : "";
        return this;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId != null ? deviceId.toUpperCase().trim() : "";
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public VcutDeviceLineMaster createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public VcutDeviceLineMaster createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VcutDeviceLineMaster)) {
            return false;
        }
        return id != null && id.equals(((VcutDeviceLineMaster) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "VcutDeviceLineMaster{" +
            "id=" + getId() +
            ", line='" + getLine() + "'" +
            ", deviceId='" + getDeviceId() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            "}";
    }
}
