package com.example.project.menu.service;

import com.example.project.menu.entity.Menu;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 菜单名称 服务类
 * </p>
 *
 * @author wanganhui
 * @since 2021-01-20
 */
public interface MenuService extends IService<Menu> {

    public List<Menu> findByRoleId(Integer roleId);

    /**
     * 查询菜单列表
     *
     * @param menu 菜单信息
     * @return 菜单集合
     */
    public List<Menu> selectMenuListPage(Menu menu);

    public List<Menu> buildTree(List<Menu> mapList, String value);

}
