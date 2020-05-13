package net.okair.springcloud.gateway.admin.entity.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.okair.springcloud.commom.web.entity.param.BaseParam;
import net.okair.springcloud.gateway.admin.entity.po.GatewayRoute;

/**
 * @author LiuShiZeng
 * @since 2020/1/14
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GatewayRouteQueryParam extends BaseParam<GatewayRoute> {

    private String uri;

}
