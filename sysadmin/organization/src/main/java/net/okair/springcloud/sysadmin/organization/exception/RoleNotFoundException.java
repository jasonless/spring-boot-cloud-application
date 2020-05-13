package net.okair.springcloud.sysadmin.organization.exception;


import net.okair.springcloud.common.core.exception.BaseException;

/**
 * @author starrk
 * Created on 2019/12/30
 */
public class RoleNotFoundException extends BaseException {

    public RoleNotFoundException(){
        super(OrganizationErrorType.ROLE_NOT_FOUND);
    }

    public RoleNotFoundException(String message){
        super(OrganizationErrorType.ROLE_NOT_FOUND,message);
    }

}
