package io.vamani.application.model;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import io.vamani.application.domain.ReportTypeMaster;
import io.vamani.application.domain.TermsConditionDetails;

public class TermsConditionMasterBean {
	private Long id;
    private LocalDate applicableDate;
    private LocalDate closedDate;
    private String createdBy;
    private Instant createdDate;
    private String lastUpdatedBy;
    private Instant lastUpdatedDate;
    private ReportTypeMaster reportTypeMaster;
    private List<TermsConditionDetails> termsConditionDetails;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDate getApplicableDate() {
		return applicableDate;
	}
	public void setApplicableDate(LocalDate applicableDate) {
		this.applicableDate = applicableDate;
	}
	public LocalDate getClosedDate() {
		return closedDate;
	}
	public void setClosedDate(LocalDate closedDate) {
		this.closedDate = closedDate;
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
	public ReportTypeMaster getReportTypeMaster() {
		return reportTypeMaster;
	}
	public void setReportTypeMaster(ReportTypeMaster reportTypeMaster) {
		this.reportTypeMaster = reportTypeMaster;
	}
	public List<TermsConditionDetails> getTermsConditionDetails() {
		return termsConditionDetails;
	}
	public void setTermsConditionDetails(List<TermsConditionDetails> termsConditionDetails) {
		this.termsConditionDetails = termsConditionDetails;
	}
	
    
}
