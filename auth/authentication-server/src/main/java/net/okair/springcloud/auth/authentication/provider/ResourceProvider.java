package net.okair.springcloud.auth.authentication.provider;

import net.okair.springcloud.common.core.entity.vo.Result;
import net.okair.springcloud.sysadmin.organization.entity.po.Resource;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;

/**
 * 资源接口调用
 * @author LiuShiZeng
 * @since 2020/1/7
 */
@FeignClient(name = "organization",fallback = ResourceProviderFallback.class)
public interface ResourceProvider {

    @GetMapping(value = "/resource/all")
    Result<Set<Resource>> resources();

    @GetMapping(value = "/resource/user/{username}")
    Result<Set<Resource>> resources(@PathVariable("username") String username);

}
