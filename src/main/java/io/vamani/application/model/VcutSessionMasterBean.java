package io.vamani.application.model;

import java.time.Instant;
import java.util.List;

import io.vamani.application.domain.VcutSessionDetails;

public class VcutSessionMasterBean {
	private Long id;
    private String planName;
    private Instant dayStartTime;
    private Integer hours;
    private Integer minutes;
    private Integer totalMinsPerDay;
    private String createdBy;
    private Instant createdDate;
    private String lastUpdatedBy;
    private Instant lastUpdatedDate;
    private List<VcutSessionDetails> vcutSessionDetails;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public Instant getDayStartTime() {
		return dayStartTime;
	}
	public void setDayStartTime(Instant dayStartTime) {
		this.dayStartTime = dayStartTime;
	}
	public Integer getHours() {
		return hours;
	}
	public void setHours(Integer hours) {
		this.hours = hours;
	}
	public Integer getMinutes() {
		return minutes;
	}
	public void setMinutes(Integer minutes) {
		this.minutes = minutes;
	}
	public Integer getTotalMinsPerDay() {
		return totalMinsPerDay;
	}
	public void setTotalMinsPerDay(Integer totalMinsPerDay) {
		this.totalMinsPerDay = totalMinsPerDay;
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
	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}
	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
	public Instant getLastUpdatedDate() {
		return lastUpdatedDate;
	}
	public void setLastUpdatedDate(Instant lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}
	public List<VcutSessionDetails> getVcutSessionDetails() {
		return vcutSessionDetails;
	}
	public void setVcutSessionDetails(List<VcutSessionDetails> vcutSessionDetails) {
		this.vcutSessionDetails = vcutSessionDetails;
	}
	@Override
	public String toString() {
		return "VcutSessionMasterBean [id=" + id + ", planName=" + planName + ", dayStartTime=" + dayStartTime
				+ ", hours=" + hours + ", minutes=" + minutes + ", totalMinsPerDay=" + totalMinsPerDay + ", createdBy="
				+ createdBy + ", createdDate=" + createdDate + ", lastUpdatedBy=" + lastUpdatedBy + ", lastUpdatedDate="
				+ lastUpdatedDate + ", vcutSessionDetails=" + vcutSessionDetails + "]";
	}
    
}
