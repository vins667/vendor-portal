package io.vamani.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DeliveryChallanDetails.
 */
@Entity
@Table(name = "delivery_challan_details")
public class DeliveryChallanDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="deliveryChallanDetailsSeq", sequenceName="delivery_challan_details_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="deliveryChallanDetailsSeq")
    private Long id;

    @Size(max = 200)
    @Column(name = "product_name", length = 200)
    private String productName;

    @Size(max = 20)
    @Column(name = "triff_code", length = 20)
    private String triffCode;

    @Column(name = "quantity")
    private Double quantity;

    @Column(name = "taxper")
    private Double taxper;

    @Column(name = "rate")
    private Double rate;

    @Column(name = "taxval")
    private Double taxval;

    @Column(name = "amount")
    private Double amount;
    
    @Column(name = "total_amt")
    private Double totalAmt;

    @Size(max = 50)
    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Column(name = "created_date")
    private Instant createdDate;

    @Size(max = 50)
    @Column(name = "last_updated_by", length = 50)
    private String lastUpdatedBy;

    @Column(name = "last_updated_date")
    private Instant lastUpdatedDate;

    @ManyToOne
    @JoinColumn(name = "delivery_challan_id")
    @JsonIgnoreProperties("")
    private DeliveryChallan deliveryChallan;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public DeliveryChallanDetails productName(String productName) {
        this.productName = productName;
        return this;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getTriffCode() {
        return triffCode;
    }

    public DeliveryChallanDetails triffCode(String triffCode) {
        this.triffCode = triffCode;
        return this;
    }

    public void setTriffCode(String triffCode) {
        this.triffCode = triffCode;
    }

    public Double getQuantity() {
        return quantity;
    }

    public DeliveryChallanDetails quantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getTaxper() {
        return taxper;
    }

    public DeliveryChallanDetails taxper(Double taxper) {
        this.taxper = taxper;
        return this;
    }

    public void setTaxper(Double taxper) {
        this.taxper = taxper;
    }

    public Double getRate() {
        return rate;
    }

    public DeliveryChallanDetails rate(Double rate) {
        this.rate = rate;
        return this;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Double getTaxval() {
        return taxval;
    }

    public DeliveryChallanDetails taxval(Double taxval) {
        this.taxval = taxval;
        return this;
    }

    public void setTaxval(Double taxval) {
        this.taxval = taxval;
    }
    
    public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getTotalAmt() {
        return totalAmt;
    }

    public DeliveryChallanDetails totalAmt(Double totalAmt) {
        this.totalAmt = totalAmt;
        return this;
    }

    public void setTotalAmt(Double totalAmt) {
        this.totalAmt = totalAmt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public DeliveryChallanDetails createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public DeliveryChallanDetails createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public DeliveryChallanDetails lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public DeliveryChallanDetails lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public DeliveryChallan getDeliveryChallan() {
        return deliveryChallan;
    }

    public DeliveryChallanDetails deliveryChallan(DeliveryChallan deliveryChallan) {
        this.deliveryChallan = deliveryChallan;
        return this;
    }

    public void setDeliveryChallan(DeliveryChallan deliveryChallan) {
        this.deliveryChallan = deliveryChallan;
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
        DeliveryChallanDetails deliveryChallanDetails = (DeliveryChallanDetails) o;
        if (deliveryChallanDetails.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), deliveryChallanDetails.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DeliveryChallanDetails{" +
            "id=" + getId() +
            ", productName='" + getProductName() + "'" +
            ", triffCode='" + getTriffCode() + "'" +
            ", quantity=" + getQuantity() +
            ", taxper=" + getTaxper() +
            ", rate=" + getRate() +
            ", taxval=" + getTaxval() +
            ", totalAmt=" + getTotalAmt() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            "}";
    }
}
