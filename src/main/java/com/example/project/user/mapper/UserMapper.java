package com.example.project.user.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.project.user.entity.Student;
import com.example.project.user.entity.User;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author wanganhui
 * @since 2021-01-20
 */
public interface UserMapper extends BaseMapper<User> {

    public List<User> selectUserListPage(User user);

}
