package com.example.project.menu.mapper;

import com.example.project.menu.entity.Menu;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 菜单名称 Mapper 接口
 * </p>
 *
 * @author wanganhui
 * @since 2021-01-20
 */
public interface MenuMapper extends BaseMapper<Menu> {

    public List<Menu> findByRoleId(Integer roleId);

    public List<Menu> selectMenuListPage(Menu menu);

}
