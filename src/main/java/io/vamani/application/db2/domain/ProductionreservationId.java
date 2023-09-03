package io.vamani.application.db2.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class ProductionreservationId implements Serializable {
    private String companycode;
    private String ordercountercode;
    private String ordercode;
    private Integer reservationline;

    @Column(name = "COMPANYCODE", nullable = false, length = 3)
    @Id
    public String getCompanycode() {
        return companycode;
    }

    public void setCompanycode(String companycode) {
        this.companycode = companycode;
    }

    @Column(name = "ORDERCOUNTERCODE", nullable = false, length = 8)
    @Id
    public String getOrdercountercode() {
        return ordercountercode;
    }

    public void setOrdercountercode(String ordercountercode) {
        this.ordercountercode = ordercountercode;
    }

    @Column(name = "ORDERCODE", nullable = false, length = 15)
    @Id
    public String getOrdercode() {
        return ordercode;
    }

    public void setOrdercode(String ordercode) {
        this.ordercode = ordercode;
    }

    @Column(name = "RESERVATIONLINE", nullable = false, precision = 0)
    @Id
    public Integer getReservationline() {
        return reservationline;
    }

    public void setReservationline(Integer reservationline) {
        this.reservationline = reservationline;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductionreservationId that = (ProductionreservationId) o;
        return Objects.equals(companycode, that.companycode) &&
            Objects.equals(ordercountercode, that.ordercountercode) &&
            Objects.equals(ordercode, that.ordercode) &&
            Objects.equals(reservationline, that.reservationline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companycode, ordercountercode, ordercode, reservationline);
    }
}
