package net.okair.springcloud.sysadmin.organization.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import lombok.extern.slf4j.Slf4j;
import net.okair.springcloud.sysadmin.organization.entity.param.UserQueryParam;
import net.okair.springcloud.sysadmin.organization.entity.po.User;
import net.okair.springcloud.sysadmin.organization.entity.vo.UserVo;
import net.okair.springcloud.sysadmin.organization.exception.UserNotFoundException;
import net.okair.springcloud.sysadmin.organization.repository.mapper.UserMapper;
import net.okair.springcloud.sysadmin.organization.service.IUserRoleService;
import net.okair.springcloud.sysadmin.organization.service.IUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * @author starrk
 * Created on 2019/12/30
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private final IUserRoleService userRoleService;

    @Autowired
    public UserServiceImpl(IUserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }



    @Override
    public UserVo get(String id) {
        User user = this.getById(id);
        if(Objects.isNull(user)){
            throw new UserNotFoundException("用户未找到，id:"+id);
        }
        user.setRoleIds(userRoleService.queryByUserId(id));
        return new UserVo(user);
    }

    @Override
    public User getByUniqueId(String uniqueId) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",uniqueId);
        queryWrapper.or();
        queryWrapper.eq("mobile",uniqueId);
        User user = this.getOne(queryWrapper);
        if(Objects.isNull(user)){
            throw new UserNotFoundException("用户未找到，uniqueId:"+uniqueId);
        }
        user.setRoleIds(userRoleService.queryByUserId(user.getId()));
        return user;
    }

    @Override
    @Transactional
    public boolean add(User user) {
        if(StringUtils.isNotBlank(user.getPassword())){
            user.setPassword(passwordEncoder().encode(user.getPassword()));
        }
        boolean inserts = this.save(user);
        userRoleService.saveBatch(user.getId(),user.getRoleIds());
        return inserts;
    }

    @Override
    public IPage<UserVo> query(Page<User> page, UserQueryParam userQueryParam) {
        QueryWrapper<User> queryWrapper = userQueryParam.build();
        queryWrapper.eq(StringUtils.isNotBlank(userQueryParam.getName()),"name",userQueryParam.getName());
        queryWrapper.eq(StringUtils.isNotBlank(userQueryParam.getUsername()),"username",userQueryParam.getUsername());
        queryWrapper.eq(StringUtils.isNotBlank(userQueryParam.getMobile()),"mobile",userQueryParam.getMobile());
        IPage<User> iPage = this.page(page,queryWrapper);
        return iPage.convert(UserVo::new);
    }

    @Override
    @Transactional
    public boolean update(User user) {
        if(StringUtils.isNotBlank(user.getPassword())){
            user.setPassword(passwordEncoder().encode(user.getPassword()));
        }
        boolean isSuccess = this.updateById(user);
        userRoleService.saveBatch(user.getId(),user.getRoleIds());
        return isSuccess;
    }

    @Override
    @Transactional
    public boolean delete(String id) {
        boolean flag = this.removeById(id);
        userRoleService.removeByUserId(id);
        return flag;
    }
}
