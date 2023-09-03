
package io.vamani.application.model;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "color_name",
    "size_name",
    "floor_name",
    "line_name",
    "section_name",
    "operation_name",
    "production_date",
    "shift_name",
    "location_name",
    "shift_hour",
    "style_name",
    "production_quantity"
})
@Generated("jsonschema2pojo")
public class Result {

    @JsonProperty("color_name")
    private String colorName;
    @JsonProperty("size_name")
    private String sizeName;
    @JsonProperty("floor_name")
    private String floorName;
    @JsonProperty("line_name")
    private String lineName;
    @JsonProperty("section_name")
    private String sectionName;
    @JsonProperty("operation_name")
    private String operationName;
    @JsonProperty("production_date")
    private String productionDate;
    @JsonProperty("shift_name")
    private String shiftName;
    @JsonProperty("location_name")
    private String locationName;
    @JsonProperty("shift_hour")
    private String shiftHour;
    @JsonProperty("style_name")
    private String styleName;
    @JsonProperty("production_quantity")
    private Integer productionQuantity;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("color_name")
    public String getColorName() {
        return colorName;
    }

    @JsonProperty("color_name")
    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    @JsonProperty("size_name")
    public String getSizeName() {
        return sizeName;
    }

    @JsonProperty("size_name")
    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    @JsonProperty("floor_name")
    public String getFloorName() {
        return floorName;
    }

    @JsonProperty("floor_name")
    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    @JsonProperty("line_name")
    public String getLineName() {
        return lineName;
    }

    @JsonProperty("line_name")
    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    @JsonProperty("section_name")
    public String getSectionName() {
        return sectionName;
    }

    @JsonProperty("section_name")
    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    @JsonProperty("operation_name")
    public String getOperationName() {
        return operationName;
    }

    @JsonProperty("operation_name")
    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    @JsonProperty("production_date")
    public String getProductionDate() {
        return productionDate;
    }

    @JsonProperty("production_date")
    public void setProductionDate(String productionDate) {
        this.productionDate = productionDate;
    }

    @JsonProperty("shift_name")
    public String getShiftName() {
        return shiftName;
    }

    @JsonProperty("shift_name")
    public void setShiftName(String shiftName) {
        this.shiftName = shiftName;
    }

    @JsonProperty("location_name")
    public String getLocationName() {
        return locationName;
    }

    @JsonProperty("location_name")
    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    @JsonProperty("shift_hour")
    public String getShiftHour() {
        return shiftHour;
    }

    @JsonProperty("shift_hour")
    public void setShiftHour(String shiftHour) {
        this.shiftHour = shiftHour;
    }

    @JsonProperty("style_name")
    public String getStyleName() {
        return styleName;
    }

    @JsonProperty("style_name")
    public void setStyleName(String styleName) {
        this.styleName = styleName;
    }

    @JsonProperty("production_quantity")
    public Integer getProductionQuantity() {
        return productionQuantity;
    }

    @JsonProperty("production_quantity")
    public void setProductionQuantity(Integer productionQuantity) {
        this.productionQuantity = productionQuantity;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

	@Override
	public String toString() {
		return "Result [colorName=" + colorName + ", sizeName=" + sizeName + ", floorName=" + floorName + ", lineName="
				+ lineName + ", sectionName=" + sectionName + ", operationName=" + operationName + ", productionDate="
				+ productionDate + ", shiftName=" + shiftName + ", locationName=" + locationName + ", shiftHour="
				+ shiftHour + ", styleName=" + styleName + ", productionQuantity=" + productionQuantity
				+ ", additionalProperties=" + additionalProperties + "]";
	}

    
}
