package com.example.backend.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatisPlusConfig {
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {

        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 创建 MybatisPlusInterceptor 对象（MyBatis-Plus 的核心拦截器）

        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor(DbType.MYSQL);
        // 创建 PaginationInnerInterceptor 对象（分页插件），并指定数据库类型为 MySQL

        interceptor.addInnerInterceptor(paginationInnerInterceptor);
        // 将分页插件添加到 MybatisPlusInterceptor 中，启用分页功能

        return interceptor; // 返回配置好的拦截器对象
    }
}
