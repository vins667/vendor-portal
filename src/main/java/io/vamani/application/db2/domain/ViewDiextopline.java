package io.vamani.application.db2.domain;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "VIEW_DIEXTOPLINE")
public class ViewDiextopline {
    @EmbeddedId
    private ViewDiextoplineId id;
    @Basic
    @Column(name = "ORDERDATE", nullable = false)
    private Date orderdate;

    @Column(name = "ORDPRNCUSTOMERSUPPLIERCODE")
    private String ordprncustomersuppliercode;

    @Column(name = "PROGRESSSTATUS")
    private String progressstatus;

    public ViewDiextoplineId getId() {
        return id;
    }

    public void setId(ViewDiextoplineId id) {
        this.id = id;
    }

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    public String getProgressstatus() {
        return progressstatus;
    }

    public void setProgressstatus(String progressstatus) {
        this.progressstatus = progressstatus;
    }

    public String getOrdprncustomersuppliercode() {
        return ordprncustomersuppliercode;
    }

    public void setOrdprncustomersuppliercode(String ordprncustomersuppliercode) {
        this.ordprncustomersuppliercode = ordprncustomersuppliercode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ViewDiextopline that = (ViewDiextopline) o;
        return Objects.equals(id, that.id) && Objects.equals(orderdate, that.orderdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderdate);
    }

    @Override
    public String toString() {
        return "Purchaseorder{" +
            "id=" + id +
            ", orderdate=" + orderdate +
            '}';
    }
}
