package com.itembank.service;


import com.itembank.entity.dto.SessionUserAdminDto;
import com.itembank.entity.po.SysAccount;
import com.itembank.entity.query.SysAccountQuery;
import com.itembank.entity.vo.PaginationResultVO;

import java.util.List;

/**
 * @Description: 账号信息 Service
 * @Author: false
 * @Date: 2024/05/10 22:43:07
 */
public interface SysAccountService {

	/**
	 * 根据条件查询列表
	 */
	List<SysAccount> findListByParam(SysAccountQuery query);

	/**
	 * 根据条件查询数量
	 */
	Integer findCountByParam(SysAccountQuery query);

	/**
	 * 分页查询
	 */
	PaginationResultVO<SysAccount> findListByPage(SysAccountQuery query);

	/**
	 * 新增
	 */
	Integer add(SysAccount bean);

	/**
	 * 批量新增
	 */
	Integer addBatch(List<SysAccount> listBean);

	/**
	 * 批量新增或修改
	 */
	Integer addOrUpdateBatch(List<SysAccount> listBean);

	/**
	 * 根据 UserId 查询
	 */
	SysAccount getSysAccountByUserId(Integer userId);

	/**
	 * 根据 UserId 更新
	 */
	Integer updateSysAccountByUserId(SysAccount bean, Integer userId);

	/**
	 * 根据 UserId 删除
	 */
	Integer deleteSysAccountByUserId(Integer userId);

	/**
	 * 根据 Phone 查询
	 */
	SysAccount getSysAccountByPhone(String phone);

	/**
	 * 根据 Phone 更新
	 */
	Integer updateSysAccountByPhone(SysAccount bean, String phone);

	/**
	 * 根据 Phone 删除
	 */
	Integer deleteSysAccountByPhone(String phone);

	/**
	 * 登陆验证
	 *
	 * @return
	 */
	SessionUserAdminDto login(String phone, String password);
}