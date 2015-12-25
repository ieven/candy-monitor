var TreeModule = function () {

	var _array_contain = function (arr, val) {  
		for(var i in arr) {  
			if(arr[i] == val) {
				return true;
			}
		}
		return false; 
	}

	var _request_data = function (container, args ,url) {
		$.ajax({
			url : "menu", //要访问的后台地址
			type : "POST", //使用post方法访问后台
			data : "", //要发送的数据
			dataType : 'JSON',//here
			success : function(data) {
				var json = data[0];
				if (0==json['status']) return 0;

				var bodytree = $('#'+container).parent();
				bodytree.empty();

				var datatree = $('<div />');
				datatree.attr('id', container);
				datatree.addClass('tree-demo');
				bodytree.append(datatree);

				datatree.jstree({
					"core" : {
						"themes" : {
							"responsive": false
						},
						'data':json['data'],
						 "check_callback" : true
					},
					"types" : {
						"default" : {
							"icon" : "fa fa-folder icon-state-warning icon-lg"
						},
						"file" : {
							"icon" : "fa fa-file icon-state-warning icon-lg"
						}
					},
					'plugins': [ "checkbox", "types"],
					'checkbox':{
						"keep_selected_style" : false
					}
				});
			}
		});
	};

	var _get_selected_value = function (container) {
		var data_1 = $.jstree
			.reference($('#'+container)).get_json();
		if (!(data_1)) {return [];}
		var temp = getSelectedValue(data_1);
		temp = unique(temp);
		//制空
		selectedValue = [];
		return JSON.stringify(temp)
	}
	//js数组去重
	function unique(temp)
	{
		temp.sort();
		var re=[temp[0]];
		for(var i = 1; i < temp.length; i++)
		{
			if( temp[i] !== re[re.length-1])
			{
				re.push(temp[i]);
			}
		}
		return re;
	}
	//定义全局变量来存储选中值
	var selectedValue = [];
	//递归查询选中的菜单
	function getSelectedValue(data)
	{
		for(var i in data) {
			if (data[i]['state']['selected']) {
				selectedValue.push(data[i]['data']);
			}
			if (data[i]['state']['opened']) {
				selectedValue.push(data[i]['data']);
			}
			if (data[i]['children']) {
				getSelectedValue(data[i]['children']);
			}
		}
		return selectedValue;
	}
	
	return {
		tree : function (container, args,url) {
			_request_data(container, args,url);
		},

		selected : function (container) {
			return _get_selected_value(container);
		}
	};

}();