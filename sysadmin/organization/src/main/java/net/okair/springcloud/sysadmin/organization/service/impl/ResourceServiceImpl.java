package net.okair.springcloud.sysadmin.organization.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.okair.springcloud.sysadmin.organization.entity.param.ResourceQueryParam;
import net.okair.springcloud.sysadmin.organization.entity.po.Resource;
import net.okair.springcloud.sysadmin.organization.entity.po.Role;
import net.okair.springcloud.sysadmin.organization.entity.po.RoleResource;
import net.okair.springcloud.sysadmin.organization.entity.po.User;

import lombok.extern.slf4j.Slf4j;
import net.okair.springcloud.sysadmin.organization.repository.mapper.ResourceMapper;
import net.okair.springcloud.sysadmin.organization.service.IResourceService;
import net.okair.springcloud.sysadmin.organization.service.IRoleResourceService;
import net.okair.springcloud.sysadmin.organization.service.IRoleService;
import net.okair.springcloud.sysadmin.organization.service.IUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author starrk
 * Created on 2019/12/30
 */
@Service
@Slf4j
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper,Resource> implements IResourceService {

    private final IRoleResourceService roleResourceService;

    private final IRoleService roleService;

    private final IUserService userService;

    @Autowired
    public ResourceServiceImpl(IRoleResourceService roleResourceService, IRoleService roleService, IUserService userService) {
        this.roleResourceService = roleResourceService;
        this.roleService = roleService;
        this.userService = userService;
    }


    @Override
    public Resource get(String id) {
        return null;
    }

    @Override
    public boolean add(Resource resource) {
        //TODO 使用bus，将消息发送给gateway路由
        //eventSender
        return this.save(resource);
    }

    @Override
    public IPage<Resource> query(Page page, ResourceQueryParam resourceQueryParam) {
        QueryWrapper queryWrapper = resourceQueryParam.build();
        queryWrapper.eq(StringUtils.isNotBlank(resourceQueryParam.getName()),"name",resourceQueryParam.getName());
        queryWrapper.eq(StringUtils.isNotBlank(resourceQueryParam.getType()),"type",resourceQueryParam.getType());
        queryWrapper.eq(StringUtils.isNotBlank(resourceQueryParam.getUrl()),"url",resourceQueryParam.getUrl());
        queryWrapper.eq(StringUtils.isNotBlank(resourceQueryParam.getMethod()),"method",resourceQueryParam.getMethod());
        return this.page(page,queryWrapper);
    }

    @Override
    public List<Resource> getAll() {
        return this.list();
    }

    @Override
    public List<Resource> query(String username) {
        //根据用户名查询到用户所拥有的角色
        User user = userService.getByUniqueId(username);
        //提取用户所拥有角色id列表
        List<Role> roles = roleService.query(user.getId());
        //根据角色列表查询到角色的资源关联关系
        Set<String> roleIds = roles.stream().map(role -> role.getId()).collect(Collectors.toSet());
        //根据资源列表查询出所有资源对象
        List<RoleResource> roleResources = roleResourceService.queryByRoleIds(roleIds);
        //根据resourceId列表查询出resource对象
        return this.listByIds(roleResources);

    }

    @Override
    public boolean update(Resource resource) {
        return this.updateById(resource);
    }

    @Override
    public boolean delete(String id) {
        return this.removeById(id);
    }
}
