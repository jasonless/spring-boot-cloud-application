package net.okair.springcloud.commom.web.entity.form;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import net.okair.springcloud.commom.web.entity.param.BaseParam;
import org.springframework.beans.BeanUtils;

/**
 * @author starrk
 * Created on 2019/12/26
 */
@Data
@Slf4j
@ApiModel
public class BaseQueryForm<P extends BaseParam> extends BaseForm {
    /**
     * 分页查询，当前页数
     */
    private long current = 1;
    /**
     * 分页查询，当前页数每页显示的数量
     */
    private long size = 10;

    public P toParam(Class<P> clazz){
        P p = BeanUtils.instantiateClass(clazz);
        BeanUtils.copyProperties(this,p);
        return p;
    }

    /**
     * 从form中获取page参数，用于分页查询
     * @return
     */
    public Page getPage(){
        return new Page(this.getCurrent(),this.getSize());
    }


}
