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
		
		json = {"data":[{"children":[{"children":[{"children":[{"children":[{"children":[],"mod_id":"","parent_id":"","text":"nice"},{"children":[],"mod_id":"","parent_id":"","text":"interrupt"},{"children":[],"mod_id":"","parent_id":"","text":"softirq"},{"children":[],"mod_id":"","parent_id":"","text":"steal"},{"children":[],"mod_id":"","parent_id":"","text":"user"},{"children":[],"mod_id":"","parent_id":"","text":"system"},{"children":[],"mod_id":"","parent_id":"","text":"idle"},{"children":[],"mod_id":"","parent_id":"","text":"wait"}],"mod_id":"","parent_id":"","text":"cpu"}],"mod_id":"","parent_id":"","text":"0"}],"mod_id":"","parent_id":"","text":"cpu"},{"children":[{"children":[{"children":[{"children":[],"mod_id":"","parent_id":"","text":"rx"},{"children":[],"mod_id":"","parent_id":"","text":"tx"}],"mod_id":"","parent_id":"","text":"if_octets"},{"children":[{"children":[],"mod_id":"","parent_id":"","text":"rx"},{"children":[],"mod_id":"","parent_id":"","text":"tx"}],"mod_id":"","parent_id":"","text":"if_packets"},{"children":[{"children":[],"mod_id":"","parent_id":"","text":"rx"},{"children":[],"mod_id":"","parent_id":"","text":"tx"}],"mod_id":"","parent_id":"","text":"if_errors"}],"mod_id":"","parent_id":"","text":"lo"},{"children":[{"children":[{"children":[],"mod_id":"","parent_id":"","text":"rx"},{"children":[],"mod_id":"","parent_id":"","text":"tx"}],"mod_id":"","parent_id":"","text":"if_octets"},{"children":[{"children":[],"mod_id":"","parent_id":"","text":"rx"},{"children":[],"mod_id":"","parent_id":"","text":"tx"}],"mod_id":"","parent_id":"","text":"if_packets"},{"children":[{"children":[],"mod_id":"","parent_id":"","text":"rx"},{"children":[],"mod_id":"","parent_id":"","text":"tx"}],"mod_id":"","parent_id":"","text":"if_errors"}],"mod_id":"","parent_id":"","text":"eth0"}],"mod_id":"","parent_id":"","text":"interface"},{"children":[{"children":[{"children":[],"mod_id":"","parent_id":"","text":"shortterm"},{"children":[],"mod_id":"","parent_id":"","text":"midterm"},{"children":[],"mod_id":"","parent_id":"","text":"longterm"}],"mod_id":"","parent_id":"","text":"load"}],"mod_id":"","parent_id":"","text":"load"},{"children":[{"children":[{"children":[],"mod_id":"","parent_id":"","text":"used"},{"children":[],"mod_id":"","parent_id":"","text":"buffered"},{"children":[],"mod_id":"","parent_id":"","text":"cached"},{"children":[],"mod_id":"","parent_id":"","text":"free"}],"mod_id":"","parent_id":"","text":"memory"}],"mod_id":"","parent_id":"","text":"memory"},{"children":[{"children":[{"children":[],"mod_id":"","parent_id":"","text":"used"},{"children":[],"mod_id":"","parent_id":"","text":"free"},{"children":[],"mod_id":"","parent_id":"","text":"cached"}],"mod_id":"","parent_id":"","text":"swap"},{"children":[{"children":[],"mod_id":"","parent_id":"","text":"in"},{"children":[],"mod_id":"","parent_id":"","text":"out"}],"mod_id":"","parent_id":"","text":"swap_io"}],"mod_id":"","parent_id":"","text":"swap"},{"children":[{"children":[{"children":[{"children":[],"mod_id":"","parent_id":"","text":"free"},{"children":[],"mod_id":"","parent_id":"","text":"reserved"},{"children":[],"mod_id":"","parent_id":"","text":"used"}],"mod_id":"","parent_id":"","text":"df_complex"}],"mod_id":"","parent_id":"","text":"mapper_VolGroup-lv_root"},{"children":[{"children":[{"children":[],"mod_id":"","parent_id":"","text":"free"},{"children":[],"mod_id":"","parent_id":"","text":"reserved"},{"children":[],"mod_id":"","parent_id":"","text":"used"}],"mod_id":"","parent_id":"","text":"df_complex"}],"mod_id":"","parent_id":"","text":"sda1"}],"mod_id":"","parent_id":"","text":"df"},{"children":[{"children":[{"children":[],"mod_id":"","parent_id":"","text":"running"},{"children":[],"mod_id":"","parent_id":"","text":"sleeping"},{"children":[],"mod_id":"","parent_id":"","text":"zombies"},{"children":[],"mod_id":"","parent_id":"","text":"stopped"},{"children":[],"mod_id":"","parent_id":"","text":"paging"},{"children":[],"mod_id":"","parent_id":"","text":"blocked"}],"mod_id":"","parent_id":"","text":"ps_state"},{"children":[],"mod_id":"","parent_id":"","text":"fork_rate"},{"children":[{"children":[],"mod_id":"","parent_id":"","text":"ps_vm"},{"children":[],"mod_id":"","parent_id":"","text":"ps_rss"},{"children":[],"mod_id":"","parent_id":"","text":"ps_data"},{"children":[],"mod_id":"","parent_id":"","text":"ps_code"},{"children":[],"mod_id":"","parent_id":"","text":"ps_stacksize"},{"children":[{"children":[],"mod_id":"","parent_id":"","text":"user"},{"children":[],"mod_id":"","parent_id":"","text":"syst"}],"mod_id":"","parent_id":"","text":"ps_cputime"},{"children":[{"children":[],"mod_id":"","parent_id":"","text":"processes"},{"children":[],"mod_id":"","parent_id":"","text":"threads"}],"mod_id":"","parent_id":"","text":"ps_count"},{"children":[{"children":[],"mod_id":"","parent_id":"","text":"minflt"},{"children":[],"mod_id":"","parent_id":"","text":"majflt"}],"mod_id":"","parent_id":"","text":"ps_pagefaults"}],"mod_id":"","parent_id":"","text":"httpd"}],"mod_id":"","parent_id":"","text":"processes"},{"children":[{"children":[{"children":[{"children":[],"mod_id":"","parent_id":"","text":"read"},{"children":[],"mod_id":"","parent_id":"","text":"write"}],"mod_id":"","parent_id":"","text":"disk_octets"},{"children":[{"children":[],"mod_id":"","parent_id":"","text":"read"},{"children":[],"mod_id":"","parent_id":"","text":"write"}],"mod_id":"","parent_id":"","text":"disk_ops"},{"children":[{"children":[],"mod_id":"","parent_id":"","text":"read"},{"children":[],"mod_id":"","parent_id":"","text":"write"}],"mod_id":"","parent_id":"","text":"disk_time"},{"children":[{"children":[],"mod_id":"","parent_id":"","text":"read"},{"children":[],"mod_id":"","parent_id":"","text":"write"}],"mod_id":"","parent_id":"","text":"disk_merged"}],"mod_id":"","parent_id":"","text":"sda"},{"children":[{"children":[{"children":[],"mod_id":"","parent_id":"","text":"read"},{"children":[],"mod_id":"","parent_id":"","text":"write"}],"mod_id":"","parent_id":"","text":"disk_octets"},{"children":[{"children":[],"mod_id":"","parent_id":"","text":"read"},{"children":[],"mod_id":"","parent_id":"","text":"write"}],"mod_id":"","parent_id":"","text":"disk_ops"},{"children":[{"children":[],"mod_id":"","parent_id":"","text":"read"},{"children":[],"mod_id":"","parent_id":"","text":"write"}],"mod_id":"","parent_id":"","text":"disk_time"},{"children":[{"children":[],"mod_id":"","parent_id":"","text":"read"},{"children":[],"mod_id":"","parent_id":"","text":"write"}],"mod_id":"","parent_id":"","text":"disk_merged"}],"mod_id":"","parent_id":"","text":"sda1"},{"children":[{"children":[{"children":[],"mod_id":"","parent_id":"","text":"read"},{"children":[],"mod_id":"","parent_id":"","text":"write"}],"mod_id":"","parent_id":"","text":"disk_octets"},{"children":[{"children":[],"mod_id":"","parent_id":"","text":"read"},{"children":[],"mod_id":"","parent_id":"","text":"write"}],"mod_id":"","parent_id":"","text":"disk_ops"},{"children":[{"children":[],"mod_id":"","parent_id":"","text":"read"},{"children":[],"mod_id":"","parent_id":"","text":"write"}],"mod_id":"","parent_id":"","text":"disk_time"},{"children":[{"children":[],"mod_id":"","parent_id":"","text":"read"},{"children":[],"mod_id":"","parent_id":"","text":"write"}],"mod_id":"","parent_id":"","text":"disk_merged"}],"mod_id":"","parent_id":"","text":"sda2"},{"children":[{"children":[{"children":[],"mod_id":"","parent_id":"","text":"read"},{"children":[],"mod_id":"","parent_id":"","text":"write"}],"mod_id":"","parent_id":"","text":"disk_octets"},{"children":[{"children":[],"mod_id":"","parent_id":"","text":"read"},{"children":[],"mod_id":"","parent_id":"","text":"write"}],"mod_id":"","parent_id":"","text":"disk_ops"},{"children":[{"children":[],"mod_id":"","parent_id":"","text":"read"},{"children":[],"mod_id":"","parent_id":"","text":"write"}],"mod_id":"","parent_id":"","text":"disk_time"},{"children":[{"children":[],"mod_id":"","parent_id":"","text":"read"},{"children":[],"mod_id":"","parent_id":"","text":"write"}],"mod_id":"","parent_id":"","text":"disk_merged"}],"mod_id":"","parent_id":"","text":"dm-0"},{"children":[{"children":[{"children":[],"mod_id":"","parent_id":"","text":"read"},{"children":[],"mod_id":"","parent_id":"","text":"write"}],"mod_id":"","parent_id":"","text":"disk_octets"},{"children":[{"children":[],"mod_id":"","parent_id":"","text":"read"},{"children":[],"mod_id":"","parent_id":"","text":"write"}],"mod_id":"","parent_id":"","text":"disk_ops"},{"children":[{"children":[],"mod_id":"","parent_id":"","text":"read"},{"children":[],"mod_id":"","parent_id":"","text":"write"}],"mod_id":"","parent_id":"","text":"disk_time"},{"children":[{"children":[],"mod_id":"","parent_id":"","text":"read"},{"children":[],"mod_id":"","parent_id":"","text":"write"}],"mod_id":"","parent_id":"","text":"disk_merged"}],"mod_id":"","parent_id":"","text":"dm-1"}],"mod_id":"","parent_id":"","text":"disk"}],"mod_id":"","parent_id":"","text":"172_20_1_96"}],"message":"","status":"1"};
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