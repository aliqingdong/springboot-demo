package com.liqingdong.core.frame.config;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * mybatis-plus 配置
 *
 * @author liqingdong
 * @since 2019/01/27 14:44
 */
@Configuration
@MapperScan("com.liqingdong.core.mapper")
public class MyBatisPlusConfig {

    // 分页插件
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    // 性能分析拦截器，不建议生产使用
    @Bean
    @Profile("dev")
    public PerformanceInterceptor performanceInterceptor(){
        return new PerformanceInterceptor();
    }

}
