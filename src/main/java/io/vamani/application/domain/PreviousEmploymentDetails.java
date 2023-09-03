package io.vamani.application.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A PreviousEmploymentDetails.
 */
@Entity
@Table(name = "previous_employment_details")
public class PreviousEmploymentDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="previousEmploymentDetailsSeq", sequenceName="previous_employment_details_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="previousEmploymentDetailsSeq")
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "finance_year", length = 50, nullable = false)
    private String financeYear;

    @NotNull
    @Size(max = 50)
    @Column(name = "card_no", length = 50, nullable = false)
    private String cardNo;

    @Column(name = "date_from")
    private Instant dateFrom;

    @Column(name = "date_to")
    private Instant dateTo;

    @NotNull
    @Size(max = 100)
    @Column(name = "previous_employer", length = 100, nullable = false)
    private String previousEmployer;

    @Size(max = 100)
    @Column(name = "land_lord_name", length = 100)
    private String landLordName;

    @Column(name = "land_lord_pan")
    private String landLordPan;

    @Size(max = 200)
    @Column(name = "land_lord_address", length = 200)
    private String landLordAddress;

    @Column(name = "month_rent")
    private Double monthRent;

    @Column(name = "basic")
    private Double basic;

    @Column(name = "hra")
    private Double hra;
    
    @Column(name = "others")
    private Double others;
    
    @Column(name = "tds")
    private Double tds;
    
    @Column(name = "cta")
    private Double cta;

    @Column(name = "spa")
    private Double spa;

    @Column(name = "mda")
    private Double mda;

    @Column(name = "epf")
    private Double epf;

    @Column(name = "vpf")
    private Double vpf;

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

	public String getFinanceYear() {
		return financeYear;
	}

	public void setFinanceYear(String financeYear) {
		this.financeYear = financeYear;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public Instant getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Instant dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Instant getDateTo() {
		return dateTo;
	}

	public void setDateTo(Instant dateTo) {
		this.dateTo = dateTo;
	}

	public String getPreviousEmployer() {
		return previousEmployer;
	}

	public void setPreviousEmployer(String previousEmployer) {
		this.previousEmployer = previousEmployer;
	}

	public String getLandLordName() {
		return landLordName;
	}

	public void setLandLordName(String landLordName) {
		this.landLordName = landLordName;
	}

	public String getLandLordPan() {
		return landLordPan;
	}

	public void setLandLordPan(String landLordPan) {
		this.landLordPan = landLordPan;
	}

	public String getLandLordAddress() {
		return landLordAddress;
	}

	public void setLandLordAddress(String landLordAddress) {
		this.landLordAddress = landLordAddress;
	}

	public Double getMonthRent() {
		return monthRent;
	}

	public void setMonthRent(Double monthRent) {
		this.monthRent = monthRent;
	}

	public Double getBasic() {
		return basic;
	}

	public void setBasic(Double basic) {
		this.basic = basic;
	}

	public Double getHra() {
		return hra;
	}

	public void setHra(Double hra) {
		this.hra = hra;
	}

	public Double getOthers() {
		return others;
	}

	public void setOthers(Double others) {
		this.others = others;
	}

	public Double getTds() {
		return tds;
	}

	public void setTds(Double tds) {
		this.tds = tds;
	}

	public Double getCta() {
		return cta;
	}

	public void setCta(Double cta) {
		this.cta = cta;
	}

	public Double getSpa() {
		return spa;
	}

	public void setSpa(Double spa) {
		this.spa = spa;
	}

	public Double getMda() {
		return mda;
	}

	public void setMda(Double mda) {
		this.mda = mda;
	}

	public Double getEpf() {
		return epf;
	}

	public void setEpf(Double epf) {
		this.epf = epf;
	}

	public Double getVpf() {
		return vpf;
	}

	public void setVpf(Double vpf) {
		this.vpf = vpf;
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

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    
}
