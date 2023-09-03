package io.vamani.application.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A SmsRegistration.
 */
@Entity
@Table(name = "sms_registration")
public class SmsRegistration implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="smsRegistrationSeq", sequenceName="sms_registration_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="smsRegistrationSeq")
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "card_no", length = 50, nullable = false)
    private String cardNo;

    @NotNull
    @Size(max = 20)
    @Column(name = "mobile_number", length = 20, nullable = false)
    private String mobileNumber;

    @Size(max = 6)
    @Column(name = "otp", length = 6)
    @JsonIgnore
    private String otp;

    @Size(max = 50)
    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Column(name = "created_date")
    private Instant createdDate;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardNo() {
        return cardNo;
    }

    public SmsRegistration cardNo(String cardNo) {
        this.cardNo = cardNo;
        return this;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public SmsRegistration mobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
        return this;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getOtp() {
        return otp;
    }

    public SmsRegistration otp(String otp) {
        this.otp = otp;
        return this;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public SmsRegistration createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public SmsRegistration createdDate(Instant createdDate) {
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
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SmsRegistration smsRegistration = (SmsRegistration) o;
        if (smsRegistration.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), smsRegistration.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SmsRegistration{" +
            "id=" + getId() +
            ", cardNo='" + getCardNo() + "'" +
            ", mobileNumber='" + getMobileNumber() + "'" +
            ", otp='" + getOtp() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            "}";
    }
}
