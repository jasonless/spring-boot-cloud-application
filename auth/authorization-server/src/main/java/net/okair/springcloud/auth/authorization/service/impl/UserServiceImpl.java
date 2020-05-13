package net.okair.springcloud.auth.authorization.service.impl;


import net.okair.springcloud.auth.authorization.provider.OrganizationProvider;
import net.okair.springcloud.auth.authorization.service.IUserService;
import net.okair.springcloud.sysadmin.organization.entity.po.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {


    private final OrganizationProvider organizationProvider;

    public UserServiceImpl(@Qualifier("organization") OrganizationProvider organizationProvider) {
        this.organizationProvider = organizationProvider;
    }

    @Override
    public User getByUniqueId(String uniqueId) {
        return organizationProvider.getUserByUniqueId(uniqueId).getData();
    }
}
