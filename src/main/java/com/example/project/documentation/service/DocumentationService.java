package com.example.project.documentation.service;

import com.baomidou.mybatisplus.service.IService;
import com.example.project.documentation.entity.Documentation;

import java.util.List;

public interface DocumentationService extends IService<Documentation> {

    public List<Documentation> selectDocumentationListPage(Documentation documentation);
}
