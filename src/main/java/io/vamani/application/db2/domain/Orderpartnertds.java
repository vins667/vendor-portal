package io.vamani.application.db2.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "view_diorderpartnertds")
public class Orderpartnertds {
    @EmbeddedId
    private OrderpartnertdsId id;
    private String longdescription;

    private String exemptionnumber;
    private BigDecimal value;

    public OrderpartnertdsId getId() {
        return id;
    }

    public void setId(OrderpartnertdsId id) {
        this.id = id;
    }

    @Basic
    @Column(name = "LONGDESCRIPTION", nullable = true, length = 100)
    public String getLongdescription() {
        return longdescription;
    }

    public void setLongdescription(String longdescription) {
        this.longdescription = longdescription;
    }

    @Basic
    @Column(name = "EXEMPTIONNUMBER")
    public String getExemptionnumber() {
        return exemptionnumber;
    }

    public void setExemptionnumber(String exemptionnumber) {
        this.exemptionnumber = exemptionnumber;
    }

    @Basic
    @Column(name = "VALUE", nullable = true, precision = 2)
    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orderpartnertds that = (Orderpartnertds) o;
        return Objects.equals(id, that.id) && Objects.equals(longdescription, that.longdescription) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, longdescription, value);
    }
}
