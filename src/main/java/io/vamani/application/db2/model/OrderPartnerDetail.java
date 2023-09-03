package io.vamani.application.db2.model;

import java.io.Serializable;

public class OrderPartnerDetail implements Serializable {
    private String msmeNo;
    private String gstr1;
    private String gstr3B;

    public String getMsmeNo() {
        return msmeNo;
    }

    public void setMsmeNo(String msmeNo) {
        this.msmeNo = msmeNo;
    }

    public String getGstr1() {
        return gstr1;
    }

    public void setGstr1(String gstr1) {
        this.gstr1 = gstr1;
    }

    public String getGstr3B() {
        return gstr3B;
    }

    public void setGstr3B(String gstr3B) {
        this.gstr3B = gstr3B;
    }
}
