package io.vamani.application.domain;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A PaymentRequestForward.
 */
@Entity
@Table(name = "payment_request_forward")
public class PaymentRequestForward implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @SequenceGenerator(name = "paymentRequestForwardSeq", sequenceName = "payment_request_forward_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "paymentRequestForwardSeq")
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

  @Size(max = 50)
  @Column(name = "created_by", length = 50)
  private String createdBy;

  @Column(name = "created_date")
  private Instant createdDate;

  // jhipster-needle-entity-add-field - JHipster will add fields here

  public Long getId() {
    return this.id;
  }

  public PaymentRequestForward id(Long id) {
    this.setId(id);
    return this;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getEmpCode() {
    return this.empCode;
  }

  public PaymentRequestForward empCode(String empCode) {
    this.setEmpCode(empCode);
    return this;
  }

  public void setEmpCode(String empCode) {
    this.empCode = empCode;
  }

  public String getEmpName() {
    return this.empName;
  }

  public PaymentRequestForward empName(String empName) {
    this.setEmpName(empName);
    return this;
  }

  public void setEmpName(String empName) {
    this.empName = empName;
  }

  public String getFlag() {
    return this.flag;
  }

  public PaymentRequestForward flag(String flag) {
    this.setFlag(flag);
    return this;
  }

  public void setFlag(String flag) {
    this.flag = flag;
  }

  public String getForwardCode() {
    return this.forwardCode;
  }

  public PaymentRequestForward forwardCode(String forwardCode) {
    this.setForwardCode(forwardCode);
    return this;
  }

  public void setForwardCode(String forwardCode) {
    this.forwardCode = forwardCode;
  }

  public String getForwardName() {
    return this.forwardName;
  }

  public PaymentRequestForward forwardName(String forwardName) {
    this.setForwardName(forwardName);
    return this;
  }

  public void setForwardName(String forwardName) {
    this.forwardName = forwardName;
  }

  public String getCreatedBy() {
    return this.createdBy;
  }

  public PaymentRequestForward createdBy(String createdBy) {
    this.setCreatedBy(createdBy);
    return this;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public Instant getCreatedDate() {
    return this.createdDate;
  }

  public PaymentRequestForward createdDate(Instant createdDate) {
    this.setCreatedDate(createdDate);
    return this;
  }

  public void setCreatedDate(Instant createdDate) {
    this.createdDate = createdDate;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof PaymentRequestForward)) {
      return false;
    }
    return id != null && id.equals(((PaymentRequestForward) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "PaymentRequestForward{" +
            "id=" + getId() +
            ", empCode='" + getEmpCode() + "'" +
            ", empName='" + getEmpName() + "'" +
            ", flag='" + getFlag() + "'" +
            ", forwardCode='" + getForwardCode() + "'" +
            ", forwardName='" + getForwardName() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            "}";
    }
}
