package io.vamani.application.domain;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A BillRegister.
 */
@Entity
@Table(name = "bill_register")
public class BillRegister implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @SequenceGenerator(name="billRegisterSeq", sequenceName="bill_register_seq", allocationSize=1)
  @GeneratedValue(strategy=GenerationType.AUTO, generator="billRegisterSeq")
  @Column(name = "id")
  private Long id;

  @NotNull
  @Size(max = 3)
  @Column(name = "companycode", length = 3, nullable = false)
  private String companycode;

  @NotNull
  @Size(max = 3)
  @Column(name = "divisioncode", length = 3, nullable = false)
  private String divisioncode;

  @NotNull
  @Size(max = 3)
  @Column(name = "invoicetypecode", length = 3, nullable = false)
  private String invoicetypecode;

  @NotNull
  @Size(max = 15)
  @Column(name = "code", length = 15, nullable = false, unique = true)
  private String code;

  @NotNull
  @Column(name = "invoicedate", nullable = false)
  private LocalDate invoicedate;

  @NotNull
  @Size(max = 20)
  @Column(name = "style", length = 20, nullable = false)
  private String style;

  @NotNull
  @Size(max = 8)
  @Column(name = "customercode", length = 8, nullable = false)
  private String customercode;

  @NotNull
  @Size(max = 200)
  @Column(name = "customername", length = 200, nullable = false)
  private String customername;

  @Size(max = 50)
  @Column(name = "createdby", length = 50)
  private String createdby;

  @Column(name = "createddate")
  private Instant createddate;

  @Size(max = 50)
  @Column(name = "updatedby", length = 50)
  private String updatedby;

  @Column(name = "updateddate")
  private Instant updateddate;

  // jhipster-needle-entity-add-field - JHipster will add fields here

  public Long getId() {
    return this.id;
  }

  public BillRegister id(Long id) {
    this.setId(id);
    return this;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCompanycode() {
    return this.companycode;
  }

  public BillRegister companycode(String companycode) {
    this.setCompanycode(companycode);
    return this;
  }

  public void setCompanycode(String companycode) {
    this.companycode = companycode;
  }

  public String getDivisioncode() {
    return this.divisioncode;
  }

  public BillRegister divisioncode(String divisioncode) {
    this.setDivisioncode(divisioncode);
    return this;
  }

  public void setDivisioncode(String divisioncode) {
    this.divisioncode = divisioncode;
  }

  public String getInvoicetypecode() {
    return this.invoicetypecode;
  }

  public BillRegister invoicetypecode(String invoicetypecode) {
    this.setInvoicetypecode(invoicetypecode);
    return this;
  }

  public void setInvoicetypecode(String invoicetypecode) {
    this.invoicetypecode = invoicetypecode;
  }

  public String getCode() {
    return this.code;
  }

  public BillRegister code(String code) {
    this.setCode(code);
    return this;
  }

  public void setCode(String code) {
    this.code = code != null ? code.trim() : code;
  }

  public LocalDate getInvoicedate() {
    return this.invoicedate;
  }

  public BillRegister invoicedate(LocalDate invoicedate) {
    this.setInvoicedate(invoicedate);
    return this;
  }

  public void setInvoicedate(LocalDate invoicedate) {
    this.invoicedate = invoicedate;
  }

  public String getStyle() {
    return this.style;
  }

  public BillRegister style(String style) {
    this.setStyle(style);
    return this;
  }

  public void setStyle(String style) {
    this.style = style;
  }

  public String getCustomercode() {
    return this.customercode;
  }

  public BillRegister customercode(String customercode) {
    this.setCustomercode(customercode);
    return this;
  }

  public void setCustomercode(String customercode) {
    this.customercode = customercode;
  }

  public String getCustomername() {
    return this.customername;
  }

  public BillRegister customername(String customername) {
    this.setCustomername(customername);
    return this;
  }

  public void setCustomername(String customername) {
    this.customername = customername;
  }

  public String getCreatedby() {
    return this.createdby;
  }

  public BillRegister createdby(String createdby) {
    this.setCreatedby(createdby);
    return this;
  }

  public void setCreatedby(String createdby) {
    this.createdby = createdby;
  }

  public Instant getCreateddate() {
    return this.createddate;
  }

  public BillRegister createddate(Instant createddate) {
    this.setCreateddate(createddate);
    return this;
  }

  public void setCreateddate(Instant createddate) {
    this.createddate = createddate;
  }

  public String getUpdatedby() {
    return this.updatedby;
  }

  public BillRegister updatedby(String updatedby) {
    this.setUpdatedby(updatedby);
    return this;
  }

  public void setUpdatedby(String updatedby) {
    this.updatedby = updatedby;
  }

  public Instant getUpdateddate() {
    return this.updateddate;
  }

  public BillRegister updateddate(Instant updateddate) {
    this.setUpdateddate(updateddate);
    return this;
  }

  public void setUpdateddate(Instant updateddate) {
    this.updateddate = updateddate;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof BillRegister)) {
      return false;
    }
    return id != null && id.equals(((BillRegister) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "BillRegister{" +
            "id=" + getId() +
            ", companycode='" + getCompanycode() + "'" +
            ", divisioncode='" + getDivisioncode() + "'" +
            ", invoicetypecode='" + getInvoicetypecode() + "'" +
            ", code='" + getCode() + "'" +
            ", invoicedate='" + getInvoicedate() + "'" +
            ", style='" + getStyle() + "'" +
            ", customercode='" + getCustomercode() + "'" +
            ", customername='" + getCustomername() + "'" +
            ", createdby='" + getCreatedby() + "'" +
            ", createddate='" + getCreateddate() + "'" +
            ", updatedby='" + getUpdatedby() + "'" +
            ", updateddate='" + getUpdateddate() + "'" +
            "}";
    }
}
