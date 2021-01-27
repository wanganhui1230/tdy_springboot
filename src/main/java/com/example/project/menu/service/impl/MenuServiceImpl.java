package com.example.project.menu.service.impl;

import com.example.project.menu.entity.Menu;
import com.example.project.menu.mapper.MenuMapper;
import com.example.project.menu.service.MenuService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 菜单名称 服务实现类
 * </p>
 *
 * @author wanganhui
 * @since 2021-01-20
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> findByRoleId(Integer roleId) {
        return menuMapper.findByRoleId(roleId);
    }

    @Override
    public List<Menu> selectMenuListPage(Menu menu) {
        return menuMapper.selectMenuListPage(menu);
    }

    /**
     * 递归列表
     *
     */
    public List<Menu> buildTree(List<Menu> mapList, String value) {
        int size = 0;
        for (Menu menu : mapList) {
            if (!"0".equals(value) && value.equals(menu.getParentId().toString())) {
                size++;
            }
        }
        if (!"0".equals(value) && size == 0) {
            return new ArrayList<>();
        }
        List<Menu> list = new ArrayList<>();
        for (Menu menu : mapList) {
            if (value.equals(menu.getParentId().toString())) {
                Menu children = new Menu();
                children.setMenuId(menu.getMenuId());
                children.setName(menu.getName());
                children.setUrl(menu.getUrl());
                List<Menu> listNode = new ArrayList<>();
                if (value.equals(menu.getParentId().toString())) {
                    listNode = buildTree(mapList, String.valueOf(menu.getMenuId()));
                }
                children.setChildren(listNode);
                list.add(children);
            }
        }
        return list;
    }
}
