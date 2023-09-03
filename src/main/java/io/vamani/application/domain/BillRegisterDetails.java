package io.vamani.application.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * A BillRegisterDetails.
 */
@Entity
@Table(name = "bill_register_details")
public class BillRegisterDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="billRegisterDetailsSeq", sequenceName="bill_register_details_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="billRegisterDetailsSeq")
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

    @Column(name = "billtype", length = 20)
    private String billtype;

    @Column(name = "invoicedate")
    private LocalDate invoicedate;
    @NotNull
    @Size(max = 15)
    @Column(name = "code", length = 15, nullable = false)
    private String code;
    @Size(max = 20)
    @Column(name = "style", length = 20)
    private String style;

    @Size(max = 8)
    @Column(name = "customercode", length = 8)
    private String customercode;

    @Size(max = 200)
    @Column(name = "customername", length = 200)
    private String customername;

    @Column(name = "quantity", precision = 21, scale = 2)
    private BigDecimal quantity;

    @Column(name = "price", precision = 21, scale = 2)
    private BigDecimal price;

    @Column(name = "grossvalue", precision = 21, scale = 2)
    private BigDecimal grossvalue;

    @Column(name = "submitdate")
    private LocalDate submitdate;

    @NotNull
    @Size(max = 1)
    @Column(name = "status", length = 1, nullable = false)
    private String status;

    @Column(name = "perpcsrate", precision = 21, scale = 2)
    private BigDecimal perpcsrate;

    @Column(name = "shipment_mode", length = 10)
    private String shipmentMode;

    @Column(name = "receive_date")
    private LocalDate receiveDate;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("billRegisterDetails")
    @JoinColumn(name = "bill_register_master_id")
    private BillRegisterMaster billRegisterMaster;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanycode() {
        return companycode;
    }

    public BillRegisterDetails companycode(String companycode) {
        this.companycode = companycode;
        return this;
    }

    public void setCompanycode(String companycode) {
        this.companycode = companycode;
    }

    public String getDivisioncode() {
        return divisioncode;
    }

    public BillRegisterDetails divisioncode(String divisioncode) {
        this.divisioncode = divisioncode;
        return this;
    }

    public void setDivisioncode(String divisioncode) {
        this.divisioncode = divisioncode;
    }

    public String getInvoicetypecode() {
        return invoicetypecode;
    }

    public BillRegisterDetails invoicetypecode(String invoicetypecode) {
        this.invoicetypecode = invoicetypecode;
        return this;
    }

    public void setInvoicetypecode(String invoicetypecode) {
        this.invoicetypecode = invoicetypecode;
    }

    public LocalDate getInvoicedate() {
        return invoicedate;
    }

    public BillRegisterDetails invoicedate(LocalDate invoicedate) {
        this.invoicedate = invoicedate;
        return this;
    }

    public void setInvoicedate(LocalDate invoicedate) {
        this.invoicedate = invoicedate;
    }

    public String getCode() {
        return code;
    }

    public BillRegisterDetails code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStyle() {
        return style;
    }

    public BillRegisterDetails style(String style) {
        this.style = style;
        return this;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getCustomercode() {
        return customercode;
    }

    public void setCustomercode(String customercode) {
        this.customercode = customercode;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BillRegisterDetails price(BigDecimal price) {
        this.price = price;
        return this;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getGrossvalue() {
        return grossvalue;
    }

    public BillRegisterDetails grossvalue(BigDecimal grossvalue) {
        this.grossvalue = grossvalue;
        return this;
    }

    public void setGrossvalue(BigDecimal grossvalue) {
        this.grossvalue = grossvalue;
    }

    public LocalDate getSubmitdate() {
        return submitdate;
    }

    public BillRegisterDetails submitdate(LocalDate submitdate) {
        this.submitdate = submitdate;
        return this;
    }

    public void setSubmitdate(LocalDate submitdate) {
        this.submitdate = submitdate;
    }

    public String getStatus() {
        return status;
    }

    public BillRegisterDetails status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getPerpcsrate() {
        return perpcsrate;
    }

    public BillRegisterDetails perpcsrate(BigDecimal perpcsrate) {
        this.perpcsrate = perpcsrate;
        return this;
    }

    public void setPerpcsrate(BigDecimal perpcsrate) {
        this.perpcsrate = perpcsrate;
    }

    public String getShipmentMode() {
        return shipmentMode;
    }

    public void setShipmentMode(String shipmentMode) {
        this.shipmentMode = shipmentMode;
    }

    public BillRegisterMaster getBillRegisterMaster() {
        return billRegisterMaster;
    }

    public BillRegisterDetails billRegisterMaster(BillRegisterMaster billRegisterMaster) {
        this.billRegisterMaster = billRegisterMaster;
        return this;
    }

    public void setBillRegisterMaster(BillRegisterMaster billRegisterMaster) {
        this.billRegisterMaster = billRegisterMaster;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public LocalDate getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(LocalDate receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getBilltype() {
        return billtype;
    }

    public void setBilltype(String billtype) {
        this.billtype = billtype;
    }
// jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BillRegisterDetails)) {
            return false;
        }
        return id != null && id.equals(((BillRegisterDetails) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "BillRegisterDetails{" +
            "id=" + getId() +
            ", companycode='" + getCompanycode() + "'" +
            ", divisioncode='" + getDivisioncode() + "'" +
            ", invoicetypecode='" + getInvoicetypecode() + "'" +
            ", invoicedate='" + getInvoicedate() + "'" +
            ", code='" + getCode() + "'" +
            ", style='" + getStyle() + "'" +
            ", price=" + getPrice() +
            ", grossvalue=" + getGrossvalue() +
            ", submitdate='" + getSubmitdate() + "'" +
            ", status='" + getStatus() + "'" +
            ", perpcsrate=" + getPerpcsrate() +
            "}";
    }
}
