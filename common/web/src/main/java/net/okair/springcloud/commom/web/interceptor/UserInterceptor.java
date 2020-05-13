package net.okair.springcloud.commom.web.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.okair.springcloud.common.core.utils.UserContextHolder;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 用户拦截器
 * @author starrk
 * Created on 2019/12/27
 */
@Slf4j
public class UserInterceptor implements HandlerInterceptor {

    /**
     * 服务间调用token用户信息，格式为json
     * {
     *     "user_name":"required"
     *     "自定义key":"value"
     * }
     */
    public static final String X_CLIENT_TOKEN_USER = "x-client-token-user";

    /**
     * 服务间调用的认证token
     */
    public static final String X_CLIENT_TOKEN = "x-client-token";


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //从网关获取并校验，通过校验就可信任x-client-token-user中的信息
        checkToken(request.getHeader(X_CLIENT_TOKEN));
        String userInfoString = StringUtils.defaultIfBlank(request.getHeader(X_CLIENT_TOKEN_USER),"{}");
        UserContextHolder.getInstance().setContext(new ObjectMapper().readValue(userInfoString,Map.class));

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserContextHolder.getInstance().clear();
    }

    private void checkToken(String token){
        //TODO 从网关获取并校验,通过校验就可信任x-client-token-user中的信息
        log.debug("TODO 校验token:{}",token);
    }


}
