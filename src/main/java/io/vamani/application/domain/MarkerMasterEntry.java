package io.vamani.application.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A MarkerMasterEntry.
 */
@Entity
@Table(name = "marker_master_entry")
public class MarkerMasterEntry implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="markerMasterEntrySeq", sequenceName="marker_master_entry_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="markerMasterEntrySeq")
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "marker_code", length = 50, nullable = false)
    private String markerCode;

    @Size(max = 10)
    @Column(name = "plant_code", length = 10)
    private String plantCode;

    @Size(max = 100)
    @Column(name = "plant_description", length = 100)
    private String plantDescription;

    @Column(name = "body_fabric")
    private Boolean bodyFabric;

    @NotNull
    @Size(max = 20)
    @Column(name = "style", length = 20, nullable = false)
    private String style;

    @Size(max = 200)
    @Column(name = "item_code", length = 200)
    private String itemCode;

    @NotNull
    @Size(max = 20)
    @Column(name = "color", length = 20, nullable = false)
    private String color;

    @NotNull
    @Size(max = 100)
    @Column(name = "color_desc", length = 100, nullable = false)
    private String colorDesc;

    @Size(max = 10)
    @Column(name = "width", length = 10)
    private String width;

    @Column(name = "length")
    private Double length;

    @Column(name = "order_qty")
    private Double orderQty;

    @Size(max = 50)
    @Column(name = "item_type", length = 50)
    private String itemType;

    @Size(max = 50)
    @Column(name = "subcode01", length = 50)
    private String subcode01;

    @Size(max = 50)
    @Column(name = "subcode02", length = 50)
    private String subcode02;

    @Size(max = 50)
    @Column(name = "subcode03", length = 50)
    private String subcode03;

    @Size(max = 50)
    @Column(name = "subcode04", length = 50)
    private String subcode04;

    @Size(max = 50)
    @Column(name = "subcode05", length = 50)
    private String subcode05;

    @Size(max = 50)
    @Column(name = "subcode06", length = 50)
    private String subcode06;

    @Size(max = 50)
    @Column(name = "subcode07", length = 50)
    private String subcode07;

    @Size(max = 50)
    @Column(name = "subcode08", length = 50)
    private String subcode08;

    @Size(max = 50)
    @Column(name = "subcode09", length = 50)
    private String subcode09;

    @Size(max = 50)
    @Column(name = "subcode10", length = 50)
    private String subcode10;

    @Size(max = 2)
    @Column(name = "order_status", length = 2)
    private String orderStatus;

    @Column(name = "planned_avg")
    private Double plannedAvg;

    @Column(name = "actual_avg")
    private Double actualAvg;

    @Size(max = 20)
    @Column(name = "created_by", length = 20)
    private String createdBy;

    @Column(name = "created_date")
    private Instant createdDate;

    @Size(max = 20)
    @Column(name = "last_updated_by", length = 20)
    private String lastUpdatedBy;

    @Column(name = "last_updated_date")
    private Instant lastUpdatedDate;

    @Size(max = 1)
    @Column(name = "approval_flag", length = 1)
    private String approvalFlag;

    @Size(max = 50)
    @Column(name = "approved_by", length = 50)
    private String approvedBy;

    @Column(name = "approved_date")
    private Instant approvedDate;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarkerCode() {
        return markerCode;
    }

    public MarkerMasterEntry markerCode(String markerCode) {
        this.markerCode = markerCode;
        return this;
    }

    public void setMarkerCode(String markerCode) {
        this.markerCode = markerCode;
    }

    public String getStyle() {
        return style;
    }

    public MarkerMasterEntry style(String style) {
        this.style = style != null ? style.toUpperCase().trim() : style;
        return this;
    }

    public void setStyle(String style) {
        this.style = style != null ? style.toUpperCase().trim() : style;
    }

    public String getColor() {
        return color;
    }

    public MarkerMasterEntry color(String color) {
        this.color = color != null ? color.toUpperCase().trim() : color;
        return this;
    }

    public void setColor(String color) {
        this.color = color != null ? color.toUpperCase().trim() : color;
    }

    public String getColorDesc() {
        return colorDesc;
    }

    public void setColorDesc(String colorDesc) {
        this.colorDesc = colorDesc != null ? colorDesc.toUpperCase().trim() : colorDesc;
    }

    public MarkerMasterEntry colorDesc(String colorDesc) {
        this.colorDesc = colorDesc != null ? colorDesc.toUpperCase().trim() : colorDesc;
        return this;
    }

    public Double getOrderQty() {
        return orderQty;
    }

    public MarkerMasterEntry orderQty(Double orderQty) {
        this.orderQty = orderQty;
        return this;
    }

    public void setOrderQty(Double orderQty) {
        this.orderQty = orderQty;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public MarkerMasterEntry orderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
        return this;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public MarkerMasterEntry createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public MarkerMasterEntry createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public MarkerMasterEntry lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public MarkerMasterEntry lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public Double getLength() {
        return length;
    }

    public MarkerMasterEntry length(Double length) {
        this.length = length;
        return this;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public String getWidth() {
        return width;
    }

    public MarkerMasterEntry width(String width) {
        this.width = width;
        return this;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getItemCode() {
        return itemCode;
    }

    public MarkerMasterEntry itemCode(String itemCode) {
        this.itemCode = itemCode;
        return this;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

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

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MarkerMasterEntry)) {
            return false;
        }
        return id != null && id.equals(((MarkerMasterEntry) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

	@Override
	public String toString() {
		return "MarkerMasterEntry [id=" + id + ", markerCode=" + markerCode + ", style=" + style + ", itemCode="
				+ itemCode + ", color=" + color + ", colorDesc=" + colorDesc + ", width=" + width + ", length=" + length
				+ ", orderQty=" + orderQty + ", subcode01=" + subcode01 + ", subcode02=" + subcode02 + ", subcode03="
				+ subcode03 + ", subcode04=" + subcode04 + ", subcode05=" + subcode05 + ", subcode06=" + subcode06
				+ ", subcode07=" + subcode07 + ", subcode08=" + subcode08 + ", subcode09=" + subcode09 + ", subcode10="
				+ subcode10 + ", orderStatus=" + orderStatus + ", createdBy=" + createdBy + ", createdDate="
				+ createdDate + ", lastUpdatedBy=" + lastUpdatedBy + ", lastUpdatedDate=" + lastUpdatedDate + "]";
	}




}
