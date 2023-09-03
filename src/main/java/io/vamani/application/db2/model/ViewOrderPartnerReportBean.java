package io.vamani.application.db2.model;

import java.io.Serializable;
import java.util.Date;

public class ViewOrderPartnerReportBean implements Serializable {
    private String customersuppliercompanycode;
    private String customersuppliertype;
    private String customersuppliercode;
    private String legalname1;
    private String commissionerate;
    private String eccno;
    private String gstinnumber;
    private String glcode;
    private String glname;
    private Date gst3b;
    private Date gst1;
    private String gst2a;
    private String gst2aRemark;
    private String email;
    private String phone;

    public String getCustomersuppliercompanycode() {
        return customersuppliercompanycode;
    }

    public void setCustomersuppliercompanycode(String customersuppliercompanycode) {
        this.customersuppliercompanycode = customersuppliercompanycode;
    }

    public String getCustomersuppliertype() {
        return customersuppliertype;
    }

    public void setCustomersuppliertype(String customersuppliertype) {
        this.customersuppliertype = customersuppliertype;
    }

    public String getCustomersuppliercode() {
        return customersuppliercode;
    }

    public void setCustomersuppliercode(String customersuppliercode) {
        this.customersuppliercode = customersuppliercode;
    }

    public String getLegalname1() {
        return legalname1;
    }

    public void setLegalname1(String legalname1) {
        this.legalname1 = legalname1;
    }

    public String getCommissionerate() {
        return commissionerate;
    }

    public void setCommissionerate(String commissionerate) {
        this.commissionerate = commissionerate;
    }

    public String getEccno() {
        return eccno;
    }

    public void setEccno(String eccno) {
        this.eccno = eccno;
    }

    public String getGstinnumber() {
        return gstinnumber;
    }

    public void setGstinnumber(String gstinnumber) {
        this.gstinnumber = gstinnumber;
    }

    public String getGlcode() {
        return glcode;
    }

    public void setGlcode(String glcode) {
        this.glcode = glcode;
    }

    public String getGlname() {
        return glname;
    }

    public void setGlname(String glname) {
        this.glname = glname;
    }

    public Date getGst3b() {
        return gst3b;
    }

    public void setGst3b(Date gst3b) {
        this.gst3b = gst3b;
    }

    public Date getGst1() {
        return gst1;
    }

    public void setGst1(Date gst1) {
        this.gst1 = gst1;
    }

    public String getGst2a() {
        return gst2a;
    }

    public void setGst2a(String gst2a) {
        this.gst2a = gst2a;
    }

    public String getGst2aRemark() {
        return gst2aRemark;
    }

    public void setGst2aRemark(String gst2aRemark) {
        this.gst2aRemark = gst2aRemark;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
