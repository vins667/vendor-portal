package io.vamani.application.model;

import java.util.List;

public class UserPlantBean {

	private String Login;
	private List<UserPlantDetailsBean> userPlantDetailsNew;
	
	public String getLogin() {
		return Login;
	}
	public void setLogin(String login) {
		Login = login;
	}
	public List<UserPlantDetailsBean> getUserPlantDetailsNew() {
		return userPlantDetailsNew;
	}
	public void setUserPlantDetailsNew(List<UserPlantDetailsBean> userPlantDetailsNew) {
		this.userPlantDetailsNew = userPlantDetailsNew;
	}
	
	
}
