package io.vamani.application.model;

import io.vamani.application.domain.VendorAuditQuesMaster;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class AuctionQuestionBuyerMappingBean implements Serializable {
    private VendorAuditQuesMaster vendorAuditQuesMaster;
    private List<VendorAuditGroupMasterBean> vendorAuditGroupMasterBean;

    public VendorAuditQuesMaster getVendorAuditQuesMaster() {
        return vendorAuditQuesMaster;
    }

    public void setVendorAuditQuesMaster(VendorAuditQuesMaster vendorAuditQuesMaster) {
        this.vendorAuditQuesMaster = vendorAuditQuesMaster;
    }

    public List<VendorAuditGroupMasterBean> getVendorAuditGroupMasterBean() {
        return vendorAuditGroupMasterBean;
    }

    public void setVendorAuditGroupMasterBean(List<VendorAuditGroupMasterBean> vendorAuditGroupMasterBean) {
        this.vendorAuditGroupMasterBean = vendorAuditGroupMasterBean;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuctionQuestionBuyerMappingBean that = (AuctionQuestionBuyerMappingBean) o;
        return Objects.equals(vendorAuditQuesMaster, that.vendorAuditQuesMaster) &&
            Objects.equals(vendorAuditGroupMasterBean, that.vendorAuditGroupMasterBean);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vendorAuditQuesMaster, vendorAuditGroupMasterBean);
    }

    @Override
    public String toString() {
        return "AuctionQuestionBuyerMappingBean{" +
            "vendorAuditQuesMaster=" + vendorAuditQuesMaster +
            ", vendorAuditGroupMasterBean=" + vendorAuditGroupMasterBean +
            '}';
    }
}
