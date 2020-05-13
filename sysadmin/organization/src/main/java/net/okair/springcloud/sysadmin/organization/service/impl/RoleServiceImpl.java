package net.okair.springcloud.sysadmin.organization.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.okair.springcloud.sysadmin.organization.entity.param.RoleQueryParam;
import net.okair.springcloud.sysadmin.organization.entity.po.Role;

import lombok.extern.slf4j.Slf4j;
import net.okair.springcloud.sysadmin.organization.exception.RoleNotFoundException;
import net.okair.springcloud.sysadmin.organization.repository.mapper.RoleMapper;
import net.okair.springcloud.sysadmin.organization.service.IRoleResourceService;
import net.okair.springcloud.sysadmin.organization.service.IRoleService;
import net.okair.springcloud.sysadmin.organization.service.IUserRoleService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author starrk
 * Created on 2019/12/30
 */
@Service
@Slf4j
public class RoleServiceImpl extends ServiceImpl<RoleMapper,Role> implements IRoleService {

    private final IUserRoleService userRoleService;

    private final IRoleResourceService roleResourceService;

    public RoleServiceImpl(IUserRoleService userRoleService, IRoleResourceService roleResourceService) {
        this.userRoleService = userRoleService;
        this.roleResourceService = roleResourceService;
    }

    @Override
    public Role get(String id) {
        Role role = this.getById(id);
        if(Objects.isNull(role)){
            throw new RoleNotFoundException("权限没有找到，id:"+id);
        }
        role.setResourceIds(roleResourceService.queryByRoleId(id));
        return role;
    }

    @Override
    public List<Role> getAll() {
        return this.list();
    }

    @Override
    public boolean add(Role role) {
        boolean isSuccess = this.save(role);
        roleResourceService.saveBatch(role.getId(),role.getResourceIds());
        return isSuccess;
    }

    @Override
    public IPage<Role> query(Page page, RoleQueryParam roleQueryParam) {
        QueryWrapper<Role> queryWrapper = roleQueryParam.build();
        queryWrapper.eq(StringUtils.isNotBlank(roleQueryParam.getName()),"name",roleQueryParam.getName());
        queryWrapper.eq(StringUtils.isNotBlank(roleQueryParam.getCode()),"code",roleQueryParam.getCode());
        return this.page(page,queryWrapper);
    }

    @Override
    public List<Role> query(String userId) {
        Set<String> roleIds = userRoleService.queryByUserId(userId);
        return this.listByIds(roleIds);
    }

    @Override
    public boolean update(Role role) {
        boolean isSuccess = this.updateById(role);
        roleResourceService.saveBatch(role.getId(),role.getResourceIds());
        return isSuccess;
    }

    @Override
    @Transactional
    public boolean delete(String id) {
        roleResourceService.removeByRoleId(id);
        return this.removeById(id);
    }
}
