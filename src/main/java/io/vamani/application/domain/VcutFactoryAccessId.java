package io.vamani.application.domain;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Objects;

public class VcutFactoryAccessId implements Serializable {
    @Column(name = "card_no")
    private String cardNo;

    @Column(name = "factory_code")
    private String factoryCode;

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getFactoryCode() {
        return factoryCode;
    }

    public void setFactoryCode(String factoryCode) {
        this.factoryCode = factoryCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VcutFactoryAccessId that = (VcutFactoryAccessId) o;
        return Objects.equals(cardNo, that.cardNo) &&
            Objects.equals(factoryCode, that.factoryCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNo, factoryCode);
    }

    @Override
    public String toString() {
        return "VcutFactoryAccessId{" +
            "cardNo='" + cardNo + '\'' +
            ", factoryCode='" + factoryCode + '\'' +
            '}';
    }
}
