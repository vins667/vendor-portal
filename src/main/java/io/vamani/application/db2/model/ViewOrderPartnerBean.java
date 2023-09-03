package io.vamani.application.db2.model;

import io.vamani.application.db2.domain.Orderpartnerbank;
import io.vamani.application.domain.OrderpartnerUpload;

import java.io.Serializable;
import java.util.List;

public class ViewOrderPartnerBean implements Serializable {
    private String customersuppliercompanycode;
    private String customersuppliertype;
    private String customersuppliercode;
    private String legalname1;
    private String commissionerate;
    private String eccno;
    private Boolean eccnoAllow;
    private String gstinnumber;
    private String glcode;
    private String glname;
    private String gst3b;
    private String gst1;
    private String gst2a;
    private String gst2aRemark;
    private String email;
    private Boolean emailAllow;
    private String phone;
    private Boolean phoneAllow;
    private List<Orderpartnerbank> orderpartnerbanks;
    private List<OrderpartnerUpload> orderpartnerUploads;

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

    public Boolean getEccnoAllow() {
        return eccnoAllow;
    }

    public void setEccnoAllow(Boolean eccnoAllow) {
        this.eccnoAllow = eccnoAllow;
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

    public String getGst3b() {
        return gst3b;
    }

    public void setGst3b(String gst3b) {
        this.gst3b = gst3b;
    }

    public String getGst1() {
        return gst1;
    }

    public void setGst1(String gst1) {
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

    public Boolean getEmailAllow() {
        return emailAllow;
    }

    public void setEmailAllow(Boolean emailAllow) {
        this.emailAllow = emailAllow;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getPhoneAllow() {
        return phoneAllow;
    }

    public void setPhoneAllow(Boolean phoneAllow) {
        this.phoneAllow = phoneAllow;
    }

    public List<Orderpartnerbank> getOrderpartnerbanks() {
        return orderpartnerbanks;
    }

    public void setOrderpartnerbanks(List<Orderpartnerbank> orderpartnerbanks) {
        this.orderpartnerbanks = orderpartnerbanks;
    }

    public List<OrderpartnerUpload> getOrderpartnerUploads() {
        return orderpartnerUploads;
    }

    public void setOrderpartnerUploads(List<OrderpartnerUpload> orderpartnerUploads) {
        this.orderpartnerUploads = orderpartnerUploads;
    }
}
