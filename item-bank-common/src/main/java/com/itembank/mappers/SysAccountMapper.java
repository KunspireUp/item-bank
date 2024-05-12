package com.itembank.mappers;

import org.apache.ibatis.annotations.Param;

/**
 * @Description: 账号信息 Mapper
 * @Author: false
 * @Date: 2024/05/10 22:43:07
 */
public interface SysAccountMapper<T, P> extends BaseMapper {

	/**
 	 * 根据 UserId 查询
 	 */
	T selectByUserId(@Param("userId")Integer userId);

	/**
 	 * 根据 UserId 更新
 	 */
	Integer updateByUserId(@Param("bean") T t, @Param("userId")Integer userId); 

	/**
 	 * 根据 UserId 删除
 	 */
	Integer deleteByUserId(@Param("userId")Integer userId);

	/**
 	 * 根据 Phone 查询
 	 */
	T selectByPhone(@Param("phone")String phone);

	/**
 	 * 根据 Phone 更新
 	 */
	Integer updateByPhone(@Param("bean") T t, @Param("phone")String phone); 

	/**
 	 * 根据 Phone 删除
 	 */
	Integer deleteByPhone(@Param("phone")String phone);

}