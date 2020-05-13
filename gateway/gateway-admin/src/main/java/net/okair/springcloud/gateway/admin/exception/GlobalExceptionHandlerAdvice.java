package net.okair.springcloud.gateway.admin.exception;

import lombok.extern.slf4j.Slf4j;
import net.okair.springcloud.commom.web.exception.DefaultGlobalExceptionHandlerAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author LiuShiZeng
 * @since 2020/1/14
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandlerAdvice extends DefaultGlobalExceptionHandlerAdvice {
}
