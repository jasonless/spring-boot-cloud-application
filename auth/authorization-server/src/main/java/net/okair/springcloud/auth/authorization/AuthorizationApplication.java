package net.okair.springcloud.auth.authorization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author LiuShiZeng
 * @since 2020/1/7
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class AuthorizationApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthorizationApplication.class,args);
    }

}
