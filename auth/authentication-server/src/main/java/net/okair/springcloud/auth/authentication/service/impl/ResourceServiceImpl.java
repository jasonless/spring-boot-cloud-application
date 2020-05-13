package net.okair.springcloud.auth.authentication.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.okair.springcloud.auth.authentication.provider.ResourceProvider;
import net.okair.springcloud.auth.authentication.service.IResourceService;
import net.okair.springcloud.auth.authentication.service.NewMvcRequestMatcher;
import net.okair.springcloud.sysadmin.organization.entity.po.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author LiuShiZeng
 * @since 2020/1/7
 */
@Service
@Slf4j
public class ResourceServiceImpl implements IResourceService {


    private final HandlerMappingIntrospector mvcHandlerMappingIntrospector;

    private final ResourceProvider resourceProvider;

    public ResourceServiceImpl(HandlerMappingIntrospector mvcHandlerMappingIntrospector, @Qualifier("organization") ResourceProvider resourceProvider) {
        this.mvcHandlerMappingIntrospector = mvcHandlerMappingIntrospector;
        this.resourceProvider = resourceProvider;
    }
    
    private Map<RequestMatcher,ConfigAttribute> resourceConfigAttributes;

    @Override
    public void saveResource(Resource resource) {
        resourceConfigAttributes.put(
                this.newMvcRequestMatcher(resource.getUrl(), resource.getMethod()),
                new SecurityConfig(resource.getCode())
        );
        //TODO DB数据库中的数据也需要增加
        log.info("resourceConfigAttributes size:{}", this.resourceConfigAttributes.size());
    }

    @Override
    public void removeResource(Resource resource) {
        resourceConfigAttributes.remove(this.newMvcRequestMatcher(resource.getUrl(), resource.getMethod()));
        //TODO DB数据库中的数据也需要删除
        log.info("resourceConfigAttributes size:{}", this.resourceConfigAttributes.size());
    }

    @Override
    public Map<RequestMatcher, ConfigAttribute> loadResource() {
        Set<Resource> resources = resourceProvider.resources().getData();
        this.resourceConfigAttributes = resources.stream()
                .collect(Collectors.toMap(
                        resource -> this.newMvcRequestMatcher(resource.getUrl(), resource.getMethod()),
                        resource -> new SecurityConfig(resource.getCode())
                ));
        log.debug("init resourceConfigAttributes:{}", this.resourceConfigAttributes);
        return this.resourceConfigAttributes;
    }

    @Override
    public ConfigAttribute findConfigAttributesByUrl(HttpServletRequest authRequest) {
        return this.resourceConfigAttributes.keySet().stream()
                .filter(requestMatcher -> requestMatcher.matches(authRequest))
                .map(requestMatcher -> this.resourceConfigAttributes.get(requestMatcher))
                .peek(urlConfigAttribute -> log.debug("url在资源池中配置：{}", urlConfigAttribute.getAttribute()))
                .findFirst()
                .orElse(new SecurityConfig("NONEXISTENT_URL"));
    }

    @Override
    public Set<Resource> queryByUsername(String username) {
        return resourceProvider.resources(username).getData();
    }

    /**
     * 创建RequestMatcher
     *
     * @param url
     * @param method
     * @return
     */
    private MvcRequestMatcher newMvcRequestMatcher(String url, String method) {
        return new NewMvcRequestMatcher(mvcHandlerMappingIntrospector, url, method);
    }
    
}
