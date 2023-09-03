package io.vamani.application.domain;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Objects;

public class VendorBuyerAuditLinkingBuyerMasterId implements Serializable {
    @Column(name = "buyer_masters_code")
    private String buyerMastersCode;

    @Column(name = "vendor_buyer_audit_linkings_id")
    private Long vendorBuyerAuditLinkingsId;

    public VendorBuyerAuditLinkingBuyerMasterId() {
    }

    public VendorBuyerAuditLinkingBuyerMasterId(String buyerMastersCode, Long vendorBuyerAuditLinkingsId) {
        this.buyerMastersCode = buyerMastersCode;
        this.vendorBuyerAuditLinkingsId = vendorBuyerAuditLinkingsId;
    }

    public String getBuyerMastersCode() {
        return buyerMastersCode;
    }

    public void setBuyerMastersCode(String buyerMastersCode) {
        this.buyerMastersCode = buyerMastersCode;
    }

    public Long getVendorBuyerAuditLinkingsId() {
        return vendorBuyerAuditLinkingsId;
    }

    public void setVendorBuyerAuditLinkingsId(Long vendorBuyerAuditLinkingsId) {
        this.vendorBuyerAuditLinkingsId = vendorBuyerAuditLinkingsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VendorBuyerAuditLinkingBuyerMasterId that = (VendorBuyerAuditLinkingBuyerMasterId) o;
        return Objects.equals(buyerMastersCode, that.buyerMastersCode) &&
            Objects.equals(vendorBuyerAuditLinkingsId, that.vendorBuyerAuditLinkingsId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(buyerMastersCode, vendorBuyerAuditLinkingsId);
    }

    @Override
    public String toString() {
        return "VendorBuyerAuditLinkingBuyerMasterId{" +
            "buyerMastersCode='" + buyerMastersCode + '\'' +
            ", vendorBuyerAuditLinkingsId=" + vendorBuyerAuditLinkingsId +
            '}';
    }
}
