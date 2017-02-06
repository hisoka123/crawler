<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/commons/taglibs.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<script src="${ctx}/static/js/jquery-2.1.4.min.js"></script>
<script src="${ctx}/static/js/echarts/echarts.js"></script>
<script type="text/javascript">
	//main函数
	$(function() {
		//路径配置
		require.config({
			paths : {
				echarts : '${ctx}/static/js/echarts'
			}
		});
		// 使用
		require(
		   [ 'echarts', 
		     'echarts/chart/bar', //使用柱状图就加载bar模块，按需加载
		   ], 
		   function(ec) {
			   //基于准备好的dom，初始化echarts图表
			   var myChart = ec.init(document.getElementById('main'));
			   //设置数据
			   var option = {
				  //设置坐标轴
			      xAxis : [
			        {
			          type : 'category',
			          data : ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子","帽子","围巾"],
			          axisTick : {
			    		  inside:true
			    	  }
			        }
			      ],
			      yAxis : [
			        {
			          type : 'value'
			        }
			      ],
			      //设置数据
			      series : [
			        {
			          "name":"销量",
			          "type":"bar",
			          "data":[5, 20, 40, 10, 24, 20,24,32],
			        }
			      ],
			      
			   };
			   //为echarts对象加载数据 
			   myChart.setOption(option);
		});

	});
</script>
<script type="text/javascript">
	$(function() {
		$("#test2").html("<h1>呵呵呵呵</h1>"); //动态页面
		var url = "${url}";
		$("#link").attr("href", url);
		$("#urltest").text(url);
	});
	
</script>
</head>
<body>
	<div id="main" style="width: 600px; height: 400px"></div>
	<div id="test2"></div>
	
	<a href="" id="link">链接1</a>
	<form action="${ctx}/api/weibo/echartTest" method="get">
		<textarea id="urltest" name="url"></textarea><br/>
		<input type="submit" value="提交"/>
	</form>
</body>
</html>