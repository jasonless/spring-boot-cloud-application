package net.okair.springcloud.common.core.exception;


import lombok.Getter;

/**
 * @author starrk
 * Created on 2019/12/25
 */
@Getter
public class BaseException extends RuntimeException {

    private final IErrorType errorType;

    public BaseException(){
        this.errorType = SystemErrorEnum.SYSTEM_ERROR;
    }

    public BaseException(IErrorType errorType) {
        this.errorType = errorType;
    }

    public BaseException(IErrorType errorType, String message) {
        super(message);
        this.errorType = errorType;
    }

    public BaseException(IErrorType errorType, String message, Throwable cause) {
        super(message, cause);
        this.errorType = errorType;
    }

}
