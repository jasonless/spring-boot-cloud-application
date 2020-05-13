package net.okair.springcloud.sysadmin.organization.exception;


import net.okair.springcloud.common.core.exception.BaseException;

/**
 * @author starrk
 * Created on 2019/12/30
 */
public class UserNotFoundException extends BaseException {
    public UserNotFoundException(){
        super(OrganizationErrorType.USER_NOT_FOUND);
    }

    public UserNotFoundException(String message){
        super(OrganizationErrorType.USER_NOT_FOUND,message);
    }

}
