/**
 * 短信管理管理初始化
 */
var Message = {
    id: "MessageTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Message.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '短信id', field: 'id', visible: false, align: 'center', valign: 'middle'},
            {title: '手机号', field: 'mobile', visible: true, align: 'center', valign: 'middle'},
            {title: '短信参数', field: 'params', visible: true, align: 'center', valign: 'middle'},
            {title: '验证码', field: 'validateCode', visible: true, align: 'center', valign: 'middle'},
            {title: '模板id', field: 'templateId', visible: false, align: 'center', valign: 'middle'},
            {title: '发送状态', field: 'sendStatusName', visible: true, align: 'center', valign: 'middle'},
            {title: '验证状态', field: 'validateStatusName', visible: true, align: 'center', valign: 'middle'},
            {title: '失败代码', field: 'failCode', visible: false, align: 'center', valign: 'middle'},
            {title: '短信平台id', field: 'bizId', visible: false, align: 'center', valign: 'middle'},
            {title: '收到短信时间', field: 'reciveDate', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createDate', visible: true, align: 'center', valign: 'middle'},
            {title: '更新时间', field: 'updateDate', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Message.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Message.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加短信管理
 */
Message.openAddMessage = function () {
    var index = layer.open({
        type: 2,
        title: '添加短信管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/message/message_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看短信管理详情
 */
Message.openMessageDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '短信管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/message/message_update/' + Message.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除短信管理
 */
Message.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/message/delete", function (data) {
            Feng.success("删除成功!");
            Message.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("messageId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询短信管理列表
 */
Message.search = function () {
    var queryData = {};
    queryData['mobile'] = $("#mobile").val();
    Message.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Message.initColumn();
    var table = new BSTable(Message.id, "/message/list", defaultColunms);
    table.setPaginationType("server");
    Message.table = table.init();
});
