package net.okair.springcloud.commom.web.entity.param;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.Data;
import net.okair.springcloud.commom.web.entity.po.BasePo;

import java.util.Date;

/**
 * @author starrk
 * Created on 2019/12/26
 */
@Data
public class BaseParam<T extends BasePo> {

    private Date createdTimeStart;
    private Date createTimeEnd;

    public QueryWrapper<T> build(){
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge(createdTimeStart != null, "created_time",this.createdTimeStart);
        queryWrapper.le(createTimeEnd != null ,"created_time",this.createTimeEnd );
        return queryWrapper;
    }

}
