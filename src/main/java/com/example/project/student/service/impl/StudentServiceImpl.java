package com.example.project.student.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.project.student.mapper.StudentMapper;
import com.example.project.student.service.StudentService;
import com.example.project.user.entity.Student;
import com.example.project.user.mapper.UserMapper;
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
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<Student> selectStudentListPage(Student student) {
        return studentMapper.selectStudentListPage(student);
    }
}
