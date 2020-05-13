package net.okair.springcloud.commom.web.entity.form;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import net.okair.springcloud.commom.web.entity.po.BasePo;
import org.springframework.beans.BeanUtils;

/**
 * @author starrk
 * Created on 2019/12/26
 */
@Data
@Slf4j
@ApiModel
public class BaseForm<T extends BasePo> {

    private String username;

    /**
     * from 转为 po,
     * @param clazz
     * @return
     */
    public T toPo(Class<T> clazz){
        T t = BeanUtils.instantiateClass(clazz);
        BeanUtils.copyProperties(this,t);
        return t;
    }

    public T toPo(String id,Class<T> clazz){

        T t = BeanUtils.instantiateClass(clazz);
        t.setId(id);
        BeanUtils.copyProperties(this,t);
        return t;

    }


}
