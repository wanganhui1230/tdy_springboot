package com.example.project.documentation.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.common.entity.AjaxResult;
import com.example.project.documentation.entity.Documentation;
import com.example.project.documentation.mapper.DocumentationMapper;
import com.example.project.documentation.service.DocumentationService;
import com.example.project.documentationDetails.entity.DocumentationDetails;
import com.example.project.documentationDetails.service.DocumentationDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class DocumentationServiceImpl extends ServiceImpl<DocumentationMapper, Documentation> implements DocumentationService {

    @Autowired
    private DocumentationMapper documentationMapper;

    @Autowired
    private DocumentationDetailsService documentationDetailsService;

    @Override
    public List<Documentation> selectDocumentationListPage(Documentation documentation) {
        return documentationMapper.selectDocumentationListPage(documentation);
    }

    @Override
    public Documentation getDocumentation(Integer id) {

        Documentation documentation = documentationMapper.selectById(id);
        EntityWrapper<DocumentationDetails> wrapper  = new EntityWrapper<>();
        wrapper.eq("documentation_id",id);
        List<DocumentationDetails> dd = documentationDetailsService.selectList(wrapper);
        documentation.setDocumentationDetailss(dd);
        return documentation;
    }

    @Override
    public int save(Map documentations) {
        try {
            List<Map> documentationDetails = (List<Map>) documentations.get("documentationDetailss");
            Documentation documentation = new Documentation();
            documentation.setTitle(documentationDetails.get(0).get("title").toString());
            documentation.setCreateTime(new Date());
            documentationMapper.insert(documentation);
            for (int i = 0; i < documentationDetails.size(); i++) {
                DocumentationDetails  dd = new DocumentationDetails();
                dd.setDocumentationId(documentation.getId());
                dd.setSort(documentationDetails.get(i).get("sort").toString());
                dd.setTitle(documentationDetails.get(i).get("title").toString());
                dd.setContent(documentationDetails.get(i).get("content").toString());
                dd.setCreateTime(new Date());
                documentationDetailsService.insert(dd);
            }
        }catch(Exception e){
            return 0;
        }
        return 1;
    }

    @Override
    public int remove(Integer id) {

        EntityWrapper<DocumentationDetails> wrapper  = new EntityWrapper<>();
        wrapper.eq("documentation_id",id);
        documentationDetailsService.delete(wrapper);
        return documentationMapper.deleteById(id);
    }

}
