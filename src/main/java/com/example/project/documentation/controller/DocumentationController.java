package com.example.project.documentation.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.common.entity.AjaxResult;
import com.example.common.entity.BaseController;
import com.example.common.entity.TableDataInfo;
import com.example.project.documentation.entity.Documentation;
import com.example.project.documentation.service.DocumentationService;
import com.example.project.documentationDetails.entity.DocumentationDetails;
import com.example.project.documentationDetails.service.DocumentationDetailsService;
import com.example.project.user.entity.User;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/documentation")
public class DocumentationController extends BaseController {

    @Autowired
    private DocumentationService documentationService;

    @Autowired
    private DocumentationDetailsService documentationDetailsService;

    @GetMapping()
    public String menu()
    {
        return "documentation/documentation";
    }

    @GetMapping("/add")
    public String add()
    {
        return "documentation/add";
    }

    @GetMapping("/details")
    public String details(Integer id,ModelMap mmap)
    {
        Documentation documentation = documentationService.selectById(id);
        EntityWrapper<DocumentationDetails> wrapper  = new EntityWrapper<>();
        wrapper.eq("documentation_id",id);
        List<DocumentationDetails> dd = documentationDetailsService.selectList(wrapper);
        documentation.setDocumentationDetailss(dd);
        mmap.put("Documentation",documentation);
        return "documentation/details";
    }

    @PostMapping("/add")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult add(@RequestBody Map documentations)
    {
        try {
            List<Map> documentationDetails = (List<Map>) documentations.get("documentationDetailss");
            Documentation documentation = new Documentation();
            documentation.setTitle(documentationDetails.get(0).get("title").toString());
            documentation.setCreateTime(new Date());
            documentationService.insert(documentation);
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
            return error("保存失败！");
        }
        return toAjax(1);
    }

    @PostMapping("/remove")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult remove(Integer id)
    {
        EntityWrapper<DocumentationDetails> wrapper  = new EntityWrapper<>();
        wrapper.eq("documentation_id",id);
        documentationDetailsService.delete(wrapper);
        return toAjax(documentationService.deleteById(id));
    }

    /**
     * 查询文档列表
     * @return
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo apiList(Documentation documentation)
    {
        PageHelper.startPage(documentation.getPageNum(),documentation.getPageSize());
        List<Documentation> list = documentationService.selectDocumentationListPage(documentation);
        return getDataTable(list);

    }

}
