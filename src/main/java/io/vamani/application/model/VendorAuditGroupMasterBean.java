package io.vamani.application.model;

import io.vamani.application.domain.VendorAuditQuesDetails;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class VendorAuditGroupMasterBean implements Serializable {
    private Long id;
    private String groupName;
    private List<Master> initColumns;
    private List<VendorAuditQuesDetailsBean> vendorAuditQuesDetails;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<VendorAuditQuesDetailsBean> getVendorAuditQuesDetails() {
        return vendorAuditQuesDetails;
    }

    public void setVendorAuditQuesDetails(List<VendorAuditQuesDetailsBean> vendorAuditQuesDetails) {
        this.vendorAuditQuesDetails = vendorAuditQuesDetails;
    }

    public List<Master> getInitColumns() {
        return initColumns;
    }

    public void setInitColumns(List<Master> initColumns) {
        this.initColumns = initColumns;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VendorAuditGroupMasterBean that = (VendorAuditGroupMasterBean) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(groupName, that.groupName) &&
            Objects.equals(vendorAuditQuesDetails, that.vendorAuditQuesDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, groupName, vendorAuditQuesDetails);
    }

    @Override
    public String toString() {
        return "VendorAuditGroupMasterBean{" +
            "id=" + id +
            ", groupName='" + groupName + '\'' +
            ", vendorAuditQuesDetails=" + vendorAuditQuesDetails +
            '}';
    }
}
