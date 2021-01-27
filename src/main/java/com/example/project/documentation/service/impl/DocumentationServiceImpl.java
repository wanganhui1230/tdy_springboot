package com.example.project.documentation.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.project.documentation.entity.Documentation;
import com.example.project.documentation.mapper.DocumentationMapper;
import com.example.project.documentation.service.DocumentationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentationServiceImpl extends ServiceImpl<DocumentationMapper, Documentation> implements DocumentationService {

    @Autowired
    private DocumentationMapper documentationMapper;

    @Override
    public List<Documentation> selectDocumentationListPage(Documentation documentation) {
        return documentationMapper.selectDocumentationListPage(documentation);
    }

}
