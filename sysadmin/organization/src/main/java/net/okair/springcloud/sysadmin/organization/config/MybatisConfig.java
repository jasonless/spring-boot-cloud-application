package net.okair.springcloud.sysadmin.organization.config;

import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author starrk
 * Created on 2019/12/31
 */
@Configuration
@EnableTransactionManagement
public class MybatisConfig {
    /**
     * 初始化Mybatis审计字段自动赋值的interceptor
     * @return
     */
    @Bean
    public ISqlInjector sqlInjector(){
        return new DefaultSqlInjector();
    }

    /**
     * 分页插件
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }

}
