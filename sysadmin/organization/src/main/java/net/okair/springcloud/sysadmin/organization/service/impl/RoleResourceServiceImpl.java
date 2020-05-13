package net.okair.springcloud.sysadmin.organization.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.okair.springcloud.sysadmin.organization.entity.po.RoleResource;

import lombok.extern.slf4j.Slf4j;
import net.okair.springcloud.sysadmin.organization.repository.mapper.RoleResourceMapper;
import net.okair.springcloud.sysadmin.organization.service.IRoleResourceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author starrk
 * Created on 2019/12/30
 */
@Service
@Slf4j
public class RoleResourceServiceImpl extends ServiceImpl<RoleResourceMapper,RoleResource> implements IRoleResourceService {

    @Override
    @Transactional
    public boolean saveBatch(String roleId, Set<String> resourceIds) {
        if(CollectionUtils.isEmpty(resourceIds)){
            return false;
        }
        removeByRoleId(roleId);
        Set<RoleResource> userRoles = resourceIds.stream().map(resourceId -> new RoleResource(roleId,resourceId)).collect(Collectors.toSet());
        return saveBatch(userRoles);
    }

    @Override
    @Transactional
    public boolean removeByRoleId(String roleId) {
        QueryWrapper<RoleResource> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(RoleResource::getRoleId,roleId);
        return remove(queryWrapper);
    }

    @Override
    public Set<String> queryByRoleId(String roleId) {
        QueryWrapper<RoleResource> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id",roleId);
        List<RoleResource> userRoleList = list(queryWrapper);
        return userRoleList.stream().map(RoleResource::getResourceId).collect(Collectors.toSet());
    }

    @Override
    public List<RoleResource> queryByRoleIds(Set<String> roleIds) {
        QueryWrapper<RoleResource> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("role_id",roleIds);
        return this.list(queryWrapper);
    }
}
