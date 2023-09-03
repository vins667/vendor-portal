package io.vamani.application.vendorportal.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "vendor_terms")
public class VendorTerms implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @SequenceGenerator(name="vendorTermsSeq", sequenceName="vendor_terms_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="vendorTermsSeq")
    private Long id;

    @NotNull
    @Column(name = "terms", nullable = false)
    private String terms;

    @NotNull
    @Column(name = "vendor_id", nullable = false)
    private Long vendorId;
    
    @Size(max = 50)
    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Column(name = "created_date")
    private Instant createdDate;

    @Size(max = 50)
    @Column(name = "last_updated_by", length = 50)
    private String lastUpdatedBy;

    @Column(name = "last_updated_date")
    private Instant lastUpdatedDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTerms() {
		return terms;
	}

	public void setTerms(String terms) {
		this.terms = terms;
	}
	
	public Long getVendorId() {
		return vendorId;
	}

	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "VendorTerms [id=" + id + ", terms=" + terms + ", vendorId=" + vendorId + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", lastUpdatedBy=" + lastUpdatedBy + ", lastUpdatedDate="
				+ lastUpdatedDate + "]";
	}

	
}
