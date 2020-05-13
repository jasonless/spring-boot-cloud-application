package net.okair.springcloud.sysadmin.organization.entity.vo;

import lombok.Data;
import net.okair.springcloud.commom.web.entity.vo.BaseVo;
import net.okair.springcloud.sysadmin.organization.entity.po.User;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.Set;

/**
 * @author starrk
 * Created on 2019/12/30
 */
@Data
public class UserVo extends BaseVo<User> {

    public UserVo(User user){
        BeanUtils.copyProperties(user,this);
    }

    private String name;
    private String mobile;
    private String username;
    private String description;
    private String deleted;
    private Set<String> roleIds;
    private String createdBy;
    private String updateBy;
    private Date createdTime;
    private Date updatedTime;

}
