package net.okair.springcloud.gateway.admin.entity.po;

import lombok.*;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author LiuShiZeng
 * @since 2020/1/14
 */
@EqualsAndHashCode
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PredicateDefinition {

    private String name;
    private Map<String,String> args = new LinkedHashMap<>();

}
