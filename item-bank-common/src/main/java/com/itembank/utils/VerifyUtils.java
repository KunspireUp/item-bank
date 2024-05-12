package com.itembank.utils;

import com.itembank.entity.constants.Constants;
import com.itembank.enums.VerifyRegexEnum;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: VerifyUtils
 * @Author: KunSpireUp
 * @Date: 5/12/2024 4:36 PM
 */
public class VerifyUtils {

	public static boolean verify(String regex, String value) {
		if (StringTools.isEmpty(value)) {
			return false;
		}
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(value);
		return matcher.matches();
	}

	public static boolean verify(VerifyRegexEnum regex, String value) {
		return verify(regex.getRegex(), value);
	}

	public static boolean checkAccount(String account) {
		/**
		 * 注册时候限制长度8-20
		 */
		if (StringTools.isEmpty(account) || account.length() < Constants.LENGTH_8 || account.length() > Constants.LENGTH_20) {
			return false;
		}
		return verify(VerifyRegexEnum.ACCOUNT, account);
	}

	public static boolean checkPassword(String password) {
		return verify(VerifyRegexEnum.PASSWORD, password);
	}
}
