package io.vamani.application.domain;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Objects;

public class EscapeEmployeeId implements Serializable {
    @Column(name = "type")
    private String type;

    @Column(name = "card_no")
    private String cardNo;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EscapeEmployeeId that = (EscapeEmployeeId) o;
        return Objects.equals(type, that.type) &&
            Objects.equals(cardNo, that.cardNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, cardNo);
    }

    @Override
    public String toString() {
        return "EscapeEmployeeId{" +
            "type='" + type + '\'' +
            ", cardNo='" + cardNo + '\'' +
            '}';
    }
}
