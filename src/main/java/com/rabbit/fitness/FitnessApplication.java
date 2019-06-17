package com.rabbit.fitness;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan(value = {"com.rabbit.fitness.dao"})
@EnableTransactionManagement  //启动事务管理
@EnableSwagger2
public class FitnessApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(FitnessApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(FitnessApplication.class, args);
        System.out.println("启动完成");
    }
}
