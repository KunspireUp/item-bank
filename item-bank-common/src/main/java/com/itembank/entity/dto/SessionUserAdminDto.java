package com.itembank.entity.dto;

/**
 * @Description: 用户 session 信息
 * @Author: KunSpireUp
 * @Date: 5/12/2024 8:26 PM
 */
public class SessionUserAdminDto {

	private Integer userId;
	private String userName;

	private Boolean superAdmin;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Boolean getSuperAdmin() {
		return superAdmin;
	}

	public void setSuperAdmin(Boolean superAdmin) {
		this.superAdmin = superAdmin;
	}
}
