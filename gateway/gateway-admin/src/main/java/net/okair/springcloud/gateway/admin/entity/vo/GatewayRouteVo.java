package net.okair.springcloud.gateway.admin.entity.vo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import net.okair.springcloud.gateway.admin.entity.po.FilterDefinition;
import net.okair.springcloud.gateway.admin.entity.po.GatewayRoute;
import net.okair.springcloud.gateway.admin.entity.po.PredicateDefinition;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author LiuShiZeng
 * @since 2020/1/14
 */
@Data
@Slf4j
public class GatewayRouteVo {

    private String id;
    private String routeId;
    private String description;
    private String status;
    private String uri;
    private Integer orders;
    private String createdBy;
    private Date createdTime;
    private String updatedBy;
    private Date updatedTime;
    private List<FilterDefinition> filters = new ArrayList<>();
    private List<PredicateDefinition> predicates = new ArrayList<>();

    public GatewayRouteVo(GatewayRoute gatewayRoute) {
        this.id = gatewayRoute.getId();
        this.routeId = gatewayRoute.getRouteId();
        this.uri = gatewayRoute.getUri();
        this.description = gatewayRoute.getDescription();
        this.status = gatewayRoute.getStatus();
        this.orders = gatewayRoute.getOrders();
        this.createdBy = gatewayRoute.getCreateBy();
        this.createdTime = gatewayRoute.getCreatedTime();
        this.updatedBy = gatewayRoute.getUpdateBy();
        this.updatedTime = gatewayRoute.getUpdatedTime();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            this.filters = objectMapper.readValue(gatewayRoute.getFilters(), new TypeReference<List<FilterDefinition>>() {
            });
            this.predicates = objectMapper.readValue(gatewayRoute.getPredicates(), new TypeReference<List<PredicateDefinition>>() {
            });
        } catch (IOException e) {
            log.error("网关路由对象转换失败", e);
        }
    }
}
