package net.okair.springcloud.sysadmin.organization.entity.param;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.okair.springcloud.commom.web.entity.param.BaseParam;
import net.okair.springcloud.sysadmin.organization.entity.po.Group;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupQueryParam extends BaseParam<Group> {
    private String name;
}
