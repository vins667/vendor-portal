package io.vamani.application.vendorportal.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "vendor_nomination_buyer_master")
public class VendorNominationBuyerMaster {
    @EmbeddedId
    private VendorNominationBuyerMasterId id;

    public VendorNominationBuyerMasterId getId() {
        return id;
    }

    public void setId(VendorNominationBuyerMasterId id) {
        this.id = id;
    }

    public VendorNominationBuyerMaster() {
    }

    public VendorNominationBuyerMaster(VendorNominationBuyerMasterId id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VendorNominationBuyerMaster that = (VendorNominationBuyerMaster) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "VendorNominationBuyerMaster{" +
            "id=" + id +
            '}';
    }
}
