package io.vamani.application.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
@Entity
@Table(name = "vendor_buyer_audit_linking_buyer_master")
public class VendorBuyerAuditLinkingBuyerMaster implements Serializable {
    @EmbeddedId
    private VendorBuyerAuditLinkingBuyerMasterId id;

    public VendorBuyerAuditLinkingBuyerMasterId getId() {
        return id;
    }

    public void setId(VendorBuyerAuditLinkingBuyerMasterId id) {
        this.id = id;
    }

    public VendorBuyerAuditLinkingBuyerMaster() {
    }

    public VendorBuyerAuditLinkingBuyerMaster(VendorBuyerAuditLinkingBuyerMasterId id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VendorBuyerAuditLinkingBuyerMaster that = (VendorBuyerAuditLinkingBuyerMaster) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "VendorBuyerAuditLinkingBuyerMaster{" +
            "id=" + id +
            '}';
    }
}
