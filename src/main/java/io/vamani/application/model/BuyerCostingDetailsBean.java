package io.vamani.application.model;
import java.io.Serializable;
import java.util.List;
import io.vamani.application.domain.CostingGroupDetails;

public class BuyerCostingDetailsBean implements Serializable{
    private String groupCode;
    List<BuyerCostingSubDetailsBean> buyerCostingSubDetails;
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public List<BuyerCostingSubDetailsBean> getBuyerCostingSubDetails() {
		return buyerCostingSubDetails;
	}
	public void setBuyerCostingSubDetails(List<BuyerCostingSubDetailsBean> buyerCostingSubDetails) {
		this.buyerCostingSubDetails = buyerCostingSubDetails;
	}
	
	   
}
