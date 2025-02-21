package com.example.demo.config;

import com.example.demo.intercetor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    //重写addInterceptors方法
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加拦截器，作用范围是/user下的所有路径
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/user/**");

    }

}
