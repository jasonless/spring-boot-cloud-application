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
@TableName("t_resource")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Resource extends BasePo {

    private String code;
    private String type;
    private String url;
    private String method;
    private String name;
    private String description;



}
