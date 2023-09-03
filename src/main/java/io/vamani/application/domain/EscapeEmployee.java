package io.vamani.application.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "escape_employee")
public class EscapeEmployee implements Serializable {

    @EmbeddedId
    EscapeEmployeeId id;

    @Column(name = "flag")
    private String flag;

    public EscapeEmployeeId getId() {
        return id;
    }

    public void setId(EscapeEmployeeId id) {
        this.id = id;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EscapeEmployee that = (EscapeEmployee) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(flag, that.flag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, flag);
    }

    @Override
    public String toString() {
        return "EscapeEmployee{" +
            "id=" + id +
            ", flag='" + flag + '\'' +
            '}';
    }
}
