package net.okair.springcloud.sysadmin.organization.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.okair.springcloud.sysadmin.organization.entity.param.MenuQueryParam;
import net.okair.springcloud.sysadmin.organization.entity.po.Menu;
import net.okair.springcloud.sysadmin.organization.repository.mapper.MenuMapper;
import net.okair.springcloud.sysadmin.organization.service.IMenuService;


import java.util.List;

/**
 * @author starrk
 * Created on 2019/12/30
 */
public class MenuServiceImpl extends ServiceImpl<MenuMapper,Menu> implements IMenuService {
    @Override
    public Menu get(String id) {
        return this.getById(id);
    }

    @Override
    public boolean add(Menu menu) {
        return this.save(menu);
    }

    @Override
    public List<Menu> query(MenuQueryParam menuQueryParam) {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(null != menuQueryParam.getName(),"name",menuQueryParam.getName());
        return this.list(queryWrapper);
    }

    @Override
    public List<Menu> queryByParentId(String id) {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id",id);
        return this.list(queryWrapper);
    }

    @Override
    public boolean update(Menu menu) {
        return this.updateById(menu);
    }

    @Override
    public boolean delete(String id) {
        return this.removeById(id);
    }
}
