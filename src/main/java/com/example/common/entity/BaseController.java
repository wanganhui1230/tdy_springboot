package com.example.common.entity;


import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

/**
 * web层通用数据处理
 * 
 * @author
 */
@CrossOrigin
public class BaseController
{



    /**
     * 响应请求分页数据
     */
    protected TableDataInfo getDataTable(List<?> list)
    {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(0);
        rspData.setRows(list);
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }

    /**
     * 响应返回结果
     *
     * @param rows 影响行数
     * @return 操作结果
     */
    protected AjaxResult toAjax(int rows)
    {
        return rows > 0 ? success() : error();
    }

    /**
     * 响应返回结果
     *
     * @param result 结果
     * @return 操作结果
     */
    protected AjaxResult toAjax(boolean result)
    {
        return result ? success() : error();
    }

    /**
     * 返回成功
     */
    public AjaxResult success()
    {
        return AjaxResult.success();
    }

    /**
     * 返回成功
     */
    public AjaxResult success(Object data)
    {
        return AjaxResult.success(data);
    }

    /**
     * 返回成功
     */
    public AjaxResult success(String msg, Object data)
    {
        return AjaxResult.success(msg,data);
    }

    /**
     * 返回成功
     */
    public AjaxResult success(String msg)
    {
        return AjaxResult.success(msg);
    }

    /**
     * 返回失败消息
     */
    public AjaxResult error()
    {
        return AjaxResult.error();
    }

    /**
     * 返回失败消息
     */
    public AjaxResult error(String msg)
    {
        return AjaxResult.error(msg);
    }

}
