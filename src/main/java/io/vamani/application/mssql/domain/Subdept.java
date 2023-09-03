package io.vamani.application.mssql.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "subdept_view")
public class Subdept  implements Serializable {
    @Id
    @Column(name = "sdep_code")
    private Long sdepCode;

    @Column(name = "sdep_desc")
    private String sdepDesc;

    @Column(name = "dep_code")
    private Long depCode;

    @Column(name = "tmp")
    private Long tmp;

    public Long getSdepCode() {
        return sdepCode;
    }

    public void setSdepCode(Long sdepCode) {
        this.sdepCode = sdepCode;
    }

    public String getSdepDesc() {
        return sdepDesc;
    }

    public void setSdepDesc(String sdepDesc) {
        this.sdepDesc = sdepDesc;
    }

    public Long getDepCode() {
        return depCode;
    }

    public void setDepCode(Long depCode) {
        this.depCode = depCode;
    }

    public Long getTmp() {
        return tmp;
    }

    public void setTmp(Long tmp) {
        this.tmp = tmp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subdept subdept = (Subdept) o;
        return Objects.equals(sdepCode, subdept.sdepCode) &&
            Objects.equals(sdepDesc, subdept.sdepDesc) &&
            Objects.equals(depCode, subdept.depCode) &&
            Objects.equals(tmp, subdept.tmp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sdepCode, sdepDesc, depCode, tmp);
    }

    @Override
    public String toString() {
        return "Subdept{" +
            "sdepCode=" + sdepCode +
            ", sdepDesc='" + sdepDesc + '\'' +
            ", depCode=" + depCode +
            ", tmp=" + tmp +
            '}';
    }
}
