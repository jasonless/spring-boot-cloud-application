package net.okair.springcloud.sysadmin.organization.entity.param;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.okair.springcloud.commom.web.entity.param.BaseParam;
import net.okair.springcloud.sysadmin.organization.entity.po.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserQueryParam extends BaseParam<User> {
    private String name;
    private String mobile;
    private String username;
}
