package net.okair.springcloud.auth.authorization.service;

import net.okair.springcloud.sysadmin.organization.entity.po.Role;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author LiuShiZeng
 */
public interface IRoleService {

    Set<Role> queryUserRolesByUserId(String userId);

}
