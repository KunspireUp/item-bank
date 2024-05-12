package com.itembank.service.impl;


import com.itembank.entity.dto.SessionUserAdminDto;
import com.itembank.entity.po.SysAccount;
import com.itembank.entity.query.SimplePage;
import com.itembank.entity.query.SysAccountQuery;
import com.itembank.entity.vo.PaginationResultVO;
import com.itembank.enums.PageSize;
import com.itembank.enums.SysAccountStatusEnum;
import com.itembank.exception.BusinessException;
import com.itembank.mappers.SysAccountMapper;
import com.itembank.service.SysAccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 账号信息 业务接口实现
 * @Author: false
 * @Date: 2024/05/10 22:43:07
 */
@Service("SysAccountMapper")
public class SysAccountServiceImpl implements SysAccountService {

	@Resource
	private SysAccountMapper<SysAccount, SysAccountQuery> sysAccountMapper;

	/**
	 * 根据条件查询列表
	 */
	public List<SysAccount> findListByParam(SysAccountQuery query) {
		return this.sysAccountMapper.selectList(query);
	}

	/**
	 * 根据条件查询数量
	 */
	public Integer findCountByParam(SysAccountQuery query) {
		return this.sysAccountMapper.selectCount(query);
	}

	/**
	 * 分页查询
	 */
	public PaginationResultVO<SysAccount> findListByPage(SysAccountQuery query) {
		Integer count = this.findCountByParam(query);
		System.out.println(count);
		Integer pageSize = query.getPageSize() == null ? PageSize.SIZE15.getSize() : query.getPageSize();
		SimplePage page = new SimplePage(query.getPageNo(), count, pageSize);
		query.setSimplePage(page);
		System.out.println(page);
		List<SysAccount> list = this.findListByParam(query);
		PaginationResultVO<SysAccount> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
		return result;
	}

	/**
	 * 新增
	 */
	public Integer add(SysAccount bean) {
		return this.sysAccountMapper.insert(bean);
	}

	/**
	 * 批量新增
	 */
	public Integer addBatch(List<SysAccount> listBean) {
		if ((listBean == null) || listBean.isEmpty()) {
			return 0;
		}
		return this.sysAccountMapper.insertBatch(listBean);
	}

	/**
	 * 批量新增或修改
	 */
	public Integer addOrUpdateBatch(List<SysAccount> listBean) {
		if ((listBean == null) || listBean.isEmpty()) {
			return 0;
		}
		return this.sysAccountMapper.insertOrUpdateBatch(listBean);
	}

	/**
	 * 根据 UserId 查询
	 */
	public SysAccount getSysAccountByUserId(Integer userId) {
		return this.sysAccountMapper.selectByUserId(userId);
	}

	/**
	 * 根据 UserId 更新
	 */
	public Integer updateSysAccountByUserId(SysAccount bean, Integer userId) {
		return this.sysAccountMapper.updateByUserId(bean, userId);
	}

	/**
	 * 根据 UserId 删除
	 */
	public Integer deleteSysAccountByUserId(Integer userId) {
		return this.sysAccountMapper.deleteByUserId(userId);
	}

	/**
	 * 根据 Phone 查询
	 */
	public SysAccount getSysAccountByPhone(String phone) {
		return this.sysAccountMapper.selectByPhone(phone);
	}

	/**
	 * 根据 Phone 更新
	 */
	public Integer updateSysAccountByPhone(SysAccount bean, String phone) {
		return this.sysAccountMapper.updateByPhone(bean, phone);
	}

	/**
	 * 根据 Phone 删除
	 */
	public Integer deleteSysAccountByPhone(String phone) {
		return this.sysAccountMapper.deleteByPhone(phone);
	}

	/**
	 * 登陆验证
	 *
	 * @param phone
	 * @param password
	 * @return
	 */
	@Override
	public SessionUserAdminDto login(String phone, String password) {
		SysAccount sysAccount = sysAccountMapper.selectByPhone(phone);
		if (sysAccount == null) {
			throw new BusinessException("账号或密码错误");
		}
		if (SysAccountStatusEnum.DISABLE.getStatus().equals(sysAccount.getStatus())) {
			throw new BusinessException("账号已禁用");
		}
		if (!sysAccount.getPassword().equals(password)) {
			throw new BusinessException("账号或者密码错误");
		}
		SessionUserAdminDto adminDto = new SessionUserAdminDto();
		/**
		 * 读取配置文件，将用户设置为超级管理员
		 */
		adminDto.setSuperAdmin(true);
		adminDto.setUserId(sysAccount.getUserId());
		adminDto.setUserName(sysAccount.getUserName());
		return adminDto;
	}
}