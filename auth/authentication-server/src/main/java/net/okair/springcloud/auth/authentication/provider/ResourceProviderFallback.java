package net.okair.springcloud.auth.authentication.provider;

import lombok.extern.slf4j.Slf4j;
import net.okair.springcloud.common.core.entity.vo.Result;
import org.springframework.stereotype.Component;

/**
 * @author LiuShiZeng
 * @since 2020/1/7
 */
@Component
@Slf4j
public class ResourceProviderFallback implements ResourceProvider {
    @Override
    public Result resources() {
        log.error("认证服务启动时加载资源异常！未加载到资源");
        return Result.fail("认证服务启动时加载资源异常！未加载到资源");
    }

    @Override
    public Result resources(String username) {
        log.error("认证服务查询用户异常！查询用户资源为空！");
        return Result.fail("认证服务查询用户异常！查询用户资源为空！");
    }
}
