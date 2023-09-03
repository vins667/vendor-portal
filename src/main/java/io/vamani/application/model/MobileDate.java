package io.vamani.application.model;

import io.vamani.application.domain.MobileAttendance;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class MobileDate implements Serializable {
    private String attendanceDate;
    private String flag;
    private List<MobileAttendanceBean> mobileAttendances;

    public String getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(String attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public List<MobileAttendanceBean> getMobileAttendances() {
        return mobileAttendances;
    }

    public void setMobileAttendances(List<MobileAttendanceBean> mobileAttendances) {
        this.mobileAttendances = mobileAttendances;
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
        MobileDate that = (MobileDate) o;
        return Objects.equals(attendanceDate, that.attendanceDate) &&
            Objects.equals(mobileAttendances, that.mobileAttendances);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attendanceDate, mobileAttendances);
    }

    @Override
    public String toString() {
        return "MobileDate{" +
            "attendanceDate='" + attendanceDate + '\'' +
            ", mobileAttendances=" + mobileAttendances +
            '}';
    }
}
