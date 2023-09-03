package io.vamani.application.db2.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class PoDetailBean implements Serializable {
    private String paymentMethodcode;
    private String paymentMethodDescription;
    private BigDecimal poBasicValue;
    private BigDecimal poGstValue;

    public String getPaymentMethodcode() {
        return paymentMethodcode;
    }

    public void setPaymentMethodcode(String paymentMethodcode) {
        this.paymentMethodcode = paymentMethodcode;
    }

    public String getPaymentMethodDescription() {
        return paymentMethodDescription;
    }

    public void setPaymentMethodDescription(String paymentMethodDescription) {
        this.paymentMethodDescription = paymentMethodDescription;
    }

    public BigDecimal getPoBasicValue() {
        return poBasicValue;
    }

    public void setPoBasicValue(BigDecimal poBasicValue) {
        this.poBasicValue = poBasicValue;
    }

    public BigDecimal getPoGstValue() {
        return poGstValue;
    }

    public void setPoGstValue(BigDecimal poGstValue) {
        this.poGstValue = poGstValue;
    }
}
