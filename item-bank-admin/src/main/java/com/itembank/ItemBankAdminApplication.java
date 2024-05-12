package com.itembank;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Description: ItemBankAdminApplication
 * @Author: KunSpireUp
 * @Date: 10/5/2024 下午1:38
 */
@SpringBootApplication(scanBasePackages = {"com.itembank"})
@EnableTransactionManagement
@EnableScheduling
@EnableAsync
@MapperScan(basePackages = {"com.itembank.mappers"})
public class ItemBankAdminApplication {
	public static void main(String[] args) {
		SpringApplication.run(ItemBankAdminApplication.class, args);
	}
}
