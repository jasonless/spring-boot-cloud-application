package net.okair.springcloud.auth.authorization.exception;


import lombok.extern.slf4j.Slf4j;
import net.okair.springcloud.commom.web.exception.DefaultGlobalExceptionHandlerAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author LiuShiZeng
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandlerAdvice extends DefaultGlobalExceptionHandlerAdvice {

}