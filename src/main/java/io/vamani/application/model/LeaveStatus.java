package io.vamani.application.model;

public class LeaveStatus {
    private String leaveType;
    private Double leaveBalance;

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public Double getLeaveBalance() {
        return leaveBalance;
    }

    public void setLeaveBalance(Double leaveBalance) {
        this.leaveBalance = leaveBalance;
    }

    public LeaveStatus() {
    }

    public LeaveStatus(String leaveType, Double leaveBalance) {
        this.leaveType = leaveType;
        this.leaveBalance = leaveBalance;
    }
}
