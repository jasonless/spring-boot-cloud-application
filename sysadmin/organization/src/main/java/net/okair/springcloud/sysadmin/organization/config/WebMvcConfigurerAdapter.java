package net.okair.springcloud.sysadmin.organization.config;


import net.okair.springcloud.commom.web.interceptor.UserInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author starrk
 * Created on 2019/12/31
 */
@Configuration
public class WebMvcConfigurerAdapter implements WebMvcConfigurer {

    @Bean
    public HandlerInterceptor userInterceptor(){
        return new UserInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userInterceptor());
    }
}
