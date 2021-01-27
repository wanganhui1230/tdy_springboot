package com.example.project.menu.controller;


import com.example.common.entity.AjaxResult;
import com.example.common.entity.BaseController;
import com.example.project.menu.entity.Menu;
import com.example.project.menu.service.MenuService;
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
 * 菜单名称 前端控制器
 * </p>
 *
 * @author wanganhui
 * @since 2021-01-20
 */
@Controller
@RequestMapping("/menu")
public class MenuController extends BaseController {

    @Autowired
    private MenuService menuService;

    @GetMapping()
    public String menu()
    {
        return "menu/menu";
    }

    @GetMapping("/add")
    public String add(Integer id, ModelMap mmap)
    {
        mmap.put("id",id);
        return "menu/add";
    }

    @GetMapping("/edit")
    public String edit(Integer id, ModelMap mmap)
    {
        mmap.put("menu",menuService.selectById(id));
        return "menu/edit";
    }


    @PostMapping("/add")
    @ResponseBody
    public AjaxResult add(Menu menu)
    {
        menu.setCreateTime(new Date());
        return toAjax(menuService.insert(menu));
    }

    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult edit(Menu menu)
    {
        menu.setUpdateTime(new Date());
        return toAjax(menuService.updateById(menu));
    }

    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(Integer id)
    {
        return toAjax(menuService.deleteById(id));
    }

    /**
     * 查询菜单列表
     * @return
     */
    @GetMapping("/list")
    @ResponseBody
    public List<Menu> apiList(Menu menu)
    {
        List<Menu> menuList = menuService.selectMenuListPage(menu);
        return menuList;

    }

}

