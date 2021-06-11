package com.scau;


import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
@ConditionalOnExpression("${Swagger.enable:true}")
public class SwaggerConfig {

    @Bean
    public Docket swaggerSpringMvcPlugin() {
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("springboot接口文档")
                .description("接口管理")
                .version("1.1")
                .build();


        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)                    // 需要上面定义的ApiInfo，信息显示到页面上
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.scau"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }

}
