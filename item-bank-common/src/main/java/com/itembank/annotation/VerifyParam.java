package com.itembank.annotation;

import com.itembank.enums.VerifyRegexEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: VerifyParam
 * @Author: KunSpireUp
 * @Date: 5/12/2024 3:29 PM
 */
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface VerifyParam {

	/**
	 * 校验正则表达
	 *
	 * @return
	 */
	VerifyRegexEnum regex() default VerifyRegexEnum.NO;

	/**
	 * 最小长度
	 *
	 * @return
	 */
	int min() default -1;

	/**
	 * 最大长度
	 *
	 * @return
	 */
	int max() default -1;

	boolean required() default false;
}
