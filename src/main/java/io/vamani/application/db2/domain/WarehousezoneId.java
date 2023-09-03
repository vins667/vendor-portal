package io.vamani.application.db2.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class WarehousezoneId implements Serializable {
    private String physicalwarehousecompanycode;
    private String physicalwarehousecode;
    private String code;

    @Id
    @Column(name = "PHYSICALWAREHOUSECOMPANYCODE", nullable = false, length = 3)
    public String getPhysicalwarehousecompanycode() {
        return physicalwarehousecompanycode;
    }

    public void setPhysicalwarehousecompanycode(String physicalwarehousecompanycode) {
        this.physicalwarehousecompanycode = physicalwarehousecompanycode;
    }

    @Id
    @Column(name = "PHYSICALWAREHOUSECODE", nullable = false, length = 8)
    public String getPhysicalwarehousecode() {
        return physicalwarehousecode;
    }

    public void setPhysicalwarehousecode(String physicalwarehousecode) {
        this.physicalwarehousecode = physicalwarehousecode;
    }

    @Id
    @Column(name = "CODE", nullable = false, length = 3)
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
        WarehousezoneId that = (WarehousezoneId) o;
        return Objects.equals(physicalwarehousecompanycode, that.physicalwarehousecompanycode) && Objects.equals(physicalwarehousecode, that.physicalwarehousecode) && Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(physicalwarehousecompanycode, physicalwarehousecode, code);
    }
}
