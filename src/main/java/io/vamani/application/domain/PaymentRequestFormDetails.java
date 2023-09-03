package io.vamani.application.domain;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A PaymentRequestFormDetails.
 */
@Entity
@Table(name = "payment_request_form_details")
public class PaymentRequestFormDetails implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @SequenceGenerator(name = "paymentRequestFormDetailsSeq", sequenceName = "payment_request_form_details_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "paymentRequestFormDetailsSeq")
  @Column(name = "id")
  private Long id;

  @Size(max = 50)
  @Column(name = "emp_code", length = 50)
  private String empCode;

  @Size(max = 200)
  @Column(name = "emp_name", length = 200)
  private String empName;

  @Size(max = 1)
  @Column(name = "flag", length = 1)
  private String flag;

  @Size(max = 50)
  @Column(name = "forward_code", length = 50)
  private String forwardCode;

  @Size(max = 200)
  @Column(name = "forward_name", length = 200)
  private String forwardName;

  @Size(max = 200)
  @Column(name = "remarks", length = 200)
  private String remarks;

  @Size(max = 50)
  @Column(name = "created_by", length = 50)
  private String createdBy;

  @Column(name = "created_date")
  private Instant createdDate;

  @Column(name = "payment_request_form_id")
  private Long paymentRequestFormId;

  // jhipster-needle-entity-add-field - JHipster will add fields here

  public Long getId() {
    return this.id;
  }

  public PaymentRequestFormDetails id(Long id) {
    this.setId(id);
    return this;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getEmpCode() {
    return this.empCode;
  }

  public PaymentRequestFormDetails empCode(String empCode) {
    this.setEmpCode(empCode);
    return this;
  }

  public void setEmpCode(String empCode) {
    this.empCode = empCode;
  }

  public String getEmpName() {
    return this.empName;
  }

  public PaymentRequestFormDetails empName(String empName) {
    this.setEmpName(empName);
    return this;
  }

  public void setEmpName(String empName) {
    this.empName = empName;
  }

  public String getFlag() {
    return this.flag;
  }

  public PaymentRequestFormDetails flag(String flag) {
    this.setFlag(flag);
    return this;
  }

  public void setFlag(String flag) {
    this.flag = flag;
  }

  public String getForwardCode() {
    return this.forwardCode;
  }

  public PaymentRequestFormDetails forwardCode(String forwardCode) {
    this.setForwardCode(forwardCode);
    return this;
  }

  public void setForwardCode(String forwardCode) {
    this.forwardCode = forwardCode;
  }

  public String getForwardName() {
    return this.forwardName;
  }

  public PaymentRequestFormDetails forwardName(String forwardName) {
    this.setForwardName(forwardName);
    return this;
  }

  public void setForwardName(String forwardName) {
    this.forwardName = forwardName;
  }

  public String getRemarks() {
    return this.remarks;
  }

  public PaymentRequestFormDetails remarks(String remarks) {
    this.setRemarks(remarks);
    return this;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  public String getCreatedBy() {
    return this.createdBy;
  }

  public PaymentRequestFormDetails createdBy(String createdBy) {
    this.setCreatedBy(createdBy);
    return this;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public Instant getCreatedDate() {
    return this.createdDate;
  }

  public PaymentRequestFormDetails createdDate(Instant createdDate) {
    this.setCreatedDate(createdDate);
    return this;
  }

  public void setCreatedDate(Instant createdDate) {
    this.createdDate = createdDate;
  }

    public Long getPaymentRequestFormId() {
        return paymentRequestFormId;
    }

    public void setPaymentRequestFormId(Long paymentRequestFormId) {
        this.paymentRequestFormId = paymentRequestFormId;
    }
// jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof PaymentRequestFormDetails)) {
      return false;
    }
    return id != null && id.equals(((PaymentRequestFormDetails) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "PaymentRequestFormDetails{" +
            "id=" + getId() +
            ", empCode='" + getEmpCode() + "'" +
            ", empName='" + getEmpName() + "'" +
            ", flag='" + getFlag() + "'" +
            ", forwardCode='" + getForwardCode() + "'" +
            ", forwardName='" + getForwardName() + "'" +
            ", remarks='" + getRemarks() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            "}";
    }
}
