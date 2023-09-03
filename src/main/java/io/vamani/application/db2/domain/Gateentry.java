package io.vamani.application.db2.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "gateentry")
public class Gateentry {
    @EmbeddedId
    private GateentryId id;

    @Column(name = "GATEPASSNO")
    private String gatepassno;

    @Column(name = "UGGUSERGENGRPTYPECOMPANYCODE")
    private String uggusergengrptypecompanycode;

    @Column(name = "UGGUSERGENERICGROUPTYPECODE")
    private String uggusergenericgrouptypecode;

    @Column(name = "UGGCODE")
    private String uggcode;

    public GateentryId getId() {
        return id;
    }

    public void setId(GateentryId id) {
        this.id = id;
    }

    public String getGatepassno() {
        return gatepassno;
    }

    public void setGatepassno(String gatepassno) {
        this.gatepassno = gatepassno;
    }

    public String getUggusergengrptypecompanycode() {
        return uggusergengrptypecompanycode;
    }

    public void setUggusergengrptypecompanycode(String uggusergengrptypecompanycode) {
        this.uggusergengrptypecompanycode = uggusergengrptypecompanycode;
    }

    public String getUggusergenericgrouptypecode() {
        return uggusergenericgrouptypecode;
    }

    public void setUggusergenericgrouptypecode(String uggusergenericgrouptypecode) {
        this.uggusergenericgrouptypecode = uggusergenericgrouptypecode;
    }

    public String getUggcode() {
        return uggcode;
    }

    public void setUggcode(String uggcode) {
        this.uggcode = uggcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gateentry gateentry = (Gateentry) o;
        return Objects.equals(id, gateentry.id) && Objects.equals(gatepassno, gateentry.gatepassno);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, gatepassno);
    }
}
