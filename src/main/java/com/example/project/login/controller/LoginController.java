package com.example.project.login.controller;


import com.example.common.config.SpringUtil;
import com.example.common.entity.AjaxResult;
import com.example.common.entity.BaseController;
import com.example.common.shiro.ShiroRealm;
import com.example.project.menu.entity.Menu;
import com.example.project.menu.service.MenuService;
import com.example.project.user.entity.Student;
import com.example.project.user.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Author:
 * @Version 1.0.0
 */
@Slf4j
@Controller
public class LoginController extends BaseController {

    @Autowired
    ShiroRealm shiroRealm;

    @Autowired
    MenuService menuService;


    @GetMapping("/")
    public String log() {
        return "login";
    }

    @GetMapping("/index")
    public String index(ModelMap mmap) {
        mmap.put("menuTree",menuService.buildTree(menuService.selectMenuListPage(new Menu()),"0"));
        return "index";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/editor")
    public String editor() {
        return "editor";
    }

    /**
     * description: 登录
     *
     * @return 登录结果
     */
    @GetMapping("/login")
    @ResponseBody
    public AjaxResult login(User user) {
        log.warn("进入登录.....");

        String username = user.getUsername();
        String password = user.getPassword();

        if (StringUtils.isBlank(username)) {
           return error("用户名为空！");
        }

        if (StringUtils.isBlank(password)) {
            return error("密码为空！");
        }
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, false);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            Map map = new HashMap();
            map.put("data",subject.getPrincipal());
            map.put("token",subject.getSession().getId().toString());
            return success(map);

        }
        catch(UnknownAccountException e){
            return error("没有找到对应账号");
        }
        catch(IncorrectCredentialsException e){
            return error("账号密码错误");
            }
    }


    /**
     * description: 登出
     */
    @RequestMapping("/logout")
    @ResponseBody
    public AjaxResult logOut() {
        Subject lvSubject=SecurityUtils.getSubject();
        lvSubject.logout();
        return success("登出成功！");
    }


//    /**
//     * 未登录，shiro应重定向到登录界面，此处返回未登录状态信息由前端控制跳转页面
//     * @return
//     */
//    @RequestMapping("/un_auth")
//    @ResponseBody
//    public AjaxResult unAuth() {
//        return success( "用户未登录！");
//    }


    /**
     * 未登录，shiro应重定向到登录界面，此处返回未登录状态信息由前端控制跳转页面
     * @return
     */
    @GetMapping("/un_auth")
    public String unAuth() {
        return "login";
    }

    /**
     * 未授权，无权限，此处返回未授权状态信息由前端控制跳转页面
     * @return
     */
    @RequestMapping("/unauthorized")
    @ResponseBody
    public AjaxResult unauthorized() {
        return success("用户无权限！");
    }

    private static RedisSessionDAO redisSessionDAO = SpringUtil.getBean(RedisSessionDAO.class);

}