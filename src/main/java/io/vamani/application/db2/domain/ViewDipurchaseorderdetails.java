package io.vamani.application.db2.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "VIEW_DIPURCHASEORDERDETAILS")
public class ViewDipurchaseorderdetails {
    @EmbeddedId
    private ViewDipurchaseorderdetailsId id;

    @Basic
    @Column(name = "PAYMENTMETHODCODE", nullable = true, length = 3)
    private String paymentmethodcode;
    @Basic
    @Column(name = "PAYMENTMETHODDESCRIPTION", nullable = false, length = 200)
    private String paymentmethoddescription;
    @Basic
    @Column(name = "POBASICVALUE", nullable = true, precision = 5)
    private BigDecimal pobasicvalue;
    @Basic
    @Column(name = "POGSTVALUE", nullable = false, precision = 5)
    private BigDecimal pogstvalue;

    public ViewDipurchaseorderdetailsId getId() {
        return id;
    }

    public void setId(ViewDipurchaseorderdetailsId id) {
        this.id = id;
    }

    public String getPaymentmethodcode() {
        return paymentmethodcode;
    }

    public void setPaymentmethodcode(String paymentmethodcode) {
        this.paymentmethodcode = paymentmethodcode;
    }

    public String getPaymentmethoddescription() {
        return paymentmethoddescription;
    }

    public void setPaymentmethoddescription(String paymentmethoddescription) {
        this.paymentmethoddescription = paymentmethoddescription;
    }

    public BigDecimal getPobasicvalue() {
        return pobasicvalue;
    }

    public void setPobasicvalue(BigDecimal pobasicvalue) {
        this.pobasicvalue = pobasicvalue;
    }

    public BigDecimal getPogstvalue() {
        return pogstvalue;
    }

    public void setPogstvalue(BigDecimal pogstvalue) {
        this.pogstvalue = pogstvalue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ViewDipurchaseorderdetails that = (ViewDipurchaseorderdetails) o;
        return Objects.equals(id, that.id) && Objects.equals(paymentmethodcode, that.paymentmethodcode) && Objects.equals(paymentmethoddescription, that.paymentmethoddescription) && Objects.equals(pobasicvalue, that.pobasicvalue) && Objects.equals(pogstvalue, that.pogstvalue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, paymentmethodcode, paymentmethoddescription, pobasicvalue, pogstvalue);
    }
}
