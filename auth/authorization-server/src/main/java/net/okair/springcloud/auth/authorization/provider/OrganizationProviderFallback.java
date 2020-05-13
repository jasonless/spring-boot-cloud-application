package net.okair.springcloud.auth.authorization.provider;



import net.okair.springcloud.common.core.entity.vo.Result;
import org.springframework.stereotype.Component;

/**
 * @author starrk
 * @since 2020/1/2
 */
@Component
public class OrganizationProviderFallback implements OrganizationProvider {
    @Override
    public Result getUserByUniqueId(String uniqueId) {
        //TODO fail
        return Result.fail("通过名称获取用户失败");
    }

    @Override
    public Result queryRolesByUserId(String userId) {
        //TODO fail
        return Result.fail("通过用户主键获取权限失败");
    }


}
