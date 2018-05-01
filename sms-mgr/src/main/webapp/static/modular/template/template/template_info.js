/**
 * 初始化短信模板详情对话框
 */
var TemplateInfoDlg = {
    templateInfoData : {}
};

/**
 * 清除数据
 */
TemplateInfoDlg.clearData = function() {
    this.templateInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TemplateInfoDlg.set = function(key, val) {
    this.templateInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TemplateInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
TemplateInfoDlg.close = function() {
    parent.layer.close(window.parent.Template.layerIndex);
}

/**
 * 收集数据
 */
TemplateInfoDlg.collectData = function() {
    this
    .set('id')
    .set('channelTemplateNo')
    .set('name')
    .set('type')
    .set('template')
    .set('validateCodeKey')
    .set('validateCodeExpire')
    .set('appId')
    .set('createDate')
    .set('updateDate');
}

/**
 * 提交添加
 */
TemplateInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/template/add", function(data){
        Feng.success("添加成功!");
        window.parent.Template.table.refresh();
        TemplateInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.templateInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
TemplateInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/template/update", function(data){
        Feng.success("修改成功!");
        window.parent.Template.table.refresh();
        TemplateInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.templateInfoData);
    ajax.start();
}

$(function() {

});
