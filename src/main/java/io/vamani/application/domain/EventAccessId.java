package io.vamani.application.domain;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Objects;

public class EventAccessId implements Serializable {
    @Column(name = "card_no")
    private String cardNo;

    @Column(name = "access_card_no")
    private String accessCardNo;

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getAccessCardNo() {
        return accessCardNo;
    }

    public void setAccessCardNo(String accessCardNo) {
        this.accessCardNo = accessCardNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventAccessId that = (EventAccessId) o;
        return Objects.equals(cardNo, that.cardNo) &&
            Objects.equals(accessCardNo, that.accessCardNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNo, accessCardNo);
    }

    @Override
    public String toString() {
        return "EventAccessId{" +
            "cardNo='" + cardNo + '\'' +
            ", accessCardNo='" + accessCardNo + '\'' +
            '}';
    }
}
