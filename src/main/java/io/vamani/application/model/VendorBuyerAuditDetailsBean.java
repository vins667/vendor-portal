package io.vamani.application.model;

import io.vamani.application.domain.VendorMaster;
import io.vamani.application.vendorportal.domain.BuyerMaster;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class VendorBuyerAuditDetailsBean implements Serializable {
    private VendorMaster vendorMaster;
    private BuyerMaster buyerMaster;
    private List<Master> masters;

    public VendorMaster getVendorMaster() {
        return vendorMaster;
    }

    public void setVendorMaster(VendorMaster vendorMaster) {
        this.vendorMaster = vendorMaster;
    }

    public BuyerMaster getBuyerMaster() {
        return buyerMaster;
    }

    public void setBuyerMaster(BuyerMaster buyerMaster) {
        this.buyerMaster = buyerMaster;
    }

    public List<Master> getMasters() {
        return masters;
    }

    public void setMasters(List<Master> masters) {
        this.masters = masters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VendorBuyerAuditDetailsBean that = (VendorBuyerAuditDetailsBean) o;
        return Objects.equals(vendorMaster, that.vendorMaster) &&
            Objects.equals(buyerMaster, that.buyerMaster) &&
            Objects.equals(masters, that.masters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vendorMaster, buyerMaster, masters);
    }

    @Override
    public String toString() {
        return "VendorBuyerAuditDetailsBean{" +
            "vendorMaster=" + vendorMaster +
            ", buyerMaster=" + buyerMaster +
            ", masters=" + masters +
            '}';
    }
}
