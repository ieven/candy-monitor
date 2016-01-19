var beginTimeRange = '-30minutes';
var endTimeRange = 'now';

var xData = [];
var yData = [];
var charts = function () {
	
	var _lineCharts = function (target) {
		var param = {
			from : beginTimeRange,
			until : endTimeRange,
			target : target
		};
		$.ajax({
			url : "render", //要访问的后台地址
			type : "POST", //使用post方法访问后台
			data : param, //要发送的数据
			dataType : 'JSON',//here
			success : function(data) {
				drawLineCharts(data,target);
			}
		});
	}
	
	function drawLineCharts(data,target)
	{
		// 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('charsId'));
        // 过渡---------------------
        myChart.showLoading({
            text: '正在努力的读取数据中...',   
        });
        //处理返回的数据
        handlerData(data);
        // 指定图表的配置项和数据
        var option = {
            title: {
            	show: false,
                text: target
            },
            tooltip : {
			    trigger: 'axis'
			},
            legend: {
                data:[target]
            },
            xAxis: {
                data: xData
            },
            yAxis: {},
            series: [{
                name: target,
                type: 'line',
                data: yData
            }]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
        myChart.hideLoading();
	}
	
	function handlerData(data)
	{
		//清空上次数据
		xData = [];
		yData = [];
		for(var i=0;i<data[0].datapoints.length;i++)
		{
			xData.push(new Date(data[0].datapoints[i][1]*1000).format("hh:mm:ss"));
			yData.push(data[0].datapoints[i][0]);
		}
	}
	
	Date.prototype.format = function(format) {
	    var o = {
	        "M+": this.getMonth() + 1,
	        //month
	        "d+": this.getDate(),
	        //day
	        "h+": this.getHours(),
	        //hour
	        "m+": this.getMinutes(),
	        //minute
	        "s+": this.getSeconds(),
	        //second
	        "q+": Math.floor((this.getMonth() + 3) / 3),
	        //quarter
	        "S": this.getMilliseconds() //millisecond
	    };
	    if (/(y+)/.test(format)) format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	    for (var k in o) if (new RegExp("(" + k + ")").test(format)) format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
	    return format;
	};
	
	return {
		lineCharts : function(target){
			_lineCharts(target);
		}
	};
}();