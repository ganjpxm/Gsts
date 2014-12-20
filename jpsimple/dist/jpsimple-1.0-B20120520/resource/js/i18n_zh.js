/**
 *@class I18N
 *description:js multi language class,should be loaded before others,singleton.
 *author:ganjp
 *date:2010-8-16
 */
var I18N = null;
if(!I18N){
I18N = {
	save: "保存",
	reset: "重置",
	close: "关闭",
	confirm: "确定",
	cancel: "取消",
	yes: "是",
	no: "否",
	refresh: "刷新",
	loading: "请稍等...",
	processing: "处理中...",
	choose: "请选择...",
	promp: "提示",
	add: "增加",
	edit: "编辑",
	del: "删除",
	search: "查询",
	view: "查看",
	
	msg_save_sucess: "保存成功",
	msg_save_fail: "保存失败",
	msg_del_sucess: "成功删除所选的记录!",
	msg_del_confirm: "是否真的要删除数据?",
	msg_no_sel_node: "请先选中节点!",
	msg_no_sel_del_record: "请选择要删除的行!",
	msg_no_sel_del_node: "请选择要删除的节点!",
	msg_no_sel_edit_record: "请选择要编辑的行!",
	msg_no_sel_edit_node: "请选择要编辑的节点!",
	msg_no_sel_view_record: "请选择要查看的行!",
	msg_single_edit_record: "编辑时只能单选!",
	msg_single_view_record: "查看时只能单选!",
	msg_bg_verify_fail: "后台验证失败",
	msg_fg_verify_fail: "前台验证失败",
	msg_fg_verify_fail_tip: "有非法字符不能保存!",
	msg_saving: "正在保存。。。",
	msg_system_error: "系统错误",
	msg_set_form_id: "请在表单中设置ID项:",
	msg_pwd_not_match: "密码不一致",
	msg_cd_format_error: "该输入项只能包含半角字母,数字,-和_",
	msg_chinese_format_error: "该项只能输入中文",
	
	zeroRecords: "没有匹配的数据",
	emptyTable: "数据为空",
	displayRecords: "显示 {0} - {1} 条, 共 {2} 条",
	infoFiltered: "总共查找到 {0} 条",
	firstPage: "首页",
	previousPage: "上一页",
	nextPage: "下一页",
	lastPage: "最后页",
	
	uuid_get_fail: "获取uuid失败"
};
};
