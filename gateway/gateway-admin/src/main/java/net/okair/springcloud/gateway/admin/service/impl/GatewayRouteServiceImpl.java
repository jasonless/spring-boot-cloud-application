package net.okair.springcloud.gateway.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.okair.springcloud.gateway.admin.dao.GatewayRouteMapper;
import net.okair.springcloud.gateway.admin.entity.param.GatewayRouteQueryParam;
import net.okair.springcloud.gateway.admin.entity.po.GatewayRoute;
import net.okair.springcloud.gateway.admin.entity.vo.GatewayRouteVo;
import net.okair.springcloud.gateway.admin.service.IGatewayRouteService;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author LiuShiZeng
 * @since 2020/1/14
 */
@Service
public class GatewayRouteServiceImpl extends ServiceImpl<GatewayRouteMapper, GatewayRoute> implements IGatewayRouteService {

    private static final String GATEWAY_ROUTES = "gateway_routes::";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public GatewayRoute get(String id) {
        return this.getById(id);
    }

    @Override
    public boolean add(GatewayRoute gatewayRoute) {
        stringRedisTemplate.opsForValue().set(GATEWAY_ROUTES+gatewayRoute.getId(),toJson(new GatewayRouteVo(gatewayRoute)));
        return this.save(gatewayRoute);
    }

    @Override
    public List<GatewayRouteVo> query(GatewayRouteQueryParam gatewayRouteQueryParam) {
        QueryWrapper<GatewayRoute> queryWrapper = gatewayRouteQueryParam.build();
        queryWrapper.eq(StringUtils.isNotBlank(gatewayRouteQueryParam.getUri()),"uri",gatewayRouteQueryParam.getUri());

        return this.list(queryWrapper).stream().map(GatewayRouteVo::new).collect(Collectors.toList());
    }

    @Override
    public List<GatewayRoute> getAll() {
        return this.list();
    }

    @Override
    public boolean update(GatewayRoute gatewayRoute) {
        stringRedisTemplate.delete(GATEWAY_ROUTES + gatewayRoute.getId());
        stringRedisTemplate.opsForValue().set(GATEWAY_ROUTES, toJson(new GatewayRouteVo(get(gatewayRoute.getId()))));
        return this.updateById(gatewayRoute);
    }

    @Override
    public boolean delete(String id) {
        stringRedisTemplate.delete(GATEWAY_ROUTES + id);
        return this.removeById(id);
    }

    @Override
    public boolean overload() {
        List<GatewayRoute> gatewayRoutes = this.list();
        ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
        gatewayRoutes.forEach(gatewayRoute ->
                opsForValue.set(GATEWAY_ROUTES + gatewayRoute.getId(), toJson(new GatewayRouteVo(gatewayRoute)))
        );
        return true;
    }

    /**
     * GatewayRoute转换为json
     *
     * @param gatewayRouteVo redis需要的vo
     * @return json string
     */
    private String toJson(GatewayRouteVo gatewayRouteVo) {
        String routeDefinitionJson = Strings.EMPTY;
        try {
            routeDefinitionJson = new ObjectMapper().writeValueAsString(gatewayRouteVo);
        } catch (JsonProcessingException e) {
            log.error("网关对象序列化为json String", e);
        }
        return routeDefinitionJson;
    }

}
