package com.cbwleft.sms.mgr;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.cbwleft.sms", "com.stylefeng.guns"})
@MapperScan(basePackages = "com.cbwleft.sms.dao")
public class SmsMgrApplication{

    protected final static Logger logger = LoggerFactory.getLogger(SmsMgrApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SmsMgrApplication.class, args);
    }
}
