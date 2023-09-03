package io.vamani.application.mssql.model;

import java.io.Serializable;

public class DayStatusBean implements Serializable {
    private String inTm;
    private String outTm;

    public String getInTm() {
        return inTm;
    }

    public void setInTm(String inTm) {
        this.inTm = inTm;
    }

    public String getOutTm() {
        return outTm;
    }

    public void setOutTm(String outTm) {
        this.outTm = outTm;
    }
}
