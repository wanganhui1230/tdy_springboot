package com.example.project.menu.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.example.common.entity.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 菜单名称
 * </p>
 *
 * @author wanganhui
 * @since 2021-01-20
 */
@Data
@TableName("sys_menu")
public class Menu extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单id
     */
    @TableId(value = "menu_id", type = IdType.AUTO)
    private Integer menuId;
    /**
     * 菜单父id
     */
    @TableField("parent_id")
    private Integer parentId;
    /**
     * 名称
     */
    private String name;
    /**
     * 路径
     */
    private String url;
    /**
     * 权限标识
     */
    private String authority;
    /**
     * 类型
     */
    private Integer type;
    /**
     * 图标
     */
    private String icon;

    @TableField(exist = false)
    private List<Menu> children;



}
