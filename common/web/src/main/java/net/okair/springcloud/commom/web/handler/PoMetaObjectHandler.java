package net.okair.springcloud.commom.web.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;

import net.okair.springcloud.commom.web.entity.po.BasePo;
import net.okair.springcloud.common.core.utils.UserContextHolder;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.reflection.MetaObject;

import java.time.ZonedDateTime;
import java.util.Date;

/**
 * @author starrk
 * Created on 2019/12/27
 */
public class PoMetaObjectHandler implements MetaObjectHandler {


    /**
     * 获取当前用户
     * @return
     */
    private String getCurrentUserName(){
        return StringUtils.defaultIfBlank(UserContextHolder.getInstance().getUserName(), BasePo.DEFAULT_USERNAME);
    }

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createdBy",getCurrentUserName(),metaObject);
        this.setFieldValByName("createdTime",Date.from(ZonedDateTime.now().toInstant()),metaObject);
        this.updateFill(metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updatedBy",getCurrentUserName(),metaObject);
        this.setFieldValByName("updatedTime",Date.from(ZonedDateTime.now().toInstant()),metaObject);

    }
}
