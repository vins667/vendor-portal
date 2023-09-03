package io.vamani.application.db2.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class ColorId implements Serializable {
    private String colorfoldercompanycode;
    private String colorfoldercode;
    private String code;

    @Column(name = "COLORFOLDERCOMPANYCODE", nullable = false, length = 3)
    @Id
    public String getColorfoldercompanycode() {
        return colorfoldercompanycode;
    }

    public void setColorfoldercompanycode(String colorfoldercompanycode) {
        this.colorfoldercompanycode = colorfoldercompanycode;
    }

    @Column(name = "COLORFOLDERCODE", nullable = false, length = 3)
    @Id
    public String getColorfoldercode() {
        return colorfoldercode;
    }

    public void setColorfoldercode(String colorfoldercode) {
        this.colorfoldercode = colorfoldercode;
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
        ColorId colorId = (ColorId) o;
        return Objects.equals(colorfoldercompanycode, colorId.colorfoldercompanycode) && Objects.equals(colorfoldercode, colorId.colorfoldercode) && Objects.equals(code, colorId.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(colorfoldercompanycode, colorfoldercode, code);
    }
}
