package io.vamani.application.mobile;

import java.time.Instant;
import java.util.Objects;

public class CompOffMasterMobile {
    private Long id;


    private String empCode;

    private String empName;

    private String compOffDateView;

    private String timeFrom;

    private String timeTo;

    private Double balance;

    private String availDateView;

    private String hodApprovedBy;

    private String hodApprovedDateView;

    private String createdBy;

    private Instant createdDate;

    private String remarks;

    private String flag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompOffDateView() {
        return compOffDateView;
    }

    public void setCompOffDateView(String compOffDateView) {
        this.compOffDateView = compOffDateView;
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

    public String getAvailDateView() {
        return availDateView;
    }

    public void setAvailDateView(String availDateView) {
        this.availDateView = availDateView;
    }

    public String getHodApprovedBy() {
        return hodApprovedBy;
    }

    public void setHodApprovedBy(String hodApprovedBy) {
        this.hodApprovedBy = hodApprovedBy;
    }

    public String getHodApprovedDateView() {
        return hodApprovedDateView;
    }

    public void setHodApprovedDateView(String hodApprovedDateView) {
        this.hodApprovedDateView = hodApprovedDateView;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
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

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompOffMasterMobile that = (CompOffMasterMobile) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(empCode, that.empCode) &&
            Objects.equals(empName, that.empName) &&
            Objects.equals(compOffDateView, that.compOffDateView) &&
            Objects.equals(timeFrom, that.timeFrom) &&
            Objects.equals(timeTo, that.timeTo) &&
            Objects.equals(balance, that.balance) &&
            Objects.equals(availDateView, that.availDateView) &&
            Objects.equals(hodApprovedBy, that.hodApprovedBy) &&
            Objects.equals(hodApprovedDateView, that.hodApprovedDateView) &&
            Objects.equals(createdBy, that.createdBy) &&
            Objects.equals(createdDate, that.createdDate) &&
            Objects.equals(remarks, that.remarks) &&
            Objects.equals(flag, that.flag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, empCode, empName, compOffDateView, timeFrom, timeTo, balance, availDateView, hodApprovedBy, hodApprovedDateView, createdBy, createdDate, remarks, flag);
    }

    @Override
    public String toString() {
        return "CompOffMasterMobile{" +
            "id=" + id +
            ", empCode='" + empCode + '\'' +
            ", empName='" + empName + '\'' +
            ", compOffDateView='" + compOffDateView + '\'' +
            ", timeFrom='" + timeFrom + '\'' +
            ", timeTo='" + timeTo + '\'' +
            ", balance=" + balance +
            ", availDateView='" + availDateView + '\'' +
            ", hodApprovedBy='" + hodApprovedBy + '\'' +
            ", hodApprovedDateView='" + hodApprovedDateView + '\'' +
            ", createdBy='" + createdBy + '\'' +
            ", createdDate=" + createdDate +
            ", remarks='" + remarks + '\'' +
            ", flag='" + flag + '\'' +
            '}';
    }
}
