package com.itembank.controller;


import java.util.List;
import com.itembank.service.SysAccountService;
import com.itembank.entity.vo.ResponseVO;
import com.itembank.entity.po.SysAccount;
import com.itembank.entity.query.SysAccountQuery;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;
/**
 * @Description: 账号信息 Controller
 * @Author: false
 * @Date: 2024/05/10 22:43:07
 */
@RestController
@RequestMapping("/sysAccount")
public class SysAccountController extends ABaseController{

	@Resource
	private SysAccountService sysAccountService;

	@RequestMapping("/loadDataList")
	public ResponseVO loadDataList(SysAccountQuery query) {
		return getSuccessResponseVO(sysAccountService.findListByPage(query));
	}

	/**
 	 * 新增
 	 */
	@RequestMapping("/add")
	public ResponseVO add(SysAccount bean) {
		Integer result = this.sysAccountService.add(bean);
		return getSuccessResponseVO(null);
	}

	/**
 	 * 批量新增
 	 */
	@RequestMapping("/addBatch")
	public ResponseVO addBatch(@RequestBody List<SysAccount> listBean) {
		this.sysAccountService.addBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/**
 	 * 批量新增或修改
 	 */
	@RequestMapping("/addOrUpdateBatch")
	public ResponseVO addOrUpdateBatch(@RequestBody List<SysAccount> listBean) {
		this.sysAccountService.addOrUpdateBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/**
 	 * 根据 UserId 查询
 	 */
	@RequestMapping("/getSysAccountByUserId")
	public ResponseVO getSysAccountByUserId(Integer userId) {
		return getSuccessResponseVO(sysAccountService.getSysAccountByUserId(userId));}

	/**
 	 * 根据 UserId 更新
 	 */
	@RequestMapping("/updateSysAccountByUserId")
	public ResponseVO updateSysAccountByUserId(SysAccount bean, Integer userId) {
		this.sysAccountService.updateSysAccountByUserId(bean, userId);
		return getSuccessResponseVO(null);
}

	/**
 	 * 根据 UserId 删除
 	 */
	@RequestMapping("/deleteSysAccountByUserId")
	public ResponseVO deleteSysAccountByUserId(Integer userId) {
		this.sysAccountService.deleteSysAccountByUserId(userId);
		return getSuccessResponseVO(null);
}

	/**
 	 * 根据 Phone 查询
 	 */
	@RequestMapping("/getSysAccountByPhone")
	public ResponseVO getSysAccountByPhone(String phone) {
		return getSuccessResponseVO(sysAccountService.getSysAccountByPhone(phone));}

	/**
 	 * 根据 Phone 更新
 	 */
	@RequestMapping("/updateSysAccountByPhone")
	public ResponseVO updateSysAccountByPhone(SysAccount bean, String phone) {
		this.sysAccountService.updateSysAccountByPhone(bean, phone);
		return getSuccessResponseVO(null);
}

	/**
 	 * 根据 Phone 删除
 	 */
	@RequestMapping("/deleteSysAccountByPhone")
	public ResponseVO deleteSysAccountByPhone(String phone) {
		this.sysAccountService.deleteSysAccountByPhone(phone);
		return getSuccessResponseVO(null);
}
}