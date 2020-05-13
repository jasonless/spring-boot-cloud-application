package net.okair.springcloud.common.core.utils;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.Optional;

/**
 * @author starrk
 * Created on 2019/12/25
 */
public class UserContextHolder {

    private ThreadLocal<Map<String,String>> threadLocal;

    private UserContextHolder(){
        this.threadLocal = new ThreadLocal<Map<String, String>>();
    }

    public static UserContextHolder getInstance(){
        return SingletonHoler.S_INSTANCE;
    }

    /**
     * 静态内部类 单例模式
     */
    private static class SingletonHoler{
        private static final UserContextHolder S_INSTANCE = new UserContextHolder();
    }

    /**
     * 放入上下文信息
     * @param map
     */
    public void setContext(Map<String,String> map){
        threadLocal.set(map);
    }

    public String getUserName(){
        return Optional.ofNullable(threadLocal.get()).orElse(Maps.<String, String>newHashMap()).get("user_name");

    }

    /**
     * 获取上下文信息
     * @return
     */
    public Map<String,String> getContext(){
        return threadLocal.get();
    }

    /**
     * 清空上下文
     */
    public void clear(){
        threadLocal.remove();
    }


}
