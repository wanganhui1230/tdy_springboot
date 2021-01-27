package com.example.project.role.service;

import com.example.project.role.entity.Role;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author wanganhui
 * @since 2021-01-20
 */
public interface RoleService extends IService<Role> {


    public List<Role> findByUserId(Integer userId);

}
