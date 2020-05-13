package net.okair.springcloud.commom.web.entity.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.okair.springcloud.commom.web.entity.po.BasePo;

import java.io.Serializable;

/**
 * @author starrk
 * Created on 2019/12/26
 */
@Data
@NoArgsConstructor
public class BaseVo<T extends BasePo> implements Serializable {
    private String id;
}
