package com.example.project.user.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.project.user.entity.Student;
import com.example.project.user.entity.User;
import com.example.project.user.mapper.UserMapper;
import com.example.project.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author wanganhui
 * @since 2021-01-20
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> selectUserListPage(User user) {
        return userMapper.selectUserListPage(user);
    }
}
