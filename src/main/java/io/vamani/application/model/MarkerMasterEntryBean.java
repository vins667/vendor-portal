package io.vamani.application.model;
import java.time.Instant;
import java.util.List;

import io.vamani.application.db2.model.FullitemkeydecoderBean;
import io.vamani.application.domain.MarkerEntryDetails;

import javax.persistence.Column;
import javax.validation.constraints.Size;

public class MarkerMasterEntryBean {

	private Long id;

	private String markerCode;

    private String plantCode;

    private String plantDescription;

    private Boolean bodyFabric;

	private String style;

	private FullitemkeydecoderBean itemCode;

	private String color;

    private String colorDesc;

    private String width;

    private Double length;

	private Double orderQty;

	private String itemType;

	private String subcode01;

	private String subcode02;

	private String subcode03;

	private String subcode04;

	private String subcode05;

	private String subcode06;

	private String subcode07;

	private String subcode08;

	private String subcode09;

	private String subcode10;

	private String orderStatus;

    private Double plannedAvg;

    private Double actualAvg;

	private String createdBy;

	private Instant createdDate;

	private String lastUpdatedby;

	private Instant lastUpdatedDate;

	private Boolean saveDisabled;

    private String approvalFlag;

    private String approvedBy;

    private Instant approvedDate;

	private List<MarkerEntryDetailsBean> MarkerEntryDetails;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarkerCode() {
        return markerCode;
    }

    public void setMarkerCode(String markerCode) {
        this.markerCode = markerCode;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

	public FullitemkeydecoderBean getItemCode() {
		return itemCode;
	}

	public void setItemCode(FullitemkeydecoderBean itemCode) {
		this.itemCode = itemCode;
	}

	public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColorDesc() {
        return colorDesc;
    }

    public void setColorDesc(String colorDesc) {
        this.colorDesc = colorDesc;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Double getOrderQty() {
        return orderQty;
    }

    public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public void setOrderQty(Double orderQty) {
        this.orderQty = orderQty;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
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

    public String getLastUpdatedby() {
        return lastUpdatedby;
    }

    public void setLastUpdatedby(String lastUpdatedby) {
        this.lastUpdatedby = lastUpdatedby;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public List<MarkerEntryDetailsBean> getMarkerEntryDetails() {
        return MarkerEntryDetails;
    }

    public void setMarkerEntryDetails(List<MarkerEntryDetailsBean> markerEntryDetails) {
        MarkerEntryDetails = markerEntryDetails;
    }

	public String getSubcode01() {
		return subcode01;
	}

	public void setSubcode01(String subcode01) {
		this.subcode01 = subcode01;
	}

	public String getSubcode02() {
		return subcode02;
	}

	public void setSubcode02(String subcode02) {
		this.subcode02 = subcode02;
	}

	public String getSubcode03() {
		return subcode03;
	}

	public void setSubcode03(String subcode03) {
		this.subcode03 = subcode03;
	}

	public String getSubcode04() {
		return subcode04;
	}

	public void setSubcode04(String subcode04) {
		this.subcode04 = subcode04;
	}

	public String getSubcode05() {
		return subcode05;
	}

	public void setSubcode05(String subcode05) {
		this.subcode05 = subcode05;
	}

	public String getSubcode06() {
		return subcode06;
	}

	public void setSubcode06(String subcode06) {
		this.subcode06 = subcode06;
	}

	public String getSubcode07() {
		return subcode07;
	}

	public void setSubcode07(String subcode07) {
		this.subcode07 = subcode07;
	}

	public String getSubcode08() {
		return subcode08;
	}

	public void setSubcode08(String subcode08) {
		this.subcode08 = subcode08;
	}

	public String getSubcode09() {
		return subcode09;
	}

	public void setSubcode09(String subcode09) {
		this.subcode09 = subcode09;
	}

	public String getSubcode10() {
		return subcode10;
	}

	public void setSubcode10(String subcode10) {
		this.subcode10 = subcode10;
	}

    public String getPlantCode() {
        return plantCode;
    }

    public void setPlantCode(String plantCode) {
        this.plantCode = plantCode;
    }

    public String getPlantDescription() {
        return plantDescription;
    }

    public void setPlantDescription(String plantDescription) {
        this.plantDescription = plantDescription;
    }

    public Boolean getBodyFabric() {
        return bodyFabric;
    }

    public void setBodyFabric(Boolean bodyFabric) {
        this.bodyFabric = bodyFabric;
    }

    public Boolean getSaveDisabled() {
        return saveDisabled;
    }

    public void setSaveDisabled(Boolean saveDisabled) {
        this.saveDisabled = saveDisabled;
    }

    public Double getPlannedAvg() {
        return plannedAvg;
    }

    public void setPlannedAvg(Double plannedAvg) {
        this.plannedAvg = plannedAvg;
    }

    public Double getActualAvg() {
        return actualAvg;
    }

    public void setActualAvg(Double actualAvg) {
        this.actualAvg = actualAvg;
    }

    public String getApprovalFlag() {
        return approvalFlag;
    }

    public void setApprovalFlag(String approvalFlag) {
        this.approvalFlag = approvalFlag;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public Instant getApprovedDate() {
        return approvedDate;
    }

    public void setApprovedDate(Instant approvedDate) {
        this.approvedDate = approvedDate;
    }
}
