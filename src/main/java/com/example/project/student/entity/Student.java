package com.example.project.user.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.example.common.entity.BaseEntity;
import lombok.Data;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author wanganhui
 * @since 2021-01-20
 */
@Data
@TableName("student")
public class Student extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 学生姓名
     */
    private String name;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 性别
     */
    private Integer sex;
    /**
     * 班级
     */
    private String grade;

}
