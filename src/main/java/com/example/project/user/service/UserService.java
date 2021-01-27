package com.example.project.user.service;


import com.example.project.user.entity.Student;
import com.baomidou.mybatisplus.service.IService;
import com.example.project.user.entity.User;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author wanganhui
 * @since 2021-01-20
 */
public interface UserService extends IService<User> {

    /**
     * 查询用户列表
     *
     * @param user 用户信息
     * @return 用户集合
     */
    public List<User> selectUserListPage(User user);

}
