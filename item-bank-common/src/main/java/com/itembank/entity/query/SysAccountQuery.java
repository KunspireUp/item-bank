package com.itembank.entity.query;

import java.util.Date;

/**
 * @Description: 账号信息
 * @Author: false
 * @Date: 2024/05/10 22:43:07
 */
public class SysAccountQuery extends BaseQuery {
	/**
 	 * 用户ID 查询对象
 	 */
	private Integer userId;

	/**
 	 * 手机号 查询对象
 	 */
	private String phone;

	private String phoneFuzzy;

	/**
 	 * 用户名 查询对象
 	 */
	private String userName;

	private String userNameFuzzy;

	/**
 	 * 密码 查询对象
 	 */
	private String password;

	private String passwordFuzzy;

	/**
 	 * 职位 查询对象
 	 */
	private String positon;

	private String positonFuzzy;

	/**
 	 * 状态 0:禁用 1:启用 查询对象
 	 */
	private Integer status;

	/**
 	 * 用户拥有的角色多个用逗号隔开 查询对象
 	 */
	private String roles;

	private String rolesFuzzy;

	/**
 	 * 创建时间 查询对象
 	 */
	private Date createTime;

	private String createTimeStart;
	private String createTimeEnd;

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return phone;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPositon(String positon) {
		this.positon = positon;
	}

	public String getPositon() {
		return positon;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return status;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getRoles() {
		return roles;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setPhoneFuzzy(String phoneFuzzy) {
		this.phoneFuzzy = phoneFuzzy;
	}

	public String getPhoneFuzzy() {
		return phoneFuzzy;
	}

	public void setUserNameFuzzy(String userNameFuzzy) {
		this.userNameFuzzy = userNameFuzzy;
	}

	public String getUserNameFuzzy() {
		return userNameFuzzy;
	}

	public void setPasswordFuzzy(String passwordFuzzy) {
		this.passwordFuzzy = passwordFuzzy;
	}

	public String getPasswordFuzzy() {
		return passwordFuzzy;
	}

	public void setPositonFuzzy(String positonFuzzy) {
		this.positonFuzzy = positonFuzzy;
	}

	public String getPositonFuzzy() {
		return positonFuzzy;
	}

	public void setRolesFuzzy(String rolesFuzzy) {
		this.rolesFuzzy = rolesFuzzy;
	}

	public String getRolesFuzzy() {
		return rolesFuzzy;
	}

	public void setCreateTimeStart(String createTimeStart) {
		this.createTimeStart = createTimeStart;
	}

	public String getCreateTimeStart() {
		return createTimeStart;
	}

	public void setCreateTimeEnd(String createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

	public String getCreateTimeEnd() {
		return createTimeEnd;
	}
}