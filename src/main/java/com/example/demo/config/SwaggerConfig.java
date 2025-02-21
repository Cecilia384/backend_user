package com.example.demo.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 告诉 Spring 容器这是一个配置类
public class SwaggerConfig {

    // 配置 OpenAPI 的基本信息
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("SpringBoot整合OpenAPI") // 标题
                        .description("SpringBoot整合OpenAPI的详细信息") // 描述
                        .version("1.0")); // 版本号
    }
}

//package com.example.demo.config;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//@Configuration  //告诉Spring容器这是一个配置类
//@EnableSwagger2 //开启Swagger2
//public class SwaggerConfig {
//    //配置Swagger2相关的bean
//    @Bean
//    public Docket createRestApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                .select()//选择哪些路径和API会生成文档
//                .apis(RequestHandlerSelectors.basePackage("com"))//com下的所有API都交给Swagger代理
//                .paths(PathSelectors.any())
//                .build();
//    }
//    //API文档的基本信息
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("SpringBoot整合Swagger2") //标题
//                .description("SpringBoot整合Swagger2的详细信息")//描述
////                .termsOfServiceUrl("http://www.baidu.com")//服务地址
//                .version("1.0")//版本号
//                .build();
//    }
//}
///**
// * 启动项目访问http://127.0.0.1:8080/swagger-ui.html
// * 即可打开自动生成的可视化测试页面
// */

