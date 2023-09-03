package io.vamani.application.model;

import io.vamani.application.domain.TdsYearMaster;

import java.time.Instant;
import java.util.Objects;

public class PreviousEmploymentDetailsBean {
    private Long id;

    private String financeYear;

    private String cardNo;
    private String name;
    private Instant dateFrom;

    private Instant dateTo;

    private String previousEmployer;

    private String landLordName;

    private String landLordPan;

    private String landLordAddress;

    private Double monthRent;

    private Double basic;

    private Double hra;

    private Double others;

    private Double tds;

    private Double cta;

    private Double spa;

    private Double mda;

    private Double epf;

    private Double vpf;

    private String createdBy;

    private Instant createdDate;

    private String lastUpdatedBy;

    private Instant lastUpdatedDate;

    private TdsYearMaster tdsYearMaster;

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
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

    public TdsYearMaster getTdsYearMaster() {
        return tdsYearMaster;
    }

    public void setTdsYearMaster(TdsYearMaster tdsYearMaster) {
        this.tdsYearMaster = tdsYearMaster;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PreviousEmploymentDetailsBean that = (PreviousEmploymentDetailsBean) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(financeYear, that.financeYear) &&
            Objects.equals(cardNo, that.cardNo) &&
            Objects.equals(dateFrom, that.dateFrom) &&
            Objects.equals(dateTo, that.dateTo) &&
            Objects.equals(previousEmployer, that.previousEmployer) &&
            Objects.equals(landLordName, that.landLordName) &&
            Objects.equals(landLordPan, that.landLordPan) &&
            Objects.equals(landLordAddress, that.landLordAddress) &&
            Objects.equals(monthRent, that.monthRent) &&
            Objects.equals(basic, that.basic) &&
            Objects.equals(hra, that.hra) &&
            Objects.equals(others, that.others) &&
            Objects.equals(tds, that.tds) &&
            Objects.equals(cta, that.cta) &&
            Objects.equals(spa, that.spa) &&
            Objects.equals(mda, that.mda) &&
            Objects.equals(epf, that.epf) &&
            Objects.equals(vpf, that.vpf) &&
            Objects.equals(createdBy, that.createdBy) &&
            Objects.equals(createdDate, that.createdDate) &&
            Objects.equals(lastUpdatedBy, that.lastUpdatedBy) &&
            Objects.equals(lastUpdatedDate, that.lastUpdatedDate) &&
            Objects.equals(tdsYearMaster, that.tdsYearMaster);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, financeYear, cardNo, dateFrom, dateTo, previousEmployer, landLordName, landLordPan, landLordAddress, monthRent, basic, hra, others, tds, cta, spa, mda, epf, vpf, createdBy, createdDate, lastUpdatedBy, lastUpdatedDate, tdsYearMaster);
    }

    @Override
    public String toString() {
        return "PreviousEmploymentDetailsBean{" +
            "id=" + id +
            ", financeYear='" + financeYear + '\'' +
            ", cardNo='" + cardNo + '\'' +
            ", dateFrom=" + dateFrom +
            ", dateTo=" + dateTo +
            ", previousEmployer='" + previousEmployer + '\'' +
            ", landLordName='" + landLordName + '\'' +
            ", landLordPan='" + landLordPan + '\'' +
            ", landLordAddress='" + landLordAddress + '\'' +
            ", monthRent=" + monthRent +
            ", basic=" + basic +
            ", hra=" + hra +
            ", others=" + others +
            ", tds=" + tds +
            ", cta=" + cta +
            ", spa=" + spa +
            ", mda=" + mda +
            ", epf=" + epf +
            ", vpf=" + vpf +
            ", createdBy='" + createdBy + '\'' +
            ", createdDate=" + createdDate +
            ", lastUpdatedBy='" + lastUpdatedBy + '\'' +
            ", lastUpdatedDate=" + lastUpdatedDate +
            ", tdsYearMaster=" + tdsYearMaster +
            '}';
    }
}
