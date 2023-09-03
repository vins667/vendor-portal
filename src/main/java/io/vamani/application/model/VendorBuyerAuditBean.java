package io.vamani.application.model;

import io.vamani.application.domain.VendorMaster;
import io.vamani.application.vendorportal.domain.BuyerMaster;

import java.io.Serializable;
import java.util.List;

public class VendorBuyerAuditBean implements Serializable {
    private Long id;
    private String createdBy;
    private String createdDate;
    private VendorMaster vendorMaster;
    private List<BuyerMaster> buyerMasters;

    public VendorMaster getVendorMaster() {
        return vendorMaster;
    }

    public void setVendorMaster(VendorMaster vendorMaster) {
        this.vendorMaster = vendorMaster;
    }

    public List<BuyerMaster> getBuyerMasters() {
        return buyerMasters;
    }

    public void setBuyerMasters(List<BuyerMaster> buyerMasters) {
        this.buyerMasters = buyerMasters;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
}
