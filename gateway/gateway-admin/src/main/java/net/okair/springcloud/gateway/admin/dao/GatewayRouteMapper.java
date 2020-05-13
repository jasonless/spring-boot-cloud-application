package net.okair.springcloud.gateway.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.okair.springcloud.gateway.admin.entity.po.GatewayRoute;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author LiuShiZeng
 * @since 2020/1/14
 */
@Mapper
@Repository
public interface GatewayRouteMapper extends BaseMapper<GatewayRoute> {
}
