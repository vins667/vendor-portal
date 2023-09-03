package io.vamani.application.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

public class ShiftBean implements Serializable {
    private Instant todate;

    public Instant getTodate() {
        return todate;
    }

    public void setTodate(Instant todate) {
        this.todate = todate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShiftBean shiftBean = (ShiftBean) o;
        return Objects.equals(todate, shiftBean.todate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(todate);
    }

    @Override
    public String toString() {
        return "ShiftBean{" +
            "todate=" + todate +
            '}';
    }
}
