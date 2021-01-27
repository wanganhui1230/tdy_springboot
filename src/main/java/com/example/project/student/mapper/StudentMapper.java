package com.example.project.student.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.project.user.entity.Student;

import java.util.List;

/**
 * <p>
 * 学生表 Mapper 接口
 * </p>
 *
 * @author wanganhui
 * @since 2021-01-20
 */
public interface StudentMapper extends BaseMapper<Student> {

    public List<Student> selectStudentListPage(Student student);

}
