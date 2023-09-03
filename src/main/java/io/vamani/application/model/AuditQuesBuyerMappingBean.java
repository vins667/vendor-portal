package io.vamani.application.model;

import io.vamani.application.domain.VendorAuditQuesDetails;
import io.vamani.application.domain.VendorAuditQuesMasterWD;
import io.vamani.application.vendorportal.domain.BuyerMaster;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class AuditQuesBuyerMappingBean implements Serializable {
    private Long id;
    private VendorAuditQuesMasterWD vendorAuditQuesMaster;
    private List<BuyerMasterBean> buyerMasters;
    private List<BuyerMap> buyerMaps;
    private List<VendorAuditGroupMasterBean> vendorAuditGroupMasterBeans;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VendorAuditQuesMasterWD getVendorAuditQuesMaster() {
        return vendorAuditQuesMaster;
    }

    public void setVendorAuditQuesMaster(VendorAuditQuesMasterWD vendorAuditQuesMaster) {
        this.vendorAuditQuesMaster = vendorAuditQuesMaster;
    }

    public List<BuyerMasterBean> getBuyerMasters() {
        return buyerMasters;
    }

    public void setBuyerMasters(List<BuyerMasterBean> buyerMasters) {
        this.buyerMasters = buyerMasters;
    }

    public List<BuyerMap> getBuyerMaps() {
        return buyerMaps;
    }

    public void setBuyerMaps(List<BuyerMap> buyerMaps) {
        this.buyerMaps = buyerMaps;
    }

    public List<VendorAuditGroupMasterBean> getVendorAuditGroupMasterBeans() {
        return vendorAuditGroupMasterBeans;
    }

    public void setVendorAuditGroupMasterBeans(List<VendorAuditGroupMasterBean> vendorAuditGroupMasterBeans) {
        this.vendorAuditGroupMasterBeans = vendorAuditGroupMasterBeans;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuditQuesBuyerMappingBean that = (AuditQuesBuyerMappingBean) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(vendorAuditQuesMaster, that.vendorAuditQuesMaster) &&
            Objects.equals(buyerMasters, that.buyerMasters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, vendorAuditQuesMaster, buyerMasters);
    }

    @Override
    public String toString() {
        return "AuditQuesBuyerMappingBean{" +
            "id=" + id +
            ", vendorAuditQuesMaster=" + vendorAuditQuesMaster +
            ", buyerMasters=" + buyerMasters +
            '}';
    }
}
