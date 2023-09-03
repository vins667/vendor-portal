package io.vamani.application.model;

import java.util.List;

public class TdsGroupMasterBeans {
    private Long id;
    private String groupCode;
    private List<TdsDeclarationUploadDetailBean> tdsDeclarationUploadDetailBean;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public List<TdsDeclarationUploadDetailBean> getTdsDeclarationUploadDetailBean() {
		return tdsDeclarationUploadDetailBean;
	}
	public void setTdsDeclarationUploadDetailBean(List<TdsDeclarationUploadDetailBean> tdsDeclarationUploadDetailBean) {
		this.tdsDeclarationUploadDetailBean = tdsDeclarationUploadDetailBean;
	}
    
    
}
