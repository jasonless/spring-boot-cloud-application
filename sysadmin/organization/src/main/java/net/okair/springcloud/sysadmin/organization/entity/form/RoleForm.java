package net.okair.springcloud.sysadmin.organization.entity.form;



import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.okair.springcloud.commom.web.entity.form.BaseForm;
import net.okair.springcloud.sysadmin.organization.entity.po.Role;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@ApiModel
@Data
public class RoleForm extends BaseForm<Role> {

    @NotBlank(message = "角色编码不能为空")
    @ApiModelProperty(value = "角色编码")
    private String code;

    @NotBlank(message = "角色名称不能为空")
    @ApiModelProperty(value = "角色名称")
    private String name;

    @ApiModelProperty(value = "角色描述")
    private String description;

    @ApiModelProperty(value = "角色拥有的资源id列表")
    private Set<String> resourceIds;

}
