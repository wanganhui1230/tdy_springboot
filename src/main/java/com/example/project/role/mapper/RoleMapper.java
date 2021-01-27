package com.example.project.role.mapper;

import com.example.project.role.entity.Role;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author wanganhui
 * @since 2021-01-20
 */
public interface RoleMapper extends BaseMapper<Role> {

    public List<Role> findByUserId(Integer userId);

}
