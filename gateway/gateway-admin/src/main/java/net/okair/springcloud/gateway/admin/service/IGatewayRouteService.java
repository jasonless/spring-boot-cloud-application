package net.okair.springcloud.gateway.admin.service;

import net.okair.springcloud.gateway.admin.entity.param.GatewayRouteQueryParam;
import net.okair.springcloud.gateway.admin.entity.po.GatewayRoute;
import net.okair.springcloud.gateway.admin.entity.vo.GatewayRouteVo;

import java.util.List;

/**
 * @author LiuShiZeng
 * @since 2020/1/14
 */
public interface IGatewayRouteService {

    /**
     * 获取网关路由
     *
     * @param id
     * @return
     */
    GatewayRoute get(String id);

    /**
     * 新增网关路由
     *
     * @param gatewayRoute
     * @return
     */
    boolean add(GatewayRoute gatewayRoute);

    /**
     * 查询网关路由
     *
     * @return
     */
    List<GatewayRouteVo> query(GatewayRouteQueryParam gatewayRouteQueryParam);

    List<GatewayRoute> getAll();

    /**
     * 更新网关路由信息
     *
     * @param gatewayRoute
     */
    boolean update(GatewayRoute gatewayRoute);

    /**
     * 根据id删除网关路由
     *
     * @param id
     */
    boolean delete(String id);

    /**
     * 重新加载网关路由配置到redis
     *
     * @return 成功返回true
     */
    boolean overload();

}
