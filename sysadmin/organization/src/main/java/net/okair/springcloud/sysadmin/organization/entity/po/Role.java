package net.okair.springcloud.sysadmin.organization.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
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
@TableName("t_role")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role extends BasePo {

    private String code;
    private String name;
    private String description;

    @TableField(exist = false)
    private Set<String> resourceIds;

}
