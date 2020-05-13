package net.okair.springcloud.sysadmin.organization.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.okair.springcloud.commom.web.entity.po.BasePo;

import java.util.Set;

/**
 * @author starrk
 * Created on 2019/12/30
 */
@Data
@TableName("t_user")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User extends BasePo {

    private String name;
    private String mobile;
    private String username;
    private String password;
    private String description;
    private Boolean enabled;
    private Boolean accountNonExpired;
    private Boolean credentialsNonExpired;
    private Boolean accountNonLocked;
    @TableField(exist = false)
    private Set<String> roleIds;
    @TableLogic
    private String deleted = "N";

}
