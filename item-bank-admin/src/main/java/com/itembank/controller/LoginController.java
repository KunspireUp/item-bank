package com.itembank.controller;

import com.itembank.annotation.GlobalInterceptor;
import com.itembank.annotation.VerifyParam;
import com.itembank.entity.constants.Constants;
import com.itembank.entity.dto.CreateImageCode;
import com.itembank.entity.dto.SessionUserAdminDto;
import com.itembank.entity.vo.ResponseVO;
import com.itembank.enums.VerifyRegexEnum;
import com.itembank.exception.BusinessException;
import com.itembank.service.SysAccountService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Description: LoginController
 * @Author: KunSpireUp
 * @Date: 11/5/2024 下午12:54
 */
@RestController
public class LoginController extends ABaseController {

	@Resource
	private SysAccountService sysAccountService;

	@RequestMapping("/checkCode")
	public void checkCode(HttpServletResponse response, HttpSession session) throws IOException {
		CreateImageCode verifyCode = new CreateImageCode();
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-store");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		String code = verifyCode.getCode();
		session.setAttribute(Constants.CHECK_CODE_KEY, code);
		verifyCode.write(response.getOutputStream());
	}

	@GlobalInterceptor
	@RequestMapping("/login")
	public ResponseVO login(HttpSession session,
							@VerifyParam(regex = VerifyRegexEnum.PHONE) String phone,
							@VerifyParam(required = true) String password,
							@VerifyParam(required = true) String checkCode) {
		/**
		 * 之前的所有参数判断不正确已经经过 AOP OperationAspect 拦截了，校验正确直接判断验证码，使用 APO 实现了结解耦
		 */
		if (!checkCode.equalsIgnoreCase((String) session.getAttribute(Constants.CHECK_CODE_KEY))) {
			throw new BusinessException("图片验证码错误");
		}
		SessionUserAdminDto adminDto = sysAccountService.login(phone, password);
		session.setAttribute(Constants.SESSION_KEY, adminDto);
		return getSuccessResponseVO(null);
	}

}
