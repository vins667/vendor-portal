package io.vamani.application.model;

import java.io.Serializable;
import java.util.Date;

public class EventSearch implements Serializable {
    private Date date;
    private String cardNo;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }
}
