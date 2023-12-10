package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, RedisAutoConfiguration.class},
        scanBasePackages = "com.example.demo.test")

public class DemoApplication {


    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);
    }


}