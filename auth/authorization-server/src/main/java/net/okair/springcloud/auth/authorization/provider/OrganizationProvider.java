package net.okair.springcloud.auth.authorization.provider;

import net.okair.springcloud.common.core.entity.vo.Result;
import net.okair.springcloud.sysadmin.organization.entity.po.Role;
import net.okair.springcloud.sysadmin.organization.entity.po.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

/**
 * @author LiuShiZeng
 * @since 2020/1/6
 */
@FeignClient(name = "organization", fallback = OrganizationProviderFallback.class)
public interface OrganizationProvider {

    @GetMapping(value = "/user")
    Result<User> getUserByUniqueId(@RequestParam("uniqueId") String uniqueId);

    @GetMapping(value = "/role/user/{userId}")
    Result<Set<Role>> queryRolesByUserId(@PathVariable("userId") String userId);
}
