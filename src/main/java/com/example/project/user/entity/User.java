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
@TableName("sys_user")
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;
    /**
     * 账号名称
     */
    private String username;
    /**
     * 账号密码
     */
    private String password;
    /**
     * 加盐
     */
    private String salt;
    /**
     * 用户名称
     */
    private String name;
    /**
     * 状态
     */
    private Integer status;


}
