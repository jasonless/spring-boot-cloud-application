package net.okair.springcloud.common.core.exception;

/**
 * @author starrk
 * Created on 2019/12/25
 */
public interface IErrorType {

    /**
     * 获取异常code
     * @return
     */
    String getCode();

    /**
     * 获取异常信息
     * @return
     */
    String getMsg();
}
