package com.scau;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@MapperScan("com.scau.mapper")
public class OnlineSurveyApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineSurveyApplication.class, args);
    }

}
