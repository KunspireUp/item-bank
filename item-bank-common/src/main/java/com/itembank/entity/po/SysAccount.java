package com.itembank.entity.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.itembank.annotation.VerifyParam;
import com.itembank.enums.DateTimePatternEnum;
import com.itembank.utils.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;


/**
 * @Description: 账号信息
 * @Author: false
 * @Date: 2024/05/10 22:43:07
 */
public class SysAccount implements Serializable {
	/**
	 * 用户ID
	 */
	private Integer userId;

	/**
	 * 手机号
	 */
	@VerifyParam(required = true)
	private String phone;

	/**
	 * 用户名
	 */
	private String userName;

	/**
	 * 密码
	 */
	@VerifyParam(required = true)
	private String password;

	/**
	 * 职位
	 */
	private String positon;

	/**
	 * 状态 0:禁用 1:启用
	 */
	@JsonIgnore
	private Integer status;

	/**
	 * 用户拥有的角色多个用逗号隔开
	 */
	private String roles;

	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;


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

	@Override
	public String toString() {
		return "用户ID:" + (userId == null ? "空" : userId) + "," +
				"手机号:" + (phone == null ? "空" : phone) + "," +
				"用户名:" + (userName == null ? "空" : userName) + "," +
				"密码:" + (password == null ? "空" : password) + "," +
				"职位:" + (positon == null ? "空" : positon) + "," +
				"状态 0:禁用 1:启用:" + (status == null ? "空" : status) + "," +
				"用户拥有的角色多个用逗号隔开:" + (roles == null ? "空" : roles) + "," +
				"创建时间:" + (createTime == null ? "空" : DateUtils.format(createTime, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()));
	}
}