package net.okair.springcloud.sysadmin.organization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author LiuShiZeng
 * @since 2020/1/6
 */
@SpringBootApplication
@EnableDiscoveryClient
public class OrganizationApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrganizationApplication.class,args);
    }
}
