package com.example.common.shiro;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.project.menu.entity.Menu;
import com.example.project.menu.service.MenuService;
import com.example.project.role.entity.Role;
import com.example.project.role.service.RoleService;
import com.example.project.user.entity.User;
import com.example.project.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * @Author:
 * @Version 1.0.0
 */
@Slf4j
@Component
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;
    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        log.info("开始执行授权操作.......");

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        /**
         * 查询用户角色
         * 如果身份认证的时候没有传入User对象，这里只能取到userName
         * 也就是SimpleAuthenticationInfo构造的时候第一个参数传递需要User对象
         */
        User user = (User) principalCollection.getPrimaryPrincipal();

        if (user == null) {
            log.error("用户不存在");
            throw new UnknownAccountException("用户不存在");
        }
        //TODO 是否为超级管理员   是  全部菜单权限

        /**
         * 查询用户角色
         */
        List<Role> roles = roleService.findByUserId(user.getUserId());
        if(CollectionUtils.isNotEmpty(roles)){
            for (Role role : roles) {
                authorizationInfo.addRole(role.getRemark());
                // 根据角色查询权限
                List<Menu> menus = menuService.findByRoleId(role.getRoleId());
                for (Menu m : menus) {
                    authorizationInfo.addStringPermission(m.getAuthority());
                }
            }
        }

        return authorizationInfo;
    }


    /**
     * 认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        log.info("开始进行身份认证......");
        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;

        //通过username从数据库中查找 User对象.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        EntityWrapper<User> wrapper  = new EntityWrapper<>();
        wrapper.eq("username",upToken.getUsername());
        User user = userService.selectOne(wrapper);
        if (Objects.isNull(user)) {
            return null;
        }

        return new SimpleAuthenticationInfo(user,user.getPassword(),ByteSource.Util.bytes(user.getUsername()),getName());
    }


    /**
     * 将自己的验证方式加入容器
     *
     * 凭证匹配器（由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了）
     *
     * @param credentialsMatcher
     */
    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();

        /**
         * 散列算法:这里可以使用MD5算法 也可以使用SHA-256
         */
        hashedCredentialsMatcher.setHashAlgorithmName(ShiroUtils.hashAlgorithmName);
        /**
         * 散列的次数，比如散列2次，相当于 md5(md5(""));
         */
        hashedCredentialsMatcher.setHashIterations(ShiroUtils.hashIterations);
        super.setCredentialsMatcher(hashedCredentialsMatcher);
    }

}