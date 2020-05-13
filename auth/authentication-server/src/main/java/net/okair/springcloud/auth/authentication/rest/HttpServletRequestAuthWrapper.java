package net.okair.springcloud.auth.authentication.rest;

import lombok.Getter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @author LiuShiZeng
 * @since 2020/1/7
 */
@Getter
public class HttpServletRequestAuthWrapper extends HttpServletRequestWrapper {

    private String url;
    private String method;

    public HttpServletRequestAuthWrapper(HttpServletRequest request,String url,String method) {
        super(request);
        this.url = url;
        this.method = method;
    }
}
