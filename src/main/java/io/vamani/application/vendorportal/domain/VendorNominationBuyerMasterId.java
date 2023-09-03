package io.vamani.application.vendorportal.domain;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Objects;

public class VendorNominationBuyerMasterId implements Serializable {
    @Column(name = "buyer_master_id", nullable = false)
    private Long buyerMasterId;

    @Column(name = "vendor_nomination_id", nullable = false)
    private Long vendorNominationId;

    public Long getBuyerMasterId() {
        return buyerMasterId;
    }

    public void setBuyerMasterId(Long buyerMasterId) {
        this.buyerMasterId = buyerMasterId;
    }

    public Long getVendorNominationId() {
        return vendorNominationId;
    }

    public void setVendorNominationId(Long vendorNominationId) {
        this.vendorNominationId = vendorNominationId;
    }

    public VendorNominationBuyerMasterId() {
    }

    public VendorNominationBuyerMasterId(Long buyerMasterId, Long vendorNominationId) {
        this.buyerMasterId = buyerMasterId;
        this.vendorNominationId = vendorNominationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VendorNominationBuyerMasterId that = (VendorNominationBuyerMasterId) o;
        return Objects.equals(buyerMasterId, that.buyerMasterId) &&
            Objects.equals(vendorNominationId, that.vendorNominationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(buyerMasterId, vendorNominationId);
    }

    @Override
    public String toString() {
        return "VendorNominationBuyerMasterId{" +
            "buyerMasterId=" + buyerMasterId +
            ", vendorNominationId=" + vendorNominationId +
            '}';
    }
}
