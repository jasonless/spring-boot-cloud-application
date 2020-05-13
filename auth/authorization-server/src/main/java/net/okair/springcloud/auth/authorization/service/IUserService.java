package net.okair.springcloud.auth.authorization.service;


import net.okair.springcloud.sysadmin.organization.entity.po.User;


/**
 * @author LiuShiZeng
 */
public interface IUserService {

    /**
     * 根据用户唯一标识获取用户信息
     *
     * @param uniqueId
     * @return
     */
    User getByUniqueId(String uniqueId);
}
