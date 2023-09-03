package io.vamani.application.db2.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class WarehouselocationId implements Serializable {
    private String whszonephywhscompanycode;
    private String whszonephysicalwarehousecode;
    private String warehousezonecode;
    private String code;

    @Column(name = "WHSZONEPHYWHSCOMPANYCODE", nullable = false, length = 3)
    @Id
    public String getWhszonephywhscompanycode() {
        return whszonephywhscompanycode;
    }

    public void setWhszonephywhscompanycode(String whszonephywhscompanycode) {
        this.whszonephywhscompanycode = whszonephywhscompanycode;
    }

    @Column(name = "WHSZONEPHYSICALWAREHOUSECODE", nullable = false, length = 8)
    @Id
    public String getWhszonephysicalwarehousecode() {
        return whszonephysicalwarehousecode;
    }

    public void setWhszonephysicalwarehousecode(String whszonephysicalwarehousecode) {
        this.whszonephysicalwarehousecode = whszonephysicalwarehousecode;
    }

    @Column(name = "WAREHOUSEZONECODE", nullable = false, length = 3)
    @Id
    public String getWarehousezonecode() {
        return warehousezonecode;
    }

    public void setWarehousezonecode(String warehousezonecode) {
        this.warehousezonecode = warehousezonecode;
    }

    @Column(name = "CODE", nullable = false, length = 10)
    @Id
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WarehouselocationId that = (WarehouselocationId) o;
        return Objects.equals(whszonephywhscompanycode, that.whszonephywhscompanycode) && Objects.equals(whszonephysicalwarehousecode, that.whszonephysicalwarehousecode) && Objects.equals(warehousezonecode, that.warehousezonecode) && Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(whszonephywhscompanycode, whszonephysicalwarehousecode, warehousezonecode, code);
    }

    @Override
    public String toString() {
        return "WarehouselocationId{" +
            "whszonephywhscompanycode='" + whszonephywhscompanycode + '\'' +
            ", whszonephysicalwarehousecode='" + whszonephysicalwarehousecode + '\'' +
            ", warehousezonecode='" + warehousezonecode + '\'' +
            ", code='" + code + '\'' +
            '}';
    }
}
