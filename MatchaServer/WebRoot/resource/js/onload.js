jQuery(document).ready(function() {  
	//获取屏幕高度
	height = document.body.scrollHeight;
	$("#treeDiv").height(height);
	$("#charsId").height(height/1.5);
	//菜单列表
	TreeModule.tree('tree_module');
});