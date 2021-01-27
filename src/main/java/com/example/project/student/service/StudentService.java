package com.example.project.student.service;


import com.baomidou.mybatisplus.service.IService;
import com.example.project.user.entity.Student;

import java.util.List;

/**
 * <p>
 * 学生表 服务类
 * </p>
 *
 * @author wanganhui
 * @since 2021-01-20
 */
public interface StudentService extends IService<Student> {

    /**
     * 查询学生列表
     *
     * @param student 学生信息
     * @return 学生集合
     */
    public List<Student> selectStudentListPage(Student student);

}
