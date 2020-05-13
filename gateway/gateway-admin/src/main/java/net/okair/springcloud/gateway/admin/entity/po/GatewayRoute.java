package net.okair.springcloud.gateway.admin.entity.po;

import lombok.*;
import net.okair.springcloud.commom.web.entity.po.BasePo;

/**
 * @author LiuShiZeng
 * @since 2020/1/14
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GatewayRoute extends BasePo {

    private String uri;
    private String routeId;
    private String predicates;
    private String filters;
    private String description;
    private Integer orders = 0;
    private String status = "Y";

}
