package net.okair.springcloud.gateway.web.routes;

import lombok.extern.slf4j.Slf4j;
import net.okair.springcloud.gateway.web.service.IRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author LiuShiZeng
 * @since 2020/1/14
 */
@Component
@Slf4j
public class RedisRouteDefinitionRepository implements RouteDefinitionRepository {

    @Autowired
    private IRouteService routeService;

    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        return routeService.getRouteDefinitions();
    }

    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        return routeService.save(route);
    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return routeService.delete(routeId);
    }
}
