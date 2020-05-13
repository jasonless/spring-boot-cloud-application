package net.okair.springcloud.sysadmin.organization.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.okair.springcloud.sysadmin.organization.entity.param.PositionQueryParam;
import net.okair.springcloud.sysadmin.organization.entity.po.Position;

import net.okair.springcloud.sysadmin.organization.repository.mapper.PositionMapper;
import net.okair.springcloud.sysadmin.organization.service.IPositionService;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * @author starrk
 * Created on 2019/12/30
 */
public class PositionServiceImpl extends ServiceImpl<PositionMapper,Position> implements IPositionService {


    @Override
    public Position get(String id) {
        return this.getById(id);
    }

    @Override
    public boolean add(Position position) {
        return this.save(position);
    }

    @Override
    public List<Position> query(PositionQueryParam positionQueryParam) {
        QueryWrapper<Position> queryWrapper = positionQueryParam.build();
        queryWrapper.eq(StringUtils.isNotBlank(positionQueryParam.getName()),"name",positionQueryParam.getName());
        return this.list(queryWrapper);
    }

    @Override
    public boolean update(Position position) {
        return this.updateById(position);
    }

    @Override
    public boolean delete(String id) {
        return this.removeById(id);
    }
}
