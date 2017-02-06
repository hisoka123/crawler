<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/commons/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>${title}--LinkedIn</title>
</head>
<body>
	<hr/>
	<input type="button" name="start" id="start" value="start/stop"/><br/><br/>
	<input type="button" name="on" id="on" value="pause/on"/><br/><br/>
	<input type="button" name="addprofileOn" id="addprofileOn" value="addprofilePause/addprofileOn"/>
	<hr/>
	
	<script type="text/javascript" src="${ctx}/static/js/jquery-2.1.4.min.js"></script>
	<script type="text/javascript">
		var start = true;
		var on = false;
		var addprofileOn = false;
		
		var sentSignalByAjax = function(url, signal) {
			$.ajax({
				type: 'GET',
				dataType: 'text',
				url: url + "?signal=" + signal,
				success: function(data) {
					alert(data);
				}
			});			
		}
		var inverseSignal = function(signal) {
			if (signal) {
				signal = false;
			} else {
				signal = true;
			}
			return signal;
		}
		
		$(function() {
			$("#start").on("click", function() {
				sentSignalByAjax("${ctx}/api/linkedin/task/start", start);
				start = inverseSignal(start);
			});
			$("#on").on("click", function() {
				sentSignalByAjax("${ctx}/api/linkedin/task/on", on);
				on = inverseSignal(on);
			});
			$("#addprofileOn").on("click", function() {
				sentSignalByAjax("${ctx}/api/linkedin/task/addprofileOn", addprofileOn);
				addprofileOn = inverseSignal(addprofileOn);
			});
		});
	</script>
</body>
</html>