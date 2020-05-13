package net.okair.springcloud.sysadmin.organization.exception;


import lombok.Getter;
import net.okair.springcloud.common.core.exception.IErrorType;

/**
 * @author starrk
 * Created on 2019/12/30
 */
@Getter
public enum OrganizationErrorType implements IErrorType {
    /**
     * 用户未找到
     */
    USER_NOT_FOUND("030100","用户未找到！"),
    ROLE_NOT_FOUND("030200","角色未找到！");

    private String code;

    private String msg;

    OrganizationErrorType(String code,String msg){
        this.code = code;
        this.msg = msg;
    }



}
