package io.vamani.application.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

/**
 * A MonthDaysMaster.
 */
@Entity
@Table(name = "month_days_master")
public class MonthDaysMaster implements Serializable {
    @Id
    @Column(name = "month")
    private String month;

    @Column(name = "day")
    private Integer day;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MonthDaysMaster that = (MonthDaysMaster) o;
        return Objects.equals(month, that.month) &&
            Objects.equals(day, that.day);
    }

    @Override
    public int hashCode() {
        return Objects.hash(month, day);
    }

    @Override
    public String toString() {
        return "MonthDaysMaster{" +
            "month='" + month + '\'' +
            ", day=" + day +
            '}';
    }
}
