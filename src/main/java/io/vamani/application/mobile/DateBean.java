package io.vamani.application.mobile;

import java.io.Serializable;
import java.util.Date;

public class DateBean implements Serializable {
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
