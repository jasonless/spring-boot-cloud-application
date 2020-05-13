package net.okair.springcloud.sysadmin.organization.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.okair.springcloud.commom.web.entity.po.BasePo;

/**
 * @author starrk
 * Created on 2019/12/30
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_role_resource_relation")
public class RoleResource extends BasePo {

    private String roleId;
    private String resourceId;

}
