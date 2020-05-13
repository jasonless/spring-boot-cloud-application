package net.okair.springcloud.auth.authentication.service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author LiuShiZeng
 * @since 2020/1/7
 */
public interface IAuthenticationService {
    /**
     * 校验权限
     * @param authRequest
     * @return
     */
    boolean decode(HttpServletRequest authRequest);

}
