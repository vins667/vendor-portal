package io.vamani.application.db2.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class TdsDetailBean implements Serializable {
    private String tdsCode;
    private String tdsiTexCode;
    private BigDecimal value;

    public String getTdsCode() {
        return tdsCode;
    }

    public void setTdsCode(String tdsCode) {
        this.tdsCode = tdsCode;
    }

    public String getTdsiTexCode() {
        return tdsiTexCode;
    }

    public void setTdsiTexCode(String tdsiTexCode) {
        this.tdsiTexCode = tdsiTexCode;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
