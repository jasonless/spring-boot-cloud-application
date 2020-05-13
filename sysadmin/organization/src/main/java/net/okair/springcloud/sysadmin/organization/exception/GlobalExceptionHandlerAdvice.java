package net.okair.springcloud.sysadmin.organization.exception;


import lombok.extern.slf4j.Slf4j;
import net.okair.springcloud.commom.web.exception.DefaultGlobalExceptionHandlerAdvice;
import net.okair.springcloud.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author starrk
 * Created on 2019/12/30
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandlerAdvice extends DefaultGlobalExceptionHandlerAdvice {

    @ExceptionHandler(value = {UserNotFoundException.class})
    public Result userNotFound(UserNotFoundException ex){
        log.error(ex.getMessage());
        return Result.fail(ex.getErrorType());

    }

    @ExceptionHandler(value = {RoleNotFoundException.class})
    public Result roleNotFound(RoleNotFoundException ex){
        log.error(ex.getMessage());
        return Result.fail(ex.getMessage());
    }


}
