package com.itembank.enums;

/**
 * @Description: 账户状态
 * @Author: KunSpireUp
 * @Date: 5/12/2024 12:05 AM
 */
public enum SysAccountStatusEnum {

	ENABLE(1,"启用"),
	DISABLE(0,"禁用");

	private Integer status;
	private String desc;

	SysAccountStatusEnum(Integer status, String desc) {
		this.status = status;
		this.desc = desc;
	}

	public Integer getStatus() {
		return status;
	}

	public String getDesc() {
		return desc;
	}
}
