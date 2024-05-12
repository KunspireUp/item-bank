package com.itembank.aspect;

import com.itembank.annotation.GlobalInterceptor;
import com.itembank.annotation.VerifyParam;
import com.itembank.enums.ResponseCodeEnum;
import com.itembank.exception.BusinessException;
import com.itembank.utils.StringTools;
import com.itembank.utils.VerifyUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @Description: APO 操作切面
 * @Author: KunSpireUp
 * @Date: 5/12/2024 1:10 AM
 */
@Aspect
@Component("operationAspect")
public class OperationAspect {

	private static final String[] BASE_TYPE_ARRAY = new String[]{"java.lang.String", "java.lang.Integer", "java.lang.Long"};

	private final Logger logger = LoggerFactory.getLogger(OperationAspect.class);

	@Pointcut("@annotation(com.itembank.annotation.GlobalInterceptor)")
	public void pointCut() {
	}


	@Before("pointCut()")
	public void interceptorDo(JoinPoint point) {
		//logger.info(point.getArgs().toString());
		//System.out.println("切面");
		Object[] arguments = point.getArgs();
		Method method = ((MethodSignature) point.getSignature()).getMethod();
		logger.info("方法名:{}", method.getName());

		GlobalInterceptor interceptor = method.getAnnotation(GlobalInterceptor.class);
		if (interceptor == null) {
			return;
		}

		/**
		 * 校验参数
		 */
		if (interceptor.checkParams()) {
			validateParams(method, arguments);
		}
	}

	/**
	 * 参数校验
	 *
	 * @param method
	 * @param arguments
	 */
	private void validateParams(Method method, Object[] arguments) {
		Parameter[] parameters = method.getParameters();
		for (int i = 0; i < parameters.length; i++) {
			Parameter parameter = parameters[i];
			Object value = arguments[i];
			VerifyParam verifyParam = parameter.getAnnotation(VerifyParam.class);
			if (verifyParam == null) {
				continue;
			}
			String paramTypeName = parameter.getParameterizedType().getTypeName();
			/**
			 * 基本数据类型
			 */
			if (ArrayUtils.contains(BASE_TYPE_ARRAY, paramTypeName)) {
				checkValue(value, verifyParam);
			} else {
				checkObjValue(parameter, value);
			}
		}

	}

	private void checkObjValue(Parameter parameter, Object value) {
		try {
			/**
			 * 反射
			 */
			String typeName = parameter.getParameterizedType().getTypeName();
			Class classz = Class.forName(typeName);
			Field[] fields = classz.getDeclaredFields();
			for (Field field : fields) {
				VerifyParam fieldVerifyParam = field.getAnnotation(VerifyParam.class);
				if (fieldVerifyParam == null) {
					continue;
				}
				/**
				 * 默认情况下，Java的反射机制不能访问一个类的私有字段，可以通过调用setAccessible(true)方法来允许访问私有字段。
				 */
				field.setAccessible(true);
				Object resultValue = field.get(value);
				checkValue(resultValue, fieldVerifyParam);
			}
		} catch (Exception e) {
			logger.info("校验参数失败");
			throw new BusinessException(ResponseCodeEnum.CODE_600);
		}
	}

	private void checkValue(Object value, VerifyParam verifyParam) {
		boolean isEmpty = value == null || StringTools.isEmpty(value.toString());
		Integer length = value == null ? 0 : value.toString().length();

		/**
		 * 校验空
		 */
		if (isEmpty && verifyParam.required()) {
			throw new BusinessException(ResponseCodeEnum.CODE_600);
		}

		/**
		 * 校验长度
		 */
		if (!isEmpty && verifyParam.min() != -1 && verifyParam.max() < length || verifyParam.min() != -1 && verifyParam.min() > length) {
			throw new BusinessException(ResponseCodeEnum.CODE_600);
		}

		/**
		 * 校验正则
		 */
		if (!isEmpty && !StringTools.isEmpty(verifyParam.regex().getRegex()) && !VerifyUtils.verify(verifyParam.regex().getRegex(), String.valueOf(value))) {
			throw new BusinessException(ResponseCodeEnum.CODE_600);
		}


	}
}
