package net.okair.springcloud.gateway.admin.config;

import net.okair.springcloud.commom.web.redis.RedisConfig;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 * @author LiuShiZeng
 * @since 2020/1/15
 */
@Configuration
@EnableCaching
public class MyRedisConfig extends RedisConfig {
}
