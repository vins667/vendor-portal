package io.vamani.application.model;

import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.time.Instant;

public class LeaveSearch implements Serializable {
    private String dateType;
    private Instant leaveDateFrom;
    private Instant LeaveDateTo;
    private String empCode;
    private String hodCode;
    private String leaveStatus;
    private String factory;
    private Pageable page;
    private int size;
    private int pageNo;

    public String getDateType() {
        return dateType;
    }

    public void setDateType(String dateType) {
        this.dateType = dateType;
    }

    public Instant getLeaveDateFrom() {
        return leaveDateFrom;
    }

    public void setLeaveDateFrom(Instant leaveDateFrom) {
        this.leaveDateFrom = leaveDateFrom;
    }

    public Instant getLeaveDateTo() {
        return LeaveDateTo;
    }

    public void setLeaveDateTo(Instant leaveDateTo) {
        LeaveDateTo = leaveDateTo;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getHodCode() {
        return hodCode;
    }

    public void setHodCode(String hodCode) {
        this.hodCode = hodCode;
    }

    public String getLeaveStatus() {
        return leaveStatus;
    }

    public void setLeaveStatus(String leaveStatus) {
        this.leaveStatus = leaveStatus;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public Pageable getPage() {
        return page;
    }

    public void setPage(Pageable page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }
}
