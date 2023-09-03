package io.vamani.application.db2.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Currencydailyexchangerate implements Serializable {
    private BigDecimal purchaseexchangerate;

    public BigDecimal getPurchaseexchangerate() {
        return purchaseexchangerate;
    }

    public void setPurchaseexchangerate(BigDecimal purchaseexchangerate) {
        this.purchaseexchangerate = purchaseexchangerate;
    }
}
