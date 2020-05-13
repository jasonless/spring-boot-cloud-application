package net.okair.springcloud.gateway.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author LiuShiZeng
 * @since 2020/1/14
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GatewayAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayAdminApplication.class,args);
    }

}
