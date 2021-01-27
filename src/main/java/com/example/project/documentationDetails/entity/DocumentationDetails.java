package com.example.project.documentationDetails.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.example.common.entity.BaseEntity;
import lombok.Data;

@Data
@TableName("documentation_details")
public class DocumentationDetails extends BaseEntity {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String sort;

    private String title;

    private String content;

    @TableField(value = "documentation_id")
    private Integer documentationId;
}
