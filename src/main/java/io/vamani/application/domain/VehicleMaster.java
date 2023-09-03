package io.vamani.application.domain;


import io.vamani.application.mssql.domain.EmployeeView;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A VehicleMaster.
 */
@Entity
@Table(name = "vehicle_master")
public class VehicleMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="vehicleMasterSeq", sequenceName="vehicle_master_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="vehicleMasterSeq")
    private Long id;

    @NotNull
    @OneToOne
    @JoinColumn(name="emp_code", referencedColumnName="login")
    private User user;

    @NotNull
    @Size(max = 2)
    @Column(name = "vehicle_type", length = 2, nullable = false)
    private String vehicleType;

    @NotNull
    @Column(name = "no_vehicle", nullable = false)
    private Integer noVehicle;

    @NotNull
    @Column(name = "vehicle_date", nullable = false)
    private Instant vehicleDate;

    @Size(max = 250)
    @Column(name = "place_from", length = 250)
    private String placeFrom;

    @Size(max = 250)
    @Column(name = "place_to", length = 250)
    private String placeTo;

    @Size(max = 500)
    @Column(name = "purpose", length = 500)
    private String purpose;

    @Size(max = 1)
    @Column(name = "flag", length = 1)
    private String flag;

    @Size(max = 50)
    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Column(name = "created_date")
    private Instant createdDate;

    @Size(max = 50)
    @Column(name = "hod_approved_by", length = 50)
    private String hodApprovedBy;

    @Column(name = "hod_approved_date")
    private Instant hodApprovedDate;

    @Size(max = 100)
    @Column(name = "vehicle_number", length = 100)
    private String vehicleNumber;

    @Size(max = 100)
    @Column(name = "driver_name", length = 100)
    private String driverName;

    @Size(max = 50)
    @Column(name = "admin_approved_by", length = 50)
    private String adminApprovedBy;

    @Column(name = "admin_approved_date")
    private Instant adminApprovedDate;

    @Size(max = 500)
    @Column(name = "admin_remarks", length = 500)
    private String adminRemarks;

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

    public VehicleMaster user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public VehicleMaster vehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
        return this;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Integer getNoVehicle() {
        return noVehicle;
    }

    public VehicleMaster noVehicle(Integer noVehicle) {
        this.noVehicle = noVehicle;
        return this;
    }

    public void setNoVehicle(Integer noVehicle) {
        this.noVehicle = noVehicle;
    }

    public Instant getVehicleDate() {
        return vehicleDate;
    }

    public VehicleMaster vehicleDate(Instant vehicleDate) {
        this.vehicleDate = vehicleDate;
        return this;
    }

    public void setVehicleDate(Instant vehicleDate) {
        this.vehicleDate = vehicleDate;
    }

    public String getPlaceFrom() {
        return placeFrom;
    }

    public VehicleMaster placeFrom(String placeFrom) {
        this.placeFrom = placeFrom;
        return this;
    }

    public void setPlaceFrom(String placeFrom) {
        this.placeFrom = placeFrom;
    }

    public String getPlaceTo() {
        return placeTo;
    }

    public VehicleMaster placeTo(String placeTo) {
        this.placeTo = placeTo;
        return this;
    }

    public void setPlaceTo(String placeTo) {
        this.placeTo = placeTo;
    }

    public String getPurpose() {
        return purpose;
    }

    public VehicleMaster purpose(String purpose) {
        this.purpose = purpose;
        return this;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getFlag() {
        return flag;
    }

    public VehicleMaster flag(String flag) {
        this.flag = flag;
        return this;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public VehicleMaster createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public VehicleMaster createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getHodApprovedBy() {
        return hodApprovedBy;
    }

    public VehicleMaster hodApprovedBy(String hodApprovedBy) {
        this.hodApprovedBy = hodApprovedBy;
        return this;
    }

    public void setHodApprovedBy(String hodApprovedBy) {
        this.hodApprovedBy = hodApprovedBy;
    }

    public Instant getHodApprovedDate() {
        return hodApprovedDate;
    }

    public VehicleMaster hodApprovedDate(Instant hodApprovedDate) {
        this.hodApprovedDate = hodApprovedDate;
        return this;
    }

    public void setHodApprovedDate(Instant hodApprovedDate) {
        this.hodApprovedDate = hodApprovedDate;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public VehicleMaster vehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
        return this;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getDriverName() {
        return driverName;
    }

    public VehicleMaster driverName(String driverName) {
        this.driverName = driverName;
        return this;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getAdminApprovedBy() {
        return adminApprovedBy;
    }

    public VehicleMaster adminApprovedBy(String adminApprovedBy) {
        this.adminApprovedBy = adminApprovedBy;
        return this;
    }

    public void setAdminApprovedBy(String adminApprovedBy) {
        this.adminApprovedBy = adminApprovedBy;
    }

    public Instant getAdminApprovedDate() {
        return adminApprovedDate;
    }

    public VehicleMaster adminApprovedDate(Instant adminApprovedDate) {
        this.adminApprovedDate = adminApprovedDate;
        return this;
    }

    public String getAdminRemarks() {
        return adminRemarks;
    }

    public VehicleMaster adminRemarks(String adminRemarks) {
        this.adminRemarks = adminRemarks;
        return this;
    }

    public void setAdminRemarks(String adminRemarks) {
        this.adminRemarks = adminRemarks;
    }

    public void setAdminApprovedDate(Instant adminApprovedDate) {
        this.adminApprovedDate = adminApprovedDate;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        VehicleMaster vehicleMaster = (VehicleMaster) o;
        if (vehicleMaster.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), vehicleMaster.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "VehicleMaster{" +
            "id=" + getId() +
            ", empCode='" + getUser() + "'" +
            ", vehicleType='" + getVehicleType() + "'" +
            ", noVehicle=" + getNoVehicle() +
            ", vehicleDate='" + getVehicleDate() + "'" +
            ", placeFrom='" + getPlaceFrom() + "'" +
            ", placeTo='" + getPlaceTo() + "'" +
            ", purpose='" + getPurpose() + "'" +
            ", flag='" + getFlag() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", hodApprovedBy='" + getHodApprovedBy() + "'" +
            ", hodApprovedDate='" + getHodApprovedDate() + "'" +
            ", vehicleNumber='" + getVehicleNumber() + "'" +
            ", driverName='" + getDriverName() + "'" +
            ", adminApprovedBy='" + getAdminApprovedBy() + "'" +
            ", adminApprovedDate='" + getAdminApprovedDate() + "'" +
            ", adminRemarks='" + getAdminRemarks() + "'" +
            "}";
    }
}
