package io.vamani.application.domain;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "stitching_data")
public class StitchingDataEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name = "stitchingDataEntitySeq", sequenceName = "stitching_data_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "stitchingDataEntitySeq")
	@Column(name = "id")
	private Long id;
	@Column(name = "color_name")
	private String colorName;
	@Column(name = "size_name")
	private String sizeName;
	@Column(name = "floor_name")
	private String floorName;
	@Column(name = "line_name")
	private String lineName;
	@Column(name = "section_name")
	private String sectionName;
	@Column(name = "operation_name")
	private String operationName;
	@Column(name = "production_date")
	private LocalDate productionDate;
	@Column(name = "shift_name")
	private String shiftName;
	@Column(name = "location_name")
	private String locationName;
	@Column(name = "shift_hour")
	private String shiftHour;
	@Column(name = "style_name")
	private String styleName;
	@Column(name = "production_quantity")
	private Integer productionQuantity;
	@Size(max = 50)
	@Column(name = "created_by", length = 50)
	private String createdBy;

	@Column(name = "created_date")
	private Instant createdDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getColorName() {
		return colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	public String getSizeName() {
		return sizeName;
	}

	public void setSizeName(String sizeName) {
		this.sizeName = sizeName;
	}

	public String getFloorName() {
		return floorName;
	}

	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}

	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public String getOperationName() {
		return operationName;
	}

	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}

	public LocalDate getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(LocalDate productionDate) {
		this.productionDate = productionDate;
	}

	public String getShiftName() {
		return shiftName;
	}

	public void setShiftName(String shiftName) {
		this.shiftName = shiftName;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getShiftHour() {
		return shiftHour;
	}

	public void setShiftHour(String shiftHour) {
		this.shiftHour = shiftHour;
	}

	public String getStyleName() {
		return styleName;
	}

	public void setStyleName(String styleName) {
		this.styleName = styleName;
	}

	public Integer getProductionQuantity() {
		return productionQuantity;
	}

	public void setProductionQuantity(Integer productionQuantity) {
		this.productionQuantity = productionQuantity;
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

	@Override
	public String toString() {
		return "StitchingDataEntity [id=" + id + ", colorName=" + colorName + ", sizeName=" + sizeName + ", floorName="
				+ floorName + ", lineName=" + lineName + ", sectionName=" + sectionName + ", operationName="
				+ operationName + ", productionDate=" + productionDate + ", shiftName=" + shiftName + ", locationName="
				+ locationName + ", shiftHour=" + shiftHour + ", styleName=" + styleName + ", productionQuantity="
				+ productionQuantity + ", createdBy=" + createdBy + ", createdDate=" + createdDate + "]";
	}

}
