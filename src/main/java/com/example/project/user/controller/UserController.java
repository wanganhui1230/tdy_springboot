package com.example.project.user.controller;


import com.example.common.entity.AjaxResult;
import com.example.common.entity.BaseController;
import com.example.common.entity.TableDataInfo;
import com.example.common.shiro.ShiroUtils;
import com.example.project.user.entity.Student;
import com.example.project.user.entity.User;
import com.example.project.user.service.UserService;
import com.github.pagehelper.PageHelper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author wanganhui
 * @since 2021-01-20
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public String user()
    {
        return "user/user";
    }

    @GetMapping("/add")
    public String add()
    {
        return "user/add";
    }

    @GetMapping("/edit")
    public String edit(Integer id, ModelMap mmap)
    {
        mmap.put("user",userService.selectById(id));
        return "user/edit";
    }

    @PostMapping("/add")
    @ResponseBody
    public AjaxResult add(User user)
    {
        user.setCreateTime(new Date());
        user.setPassword(ShiroUtils.sha256(user.getPassword(),user.getUsername()));
        return toAjax(userService.insert(user));
    }

    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult edit(User user)
    {
       user.setUpdateTime(new Date());
       return toAjax(userService.updateById(user));
    }

    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(Integer id)
    {
        return toAjax(userService.deleteById(id));
    }

    /**
     * 查询用户列表
     * @return
     */
    @RequiresPermissions("system:user:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo apiList(User user)
    {
        PageHelper.startPage(user.getPageNum(),user.getPageSize());
        List<User> list = userService.selectUserListPage(user);
        return getDataTable(list);

    }

    @GetMapping("/test")
    @RequiresPermissions("user:test")
    public String test()
    {
        return "test";
    }
}

