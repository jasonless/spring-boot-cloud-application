package net.okair.springcloud.auth.authorization.service.impl;


import net.okair.springcloud.auth.authorization.provider.OrganizationProvider;
import net.okair.springcloud.auth.authorization.service.IRoleService;
import net.okair.springcloud.sysadmin.organization.entity.po.Role;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RoleServiceImpl implements IRoleService {


    private OrganizationProvider organizationProvider;

    public RoleServiceImpl(@Qualifier("organization") OrganizationProvider organizationProvider) {
        this.organizationProvider = organizationProvider;
    }

    @Override
    public Set<Role> queryUserRolesByUserId(String userId) {
        return organizationProvider.queryRolesByUserId(userId).getData();
    }

}
