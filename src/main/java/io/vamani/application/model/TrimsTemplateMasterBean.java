package io.vamani.application.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

public class TrimsTemplateMasterBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String accessoriesCode;
	
	private String description;
	
	private String flag;
	
	private String createdBy;
	
	private Instant createdDate;
	
	private String lastUpdatedBy;
	
	private Instant lastUpdatedDate;
	
	private List<TrimsTemplateDetailsBean> trimsTemplateDetails;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccessoriesCode() {
		return accessoriesCode;
	}

	public void setAccessoriesCode(String accessoriesCode) {
		this.accessoriesCode = accessoriesCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
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

	public List<TrimsTemplateDetailsBean> getTrimsTemplateDetails() {
		return trimsTemplateDetails;
	}

	public void setTrimsTemplateDetails(List<TrimsTemplateDetailsBean> trimsTemplateDetails) {
		this.trimsTemplateDetails = trimsTemplateDetails;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
