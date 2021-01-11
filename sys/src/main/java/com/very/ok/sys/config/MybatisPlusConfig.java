package com.very.ok.sys.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
@Configuration
@MapperScan("com.ds.yuan.repository.sys.mapper")
public class MybatisPlusConfig {
    
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
    	MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
    	interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return interceptor;
    }
}
