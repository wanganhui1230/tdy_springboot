/**
 * 通用js方法封装处理

 */
(function ($) {
    $.extend({
        _tree: {},
        btTable: {},
        bttTable: {},
        // 表格封装处理
        table: {
            _option: {},
            // 初始化表格参数
            init: function(options) {
                var defaults = {
                    id: "bootstrap-table",
                    type: 0, // 0 代表bootstrapTable 1代表bootstrapTreeTable
                    height: undefined,
                    sidePagination: "server",
                    sortName: "",
                    sortOrder: "asc",
                    pagination: true,
                    pageSize: 5,
                    pageList: [10, 25, 50],
                    toolbar: "toolbar",
                    striped: false,
                    escape: false,
                    showFooter: true,
                    search: false,
                    showSearch: true,
                    showPageGo: true,
                    showRefresh: true,
                    showColumns: true,
                    showToggle: true,
                    showExport: true,
                    clickToSelect: false,
                    rememberSelected: false,
                    fixedColumns: false,
                    fixedNumber: 0,
                    rightFixedColumns: false,
                    rightFixedNumber: 0,
                    queryParams: $.table.queryParams,
                    rowStyle: {},
                };
                var options = $.extend(defaults, options);
                $.table._option = options;
                $.btTable = $('#' + options.id);
                $('#' + options.id).bootstrapTable({
                    url: options.url,                                   // 请求后台的URL（*）
                    contentType: "application/x-www-form-urlencoded",   // 编码类型
                    method: 'post',                                     // 请求方式（*）
                    cache: false,                                       // 是否使用缓存
                    height: options.height,                             // 表格的高度
                    striped: options.striped,                           // 是否显示行间隔色
                    sortable: true,                                     // 是否启用排序
                    sortStable: true,                                   // 设置为 true 将获得稳定的排序
                    sortName: options.sortName,                         // 排序列名称
                    sortOrder: options.sortOrder,                       // 排序方式  asc 或者 desc
                    pagination: options.pagination,                     // 是否显示分页（*）
                    pageNumber: 1,                                      // 初始化加载第一页，默认第一页
                    pageSize: options.pageSize,                         // 每页的记录行数（*） 
                    pageList: options.pageList,                         // 可供选择的每页的行数（*）
                    escape: options.escape,                             // 转义HTML字符串
                    showFooter: options.showFooter,                     // 是否显示表尾
                    iconSize: 'outline',                                // 图标大小：undefined默认的按钮尺寸 xs超小按钮sm小按钮lg大按钮
                    toolbar: '#' + options.toolbar,                     // 指定工作栏
                    sidePagination: options.sidePagination,             // server启用服务端分页client客户端分页
                    search: options.search,                             // 是否显示搜索框功能
                    searchText: options.searchText,                     // 搜索框初始显示的内容，默认为空
                    showSearch: options.showSearch,                     // 是否显示检索信息
                    showPageGo: options.showPageGo,               		// 是否显示跳转页
                    showRefresh: options.showRefresh,                   // 是否显示刷新按钮
                    showColumns: options.showColumns,                   // 是否显示隐藏某列下拉框
                    showToggle: options.showToggle,                     // 是否显示详细视图和列表视图的切换按钮
                    showExport: options.showExport,                     // 是否支持导出文件
                    uniqueId: options.uniqueId,                         // 唯 一的标识符
                    clickToSelect: options.clickToSelect,				// 是否启用点击选中行
                    detailView: options.detailView,                     // 是否启用显示细节视图
                    onClickRow: options.onClickRow,                     // 点击某行触发的事件
                    onDblClickRow: options.onDblClickRow,               // 双击某行触发的事件
                    onClickCell: options.onClickCell,                   // 单击某格触发的事件
                    onDblClickCell: options.onDblClickCell,             // 双击某格触发的事件
                    rememberSelected: options.rememberSelected,         // 启用翻页记住前面的选择
                    fixedColumns: options.fixedColumns,                 // 是否启用冻结列（左侧）
                    fixedNumber: options.fixedNumber,                   // 列冻结的个数（左侧）
                    rightFixedColumns: options.rightFixedColumns,       // 是否启用冻结列（右侧）
                    rightFixedNumber: options.rightFixedNumber,         // 列冻结的个数（右侧）
                    onReorderRow: options.onReorderRow,                 // 当拖拽结束后处理函数
                    queryParams: options.queryParams,                   // 传递参数（*）
                    rowStyle: options.rowStyle,                         // 通过自定义函数设置行样式
                    columns: options.columns,                           // 显示列信息（*）
                    responseHandler: $.table.responseHandler,           // 在加载服务器发送来的数据之前处理函数
                    onLoadSuccess: $.table.onLoadSuccess,               // 当所有数据被加载时触发处理函数
                    exportOptions: options.exportOptions,               // 前端导出忽略列索引
                    detailFormatter: options.detailFormatter,           // 在行下面展示其他数据列表
                });
            },
            // 查询条件
            queryParams: function(params) {
                var curParams = {
                    // 传递参数查询参数
                    pageSize:       params.limit,
                    pageNum:        params.offset / params.limit + 1,
                    searchValue:    params.search,
                    orderByColumn:  params.sort,
                    isAsc:          params.order
                };
                var currentId = $.common.isEmpty($.table._option.formId) ? $('form').attr('id') : $.table._option.formId;
                return $.extend(curParams, $.common.formToJSON(currentId));
            },
            // 搜索-默认第一个form
            search: function(formId, data) {
                debugger
                var currentId = $.common.isEmpty(formId) ? $('form').attr('id') : formId;
                var params = $.btTable.bootstrapTable('getOptions');
                params.queryParams = function(params) {
                    var search = $.common.formToJSON(currentId);
                    if($.common.isNotEmpty(data)){
                        $.each(data, function(key) {
                            search[key] = data[key];
                        });
                    }
                    search.pageSize = params.limit;
                    search.pageNum = params.offset / params.limit + 1;
                    search.searchValue = params.search;
                    search.orderByColumn = params.sort;
                    search.isAsc = params.order;
                    return search;
                }
                $.btTable.bootstrapTable('refresh', params);
            },
            // 刷新表格
            refresh: function() {
                $.btTable.bootstrapTable('refresh', {
                    silent: true
                });
            },
            // 查询表格指定列值
            selectColumns: function(column) {
                var rows = $.map($.btTable.bootstrapTable('getSelections'), function (row) {
                    return row[column];
                });
                if ($.common.isNotEmpty($.table._option.rememberSelected) && $.table._option.rememberSelected) {
                    rows = rows.concat(selectionIds);
                }
                return $.common.uniqueFn(rows);
            },
            // 显示表格指定列
            showColumn: function(column) {
                $.btTable.bootstrapTable('showColumn', column);
            },
            // 隐藏表格指定列
            hideColumn: function(column) {
                $.btTable.bootstrapTable('hideColumn', column);
            }
        },
        form: {
            // 表单重置
            reset: function (formId) {
                var currentId = $.common.isEmpty(formId) ? $('form').attr('id') : formId;
                $("#" + currentId)[0].reset();
                $.table.refresh();
            }
        },
        // 表格树封装处理
        treeTable: {
            // 初始化表格
            init: function(options) {
                var defaults = {
                    id: "bootstrap-tree-table",
                    type: 1, // 0 代表bootstrapTable 1代表bootstrapTreeTable
                    height: 0,
                    rootIdValue: null,
                    ajaxParams: {},
                    toolbar: "toolbar",
                    striped: false,
                    expandColumn: 1,
                    showRefresh: true,
                    showColumns: true,
                    expandAll: true,
                    expandFirst: true
                };
                var options = $.extend(defaults, options);
                $.table._option = options;
                $.bttTable = $('#' + options.id).bootstrapTreeTable({
                    code: options.code,                                 // 用于设置父子关系
                    parentCode: options.parentCode,                     // 用于设置父子关系
                    type: 'get',                                        // 请求方式（*）
                    url: options.url,                                   // 请求后台的URL（*）
                    ajaxParams: options.ajaxParams,                     // 请求数据的ajax的data属性
                    rootIdValue: options.rootIdValue,                   // 设置指定根节点id值
                    height: options.height,                             // 表格树的高度
                    expandColumn: options.expandColumn,                 // 在哪一列上面显示展开按钮
                    striped: options.striped,                           // 是否显示行间隔色
                    bordered: true,                                     // 是否显示边框
                    toolbar: '#' + options.toolbar,                     // 指定工作栏
                    showRefresh: options.showRefresh,                   // 是否显示刷新按钮
                    showColumns: options.showColumns,                   // 是否显示隐藏某列下拉框
                    expandAll: options.expandAll,                       // 是否全部展开
                    expandFirst: options.expandFirst,                   // 是否默认第一级展开--expandAll为false时生效
                    columns: options.columns
                });
            },
            // 条件查询
            search: function(formId) {
                var currentId = $.common.isEmpty(formId) ? $('form').attr('id') : formId;
                var params = $.common.formToJSON(currentId);
                $.bttTable.bootstrapTreeTable('refresh', params);
            },
            // 刷新
            refresh: function() {
                $.bttTable.bootstrapTreeTable('refresh');
            },
            // 查询表格树指定列值
            selectColumns: function(column) {
                var rows = $.map($.bttTable.bootstrapTreeTable('getSelections'), function (row) {
                    return row[column];
                });
                return $.common.uniqueFn(rows);
            },
        },
        treeForm: {
            // 表单重置
            reset: function (formId) {
                var currentId = $.common.isEmpty(formId) ? $('form').attr('id') : formId;
                $("#" + currentId)[0].reset();
                $.treeTable.refresh();
            }
        },
        operate:{
            // 弹出层指定宽度
            open: function (title, url, width, height, callback) {
                //如果是移动端，就使用自适应大小弹窗
                if (navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)) {
                    width = 'auto';
                    height = 'auto';
                }
                if ($.common.isEmpty(title)) {
                    title = false;
                };
                if ($.common.isEmpty(url)) {
                    url = "/404.html";
                };
                if ($.common.isEmpty(width)) {
                    width = 800;
                };
                if ($.common.isEmpty(height)) {
                    height = ($(window).height() - 50);
                };
                if ($.common.isEmpty(callback)) {
                    callback = function(index, layero) {
                        var iframeWin = layero.find('iframe')[0];
                        iframeWin.contentWindow.submitHandler(index, layero);
                    }
                }
                layer.open({
                    type: 2,
                    area: [width + 'px', height + 'px'],
                    fix: false,
                    //不固定
                    maxmin: true,
                    shade: 0.3,
                    title: title,
                    content: url,
                    btn: ['确定', '关闭'],
                    // 弹层外区域关闭
                    shadeClose: true,
                    yes: callback,
                    cancel: function(index) {
                        return true;
                    }
                });
            },
            // 弹出层指定宽度
            getContent: function (type,id,callback) {
                debugger
                var title = (type==1?"新增":"修改");
                var url =(type==1?$.table._option.addUrl:$.table._option.editUrl)+(id == undefined?"":"?id="+id);
                if ($.common.isEmpty(callback)) {
                    callback = function(index, layero) {
                        var iframeWin = layero.find('iframe')[0];
                        iframeWin.contentWindow.submitHandler(index, layero);
                    }
                }
                layer.open({
                    type: 2,
                    area: [$.table._option.width + 'px', $.table._option.height + 'px'],
                    fix: false,
                    //不固定
                    maxmin: true,
                    shade: 0.3,
                    title: title,
                    content: url,
                    btn: ['确定', '关闭'],
                    // 弹层外区域关闭
                    shadeClose: true,
                    yes: callback,
                    cancel: function(index) {
                        return true;
                    }
                });
            },
            // 保存信息 刷新表格
            save: function(url, data, callback) {
                var config = {
                    url: url,
                    type: "post",
                    dataType: "json",
                    data: data,
                    beforeSend: function () {
                        layer.load(2);
                    },
                    success: function(result) {
                        var parent = window.parent;
                        if(result.code == 0) {
                            parent.$.table.refresh();
                            var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                            parent.layer.close(index); //再执行关闭
                        }else{
                            layer.msg("添加失败！")
                        }
                    }
                };
                $.ajax(config)
            },
            saveTree: function(url, data, callback) {
                var config = {
                    url: url,
                    type: "post",
                    dataType: "json",
                    data: data,
                    beforeSend: function () {
                        layer.load(2);
                    },
                    success: function(result) {
                        var parent = window.parent;
                        if(result.code == 0) {
                            parent.$.treeTable.refresh();
                            var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                            parent.layer.close(index); //再执行关闭
                        }else{
                            layer.msg("添加失败！")
                        }
                    }
                };
                $.ajax(config)
            },
            // 删除信息
            remove: function(id,url) {
                layer.confirm("确定删除该条" + $.table._option.modalName + "信息吗？", function() {
                    var config = {
                        url: $.table._option.removeUrl,
                        type: "post",
                        dataType:"json",
                        data: {"id":id},
                        beforeSend: function () {
                            layer.load(2);
                        },
                        success: function(result) {
                            if(result.code == 0) {
                                $.table.refresh();
                                layer.closeAll();
                            }else{
                                layer.msg("删除失败！")
                            }
                        }
                    };
                    $.ajax(config)
                });

            },
            // 删除信息
            removeTree: function(id,url) {
                layer.confirm("确定删除该条" + $.table._option.modalName + "信息吗？", function() {
                    var config = {
                        url: $.table._option.removeUrl,
                        type: "post",
                        dataType:"json",
                        data: {"id":id},
                        beforeSend: function () {
                            layer.load(2);
                        },
                        success: function(result) {
                            if(result.code == 0) {
                                $.treeTable.refresh();
                                layer.closeAll();
                            }else{
                                layer.msg("删除失败！")
                            }
                        }
                    };
                    $.ajax(config)
                });

            },
        },
        // 通用方法封装处理
        common: {
            // 判断字符串是否为空
            isEmpty: function (value) {
                if (value == null || this.trim(value) == "") {
                    return true;
                }
                return false;
            },
            // 空格截取
            trim: function (value) {
                if (value == null) {
                    return "";
                }
                return value.toString().replace(/(^\s*)|(\s*$)|\r|\n/g, "");
            },
            // 判断一个字符串是否为非空串
            isNotEmpty: function (value) {
                return !$.common.isEmpty(value);
            },
            // 数组中的所有元素放入一个字符串
            join: function(array, separator) {
                if ($.common.isEmpty(array)) {
                    return null;
                }
                return array.join(separator);
            },
            // 获取form下所有的字段并转换为json对象
            formToJSON: function(formId) {
                var json = {};
                $.each($("#" + formId).serializeArray(), function(i, field) {
                    json[field.name] = field.value;
                });
                return json;
            },
            // 数组去重
            uniqueFn: function(array) {
                var result = [];
                var hashObj = {};
                for (var i = 0; i < array.length; i++) {
                    if (!hashObj[array[i]]) {
                        hashObj[array[i]] = true;
                        result.push(array[i]);
                    }
                }
                return result;
            }
        }
    });
})(jQuery);





