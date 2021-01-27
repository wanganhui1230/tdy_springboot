package com.example.project.documentation.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.project.documentation.entity.Documentation;

import java.util.List;

public interface DocumentationMapper extends BaseMapper<Documentation> {
    public List<Documentation> selectDocumentationListPage(Documentation documentation);
}
