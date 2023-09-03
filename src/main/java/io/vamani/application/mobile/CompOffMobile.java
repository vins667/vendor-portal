package io.vamani.application.mobile;

import java.util.Objects;

public class CompOffMobile {
    private Long id;
    private String compOffDate;

    private String timeFrom;

    private String timeTo;

    private Double balance;

    private String availDate;

    private String remarks;

    private String flag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompOffDate() {
        return compOffDate;
    }

    public void setCompOffDate(String compOffDate) {
        this.compOffDate = compOffDate;
    }

    public String getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(String timeFrom) {
        this.timeFrom = timeFrom;
    }

    public String getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(String timeTo) {
        this.timeTo = timeTo;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getAvailDate() {
        return availDate;
    }

    public void setAvailDate(String availDate) {
        this.availDate = availDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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
        CompOffMobile that = (CompOffMobile) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(compOffDate, that.compOffDate) &&
            Objects.equals(timeFrom, that.timeFrom) &&
            Objects.equals(timeTo, that.timeTo) &&
            Objects.equals(balance, that.balance) &&
            Objects.equals(availDate, that.availDate) &&
            Objects.equals(remarks, that.remarks) &&
            Objects.equals(flag, that.flag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, compOffDate, timeFrom, timeTo, balance, availDate, remarks, flag);
    }

    @Override
    public String toString() {
        return "CompOffMobile{" +
            "id=" + id +
            ", compOffDate='" + compOffDate + '\'' +
            ", timeFrom='" + timeFrom + '\'' +
            ", timeTo='" + timeTo + '\'' +
            ", balance=" + balance +
            ", availDate='" + availDate + '\'' +
            ", remarks='" + remarks + '\'' +
            ", flag='" + flag + '\'' +
            '}';
    }
}
