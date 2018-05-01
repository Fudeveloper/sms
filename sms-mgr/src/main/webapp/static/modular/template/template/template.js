/**
 * 短信模板管理初始化
 */
var Template = {
    id: "TemplateTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Template.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '短信模板id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '第三方渠道模板', field: 'channelTemplateNo', visible: true, align: 'center', valign: 'middle'},
            {title: '模板名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '短信类型', field: 'typeName', visible: true, align: 'center', valign: 'middle'},
            {title: '短信模板', field: 'template', visible: true, align: 'center', valign: 'middle'},
            {title: '短信验证码字段', field: 'validateCodeKey', visible: true, align: 'center', valign: 'middle'},
            {title: '短信验证码有效时间（秒）', field: 'validateCodeExpire', visible: true, align: 'center', valign: 'middle'},
            {title: '应用id', field: 'appId', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createDate', visible: true, align: 'center', valign: 'middle'},
            {title: '更新时间', field: 'updateDate', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Template.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Template.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加短信模板
 */
Template.openAddTemplate = function () {
    var index = layer.open({
        type: 2,
        title: '添加短信模板',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/template/template_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看短信模板详情
 */
Template.openTemplateDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '短信模板详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/template/template_update/' + Template.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除短信模板
 */
Template.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/template/delete", function (data) {
            Feng.success("删除成功!");
            Template.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("templateId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询短信模板列表
 */
Template.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Template.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Template.initColumn();
    var table = new BSTable(Template.id, "/template/list", defaultColunms);
    table.setPaginationType("client");
    Template.table = table.init();
});
