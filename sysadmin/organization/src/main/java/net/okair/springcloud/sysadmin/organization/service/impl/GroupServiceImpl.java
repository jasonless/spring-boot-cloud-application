package net.okair.springcloud.sysadmin.organization.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.okair.springcloud.sysadmin.organization.entity.param.GroupQueryParam;
import net.okair.springcloud.sysadmin.organization.entity.po.Group;
import net.okair.springcloud.sysadmin.organization.repository.mapper.GroupMapper;
import net.okair.springcloud.sysadmin.organization.service.IGroupService;


import java.util.List;

/**
 * @author starrk
 * Created on 2019/12/30
 */
public class GroupServiceImpl extends ServiceImpl<GroupMapper,Group> implements IGroupService {
    @Override
    public Group get(String id) {
       return  this.getById(id);
    }

    @Override
    public boolean add(Group group) {
        return this.save(group);
    }

    @Override
    public List<Group> query(GroupQueryParam groupQueryParam) {
        QueryWrapper<Group> queryWrapper = groupQueryParam.build();
        queryWrapper.eq("name",groupQueryParam.getName());
        return this.list(queryWrapper);
    }

    @Override
    public List<Group> queryByParentId(String id) {
        return this.list(new QueryWrapper<Group>().eq("parent_id",id));
    }

    @Override
    public boolean update(Group group) {
        return this.updateById(group);
    }

    @Override
    public boolean delete(String id) {
        return this.removeById(id);
    }
}
