package io.vamani.application.model;
import java.io.Serializable;
import java.util.List;

import io.vamani.application.db2.domain.UnitOfMeasure;
import io.vamani.application.domain.CostingGroupDetails;

public class BuyerCostingSubDetailsBean implements Serializable{
	 private String itemType;
	 private String description;
	 private String umo;
	 private Double avg;
	 private Double rate;
	 private Double shrinkage;
	 private Double processrate;
	 private Double amount;
	 private Double amtwast;
	 private Double curramt;
	 List<CostingGroupDetails> costingGroupDetails;
	 List<UnitOfMeasure> unitOfMeasures;
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUmo() {
		return umo;
	}
	public void setUmo(String umo) {
		this.umo = umo;
	}
	public Double getAvg() {
		return avg;
	}
	public void setAvg(Double avg) {
		this.avg = avg;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	public Double getShrinkage() {
		return shrinkage;
	}
	public void setShrinkage(Double shrinkage) {
		this.shrinkage = shrinkage;
	}
	public Double getProcessrate() {
		return processrate;
	}
	public void setProcessrate(Double processrate) {
		this.processrate = processrate;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Double getAmtwast() {
		return amtwast;
	}
	public void setAmtwast(Double amtwast) {
		this.amtwast = amtwast;
	}
	public Double getCurramt() {
		return curramt;
	}
	public void setCurramt(Double curramt) {
		this.curramt = curramt;
	}
	public List<CostingGroupDetails> getCostingGroupDetails() {
		return costingGroupDetails;
	}
	public void setCostingGroupDetails(List<CostingGroupDetails> costingGroupDetails) {
		this.costingGroupDetails = costingGroupDetails;
	}
	public List<UnitOfMeasure> getUnitOfMeasures() {
		return unitOfMeasures;
	}
	public void setUnitOfMeasures(List<UnitOfMeasure> unitOfMeasures) {
		this.unitOfMeasures = unitOfMeasures;
	} 
	 
}
