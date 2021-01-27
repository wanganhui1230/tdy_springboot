package com.example.project.documentation.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.example.common.entity.BaseEntity;
import com.example.project.documentationDetails.entity.DocumentationDetails;
import lombok.Data;

import java.util.List;

@Data
@TableName("documentation")
public class Documentation extends BaseEntity {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String title;

    @TableField(exist = false)
    private List<DocumentationDetails> documentationDetailss;
}
