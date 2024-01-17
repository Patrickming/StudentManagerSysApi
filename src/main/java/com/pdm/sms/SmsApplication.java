package com.pdm.sms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author xrm
 * @date 2024/1/17 16:00
 * @description 启动类
 **/
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.pdm.sms.dao")
public class SmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(SmsApplication.class, args);
    }
}
