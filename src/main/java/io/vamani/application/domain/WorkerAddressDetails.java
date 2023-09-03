package io.vamani.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A WorkerAddressDetails.
 */
@Entity
@Table(name = "worker_address_details")
public class WorkerAddressDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="workerAddressDetailsSeq", sequenceName="worker_address_details_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="workerAddressDetailsSeq")
    private Long id;

    @Size(max = 1)
    @Column(name = "address_type", length = 1)
    private String addressType;

    @NotNull
    @Size(max = 100)
    @Column(name = "address_line_1", length = 100, nullable = false)
    private String addressLine1;

    @Size(max = 100)
    @Column(name = "address_line_2", length = 100)
    private String addressLine2;

    @Size(max = 100)
    @Column(name = "address_line_3", length = 100)
    private String addressLine3;

    @Size(max = 100)
    @Column(name = "address_line_4", length = 100)
    private String addressLine4;

    @Size(max = 20)
    @Column(name = "pin_code", length = 20)
    private String pinCode;

    @Size(max = 20)
    @Column(name = "telephone_no", length = 20)
    private String telephoneNo;

    @Size(max = 20)
    @Column(name = "mobile_no", length = 20)
    private String mobileNo;

    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name = "recruitment_country_master_id")
    @JsonIgnoreProperties("")
    private RecruitmentCountryMaster recruitmentCountryMaster;

    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name = "recruitment_state_master_id")
    @JsonIgnoreProperties("")
    private RecruitmentStateMaster recruitmentStateMaster;

    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name = "recruitment_district_id")
    @JsonIgnoreProperties("")
    private RecruitmentDistrict recruitmentDistrict;

    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name = "recruitment_city_master_id")
    @JsonIgnoreProperties("")
    private RecruitmentCityMaster recruitmentCityMaster;

    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name = "worker_joining_id")
    @JsonIgnoreProperties("")
    private WorkerJoining workerJoining;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddressType() {
        return addressType;
    }

    public WorkerAddressDetails addressType(String addressType) {
        this.addressType = addressType;
        return this;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public WorkerAddressDetails addressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
        return this;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public WorkerAddressDetails addressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
        return this;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getAddressLine3() {
        return addressLine3;
    }

    public WorkerAddressDetails addressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
        return this;
    }

    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    public String getAddressLine4() {
        return addressLine4;
    }

    public WorkerAddressDetails addressLine4(String addressLine4) {
        this.addressLine4 = addressLine4;
        return this;
    }

    public void setAddressLine4(String addressLine4) {
        this.addressLine4 = addressLine4;
    }

    public String getPinCode() {
        return pinCode;
    }

    public WorkerAddressDetails pinCode(String pinCode) {
        this.pinCode = pinCode;
        return this;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getTelephoneNo() {
        return telephoneNo;
    }

    public WorkerAddressDetails telephoneNo(String telephoneNo) {
        this.telephoneNo = telephoneNo;
        return this;
    }

    public void setTelephoneNo(String telephoneNo) {
        this.telephoneNo = telephoneNo;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public WorkerAddressDetails mobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
        return this;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public RecruitmentCountryMaster getRecruitmentCountryMaster() {
        return recruitmentCountryMaster;
    }

    public WorkerAddressDetails recruitmentCountryMaster(RecruitmentCountryMaster recruitmentCountryMaster) {
        this.recruitmentCountryMaster = recruitmentCountryMaster;
        return this;
    }

    public void setRecruitmentCountryMaster(RecruitmentCountryMaster recruitmentCountryMaster) {
        this.recruitmentCountryMaster = recruitmentCountryMaster;
    }

    public RecruitmentStateMaster getRecruitmentStateMaster() {
        return recruitmentStateMaster;
    }

    public WorkerAddressDetails recruitmentStateMaster(RecruitmentStateMaster recruitmentStateMaster) {
        this.recruitmentStateMaster = recruitmentStateMaster;
        return this;
    }

    public void setRecruitmentStateMaster(RecruitmentStateMaster recruitmentStateMaster) {
        this.recruitmentStateMaster = recruitmentStateMaster;
    }

    public RecruitmentDistrict getRecruitmentDistrict() {
        return recruitmentDistrict;
    }

    public WorkerAddressDetails recruitmentDistrict(RecruitmentDistrict recruitmentDistrict) {
        this.recruitmentDistrict = recruitmentDistrict;
        return this;
    }

    public void setRecruitmentDistrict(RecruitmentDistrict recruitmentDistrict) {
        this.recruitmentDistrict = recruitmentDistrict;
    }

    public RecruitmentCityMaster getRecruitmentCityMaster() {
        return recruitmentCityMaster;
    }

    public WorkerAddressDetails recruitmentCityMaster(RecruitmentCityMaster recruitmentCityMaster) {
        this.recruitmentCityMaster = recruitmentCityMaster;
        return this;
    }

    public void setRecruitmentCityMaster(RecruitmentCityMaster recruitmentCityMaster) {
        this.recruitmentCityMaster = recruitmentCityMaster;
    }

    public WorkerJoining getWorkerJoining() {
        return workerJoining;
    }

    public WorkerAddressDetails workerJoining(WorkerJoining workerJoining) {
        this.workerJoining = workerJoining;
        return this;
    }

    public void setWorkerJoining(WorkerJoining workerJoining) {
        this.workerJoining = workerJoining;
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
        WorkerAddressDetails workerAddressDetails = (WorkerAddressDetails) o;
        if (workerAddressDetails.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), workerAddressDetails.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "WorkerAddressDetails{" +
            "id=" + getId() +
            ", addressType='" + getAddressType() + "'" +
            ", addressLine1='" + getAddressLine1() + "'" +
            ", addressLine2='" + getAddressLine2() + "'" +
            ", addressLine3='" + getAddressLine3() + "'" +
            ", addressLine4='" + getAddressLine4() + "'" +
            ", pinCode='" + getPinCode() + "'" +
            ", telephoneNo='" + getTelephoneNo() + "'" +
            ", mobileNo='" + getMobileNo() + "'" +
            "}";
    }
}
