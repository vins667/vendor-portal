package io.vamani.application.db2.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class SizesId implements Serializable {
    private String sizestypecompanycode;
    private String sizestypecode;
    private String code;

    @Column(name = "SIZESTYPECOMPANYCODE", nullable = false, length = 3)
    @Id
    public String getSizestypecompanycode() {
        return sizestypecompanycode;
    }

    public void setSizestypecompanycode(String sizestypecompanycode) {
        this.sizestypecompanycode = sizestypecompanycode;
    }

    @Column(name = "SIZESTYPECODE", nullable = false, length = 3)
    @Id
    public String getSizestypecode() {
        return sizestypecode;
    }

    public void setSizestypecode(String sizestypecode) {
        this.sizestypecode = sizestypecode;
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
        SizesId sizesId = (SizesId) o;
        return Objects.equals(sizestypecompanycode, sizesId.sizestypecompanycode) &&
            Objects.equals(sizestypecode, sizesId.sizestypecode) &&
            Objects.equals(code, sizesId.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sizestypecompanycode, sizestypecode, code);
    }

    @Override
    public String toString() {
        return "SizesId{" +
            "sizestypecompanycode='" + sizestypecompanycode + '\'' +
            ", sizestypecode='" + sizestypecode + '\'' +
            ", code='" + code + '\'' +
            '}';
    }
}
