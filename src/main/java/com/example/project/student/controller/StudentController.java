package com.example.project.student.controller;


import com.example.common.entity.AjaxResult;
import com.example.common.entity.BaseController;
import com.example.common.entity.TableDataInfo;
import com.example.common.shiro.ShiroUtils;
import com.example.project.student.service.StudentService;
import com.example.project.user.entity.Student;
import com.example.project.user.service.UserService;
import com.github.pagehelper.PageHelper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 学生表 前端控制器
 * </p>
 *
 * @author wanganhui
 * @since 2021-01-20
 */
@Controller
@RequestMapping("/api/student")
public class StudentController extends BaseController {

    @Autowired
    private StudentService studentService;


    @PostMapping("/add")
    @ResponseBody
    public AjaxResult add(Student student)
    {
        student.setCreateTime(new Date());
        return toAjax(studentService.insert(student));
    }

    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult edit(Student student)
    {
        student.setUpdateTime(new Date());
       return toAjax(studentService.updateById(student));
    }

    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(Integer id)
    {
        return toAjax(studentService.deleteById(id));
    }

    /**
     * 查询学生列表
     * @return
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo apiList(Student student)
    {
        PageHelper.startPage(student.getPageNum(),student.getPageSize());
        List<Student> list = studentService.selectStudentListPage(student);
        return getDataTable(list);

    }

}

