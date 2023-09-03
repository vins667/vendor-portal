package io.vamani.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A BillRegisterDetails.
 */
@Entity
@Table(name = "bill_register_import_details")
public class BillRegisterImportDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="billRegisterImportDetailsSeq", sequenceName="bill_register_import_details_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="billRegisterImportDetailsSeq")
    private Long id;

    @Size(max = 50)
    @Column(name = "code", length = 50, nullable = false)
    private String code;

    @Column(name = "orderdate")
    private LocalDate orderdate;
    @Size(max = 50)
    @Column(name = "projectcode", length = 50, nullable = false)
    private String projectcode;

    @Size(max = 200)
    @Column(name = "summarizeddescription", length = 200)
    private String summarizeddescription;

    @Size(max = 3)
    @Column(name = "userprimaryuomcode", length = 3)
    private String userprimaryuomcode;

    @Column(name = "quantity", precision = 21, scale = 2)
    private BigDecimal quantity;

    @Column(name = "price", precision = 21, scale = 4)
    private BigDecimal price;

    @Column(name = "grossvalue", precision = 21, scale = 2)
    private BigDecimal grossvalue;

    @Column(name = "submitdate")
    private LocalDate submitdate;

    @Column(name = "receive_date")
    private LocalDate receiveDate;

    @Column(name = "shipment_mode", length = 10)
    private String shipmentMode;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("billRegisterDetails")
    @JoinColumn(name = "bill_register_import_id")
    private BillRegisterImport billRegisterImport;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDate getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(LocalDate orderdate) {
        this.orderdate = orderdate;
    }

    public String getProjectcode() {
        return projectcode;
    }

    public void setProjectcode(String projectcode) {
        this.projectcode = projectcode != null ? projectcode.toUpperCase() : "";
    }

    public String getSummarizeddescription() {
        return summarizeddescription;
    }

    public void setSummarizeddescription(String summarizeddescription) {
        this.summarizeddescription = summarizeddescription != null ? summarizeddescription.toUpperCase() : summarizeddescription;
    }

    public String getUserprimaryuomcode() {
        return userprimaryuomcode;
    }

    public void setUserprimaryuomcode(String userprimaryuomcode) {
        this.userprimaryuomcode = userprimaryuomcode;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getGrossvalue() {
        return grossvalue;
    }

    public void setGrossvalue(BigDecimal grossvalue) {
        this.grossvalue = grossvalue;
    }

    public LocalDate getSubmitdate() {
        return submitdate;
    }

    public void setSubmitdate(LocalDate submitdate) {
        this.submitdate = submitdate;
    }

    public LocalDate getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(LocalDate receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getShipmentMode() {
        return shipmentMode;
    }

    public void setShipmentMode(String shipmentMode) {
        this.shipmentMode = shipmentMode;
    }

    public BillRegisterImport getBillRegisterImport() {
        return billRegisterImport;
    }

    public void setBillRegisterImport(BillRegisterImport billRegisterImport) {
        this.billRegisterImport = billRegisterImport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BillRegisterImportDetails that = (BillRegisterImportDetails) o;
        return Objects.equals(id, that.id) && Objects.equals(code, that.code) && Objects.equals(orderdate, that.orderdate) && Objects.equals(projectcode, that.projectcode) && Objects.equals(summarizeddescription, that.summarizeddescription) && Objects.equals(userprimaryuomcode, that.userprimaryuomcode) && Objects.equals(quantity, that.quantity) && Objects.equals(price, that.price) && Objects.equals(grossvalue, that.grossvalue) && Objects.equals(submitdate, that.submitdate) && Objects.equals(receiveDate, that.receiveDate) && Objects.equals(shipmentMode, that.shipmentMode) && Objects.equals(billRegisterImport, that.billRegisterImport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, orderdate, projectcode, summarizeddescription, userprimaryuomcode, quantity, price, grossvalue, submitdate, receiveDate, shipmentMode, billRegisterImport);
    }
}
