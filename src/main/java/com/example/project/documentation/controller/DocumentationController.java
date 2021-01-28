package com.example.project.documentation.controller;

import com.example.common.entity.AjaxResult;
import com.example.common.entity.BaseController;
import com.example.common.entity.TableDataInfo;
import com.example.project.documentation.entity.Documentation;
import com.example.project.documentation.service.DocumentationService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/documentation")
public class DocumentationController extends BaseController {

    @Autowired
    private DocumentationService documentationService;


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

    @GetMapping("/edit")

    public String edit(Integer id,ModelMap mmap)
    {
        mmap.put("Documentation",documentationService.getDocumentation(id));
        return "documentation/edit";
    }

    @GetMapping("/details")
    public String details(Integer id,ModelMap mmap)
    {
        mmap.put("Documentation",documentationService.getDocumentation(id));
        return "documentation/details";
    }

    @PostMapping("/add")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult add(@RequestBody Map documentations)
    {
        return toAjax(documentationService.save(documentations));
    }

    @PostMapping("/remove")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult remove(Integer id)
    {
        return toAjax(documentationService.remove(id));
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
