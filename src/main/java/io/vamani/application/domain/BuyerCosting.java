package io.vamani.application.domain;
import javax.persistence.*;
import java.io.Serializable;
/**
 * A BuyerCosting.
 */
@Entity
@Table(name = "buyer_costing")
public class BuyerCosting implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="buyerCostingSeq", sequenceName="buyer_costing_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="buyerCostingSeq")
    private Long id;

    @Column(name = "order_qty")
    private Double orderQty;

    @Column(name = "smv")
    private Integer smv;

    @Column(name = "sub_total")
    private Double subTotal;

    @Column(name = "margin")
    private Double margin;

    @Column(name = "selling_price_1")
    private Double sellingPrice1;

    @Column(name = "selling_price_2")
    private Double sellingPrice2;

    @Column(name = "wastage")
    private Double wastage;

    @Column(name = "currency")
    private String currency;

    @Column(name = "conv_rate")
    private Double convRate;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getOrderQty() {
        return orderQty;
    }

    public BuyerCosting orderQty(Double orderQty) {
        this.orderQty = orderQty;
        return this;
    }

    public void setOrderQty(Double orderQty) {
        this.orderQty = orderQty;
    }

    public Integer getSmv() {
        return smv;
    }

    public BuyerCosting smv(Integer smv) {
        this.smv = smv;
        return this;
    }

    public void setSmv(Integer smv) {
        this.smv = smv;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public BuyerCosting subTotal(Double subTotal) {
        this.subTotal = subTotal;
        return this;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public Double getMargin() {
        return margin;
    }

    public BuyerCosting margin(Double margin) {
        this.margin = margin;
        return this;
    }

    public void setMargin(Double margin) {
        this.margin = margin;
    }

    public Double getSellingPrice1() {
        return sellingPrice1;
    }

    public BuyerCosting sellingPrice1(Double sellingPrice1) {
        this.sellingPrice1 = sellingPrice1;
        return this;
    }

    public void setSellingPrice1(Double sellingPrice1) {
        this.sellingPrice1 = sellingPrice1;
    }

    public Double getSellingPrice2() {
        return sellingPrice2;
    }

    public BuyerCosting sellingPrice2(Double sellingPrice2) {
        this.sellingPrice2 = sellingPrice2;
        return this;
    }

    public void setSellingPrice2(Double sellingPrice2) {
        this.sellingPrice2 = sellingPrice2;
    }

    public Double getWastage() {
        return wastage;
    }

    public BuyerCosting wastage(Double wastage) {
        this.wastage = wastage;
        return this;
    }

    public void setWastage(Double wastage) {
        this.wastage = wastage;
    }

    public String getCurrency() {
        return currency;
    }

    public BuyerCosting currency(String currency) {
        this.currency = currency;
        return this;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getConvRate() {
        return convRate;
    }

    public BuyerCosting convRate(Double convRate) {
        this.convRate = convRate;
        return this;
    }

    public void setConvRate(Double convRate) {
        this.convRate = convRate;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BuyerCosting)) {
            return false;
        }
        return id != null && id.equals(((BuyerCosting) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "BuyerCosting{" +
            "id=" + getId() +
            ", orderQty=" + getOrderQty() +
            ", smv=" + getSmv() +
            ", subTotal=" + getSubTotal() +
            ", margin=" + getMargin() +
            ", sellingPrice1=" + getSellingPrice1() +
            ", sellingPrice2=" + getSellingPrice2() +
            ", wastage=" + getWastage() +
            ", currency='" + getCurrency() + "'" +
            ", convRate=" + getConvRate() +
            "}";
    }
}
