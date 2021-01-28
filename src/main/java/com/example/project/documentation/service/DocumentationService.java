package com.example.project.documentation.service;

import com.baomidou.mybatisplus.service.IService;
import com.example.common.entity.AjaxResult;
import com.example.project.documentation.entity.Documentation;

import java.util.List;
import java.util.Map;

public interface DocumentationService extends IService<Documentation> {

    public List<Documentation> selectDocumentationListPage(Documentation documentation);

    public Documentation getDocumentation(Integer id);

    public int save(Map documentations);

    public int remove(Integer id);
}
