<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/commons/taglibs.jsp" %>
<%@ include file="/WEB-INF/views/modules/pathVariable.jsp" %>
<!DOCTYPE html>
<html>
<jsp:include page="${headerPath}"></jsp:include>

<script type="text/javascript">
      $(function(){
    	     var title="${title}";
    	     $("title").html(title);
      })
      
       function loadimage(){ 
		document.getElementById("randImage").src = "http://iecms.ec.com.cn/iecms/corp/image.jsp?"+Math.random(); 
		}
      
      function loadimagezdsb(){
    	  ROOTPATH="${ctx}";
    	  var ocrPath = ROOTPATH.replace("data", "ocr-service");
	   	   //识别验证码
	   	   $.ajax({
			   url:ocrPath+"/cjy/getVerifycode",
			   data:{"imageUrl":"${codeImageUrl}", "codeType": "1104"},
			   async:false,
		       success:function(ret){
		    	   $("#codea").val(ret);
		       }
	   	   })
      }
      

</script>
<script src="${ctx}/static/js/sweet-totop.js"></script>

<body onload="loadimagezdsb()">
   <jsp:include page="${navPath}"></jsp:include>
	
	<div data-name="text" class="container-fluid sr-zql-content">
		<div class="row">
			<!-- 左侧导航栏 -->
			<div data-name="left_menu" class="col-md-2">
			     <jsp:include page="${leftMenuPath}"></jsp:include>
			</div>
			
			<!-- 右侧主体内容 -->
			<div class="col-md-10">
				<div id="right_content" class="sr-zql-content-right sr-h-font" style="padding-left:20px;margin-right:0px">
				     
				     <!--面包屑路径 -->
				     <div data-name="breadcrumb">
                          <div id="breadcrumbPath">
                              <jsp:include page="${breadcrumbPath}"></jsp:include>
                              <script type="text/javascript">
                                   $(function(){
                                       var path_1="<a href='${iecmsIndexPath}'>贸易备案</a>";
                                       var path_2="<a href='${iecmsAuthPath}'>贸易查询</a>";
                                       $("#path_1").html(path_1);
                                       $("#path_2").html(path_2);
                                   })
                               </script>
                         </div>
                    </div>
                    
                    <!--贸易备案查询 -->
                    <div data-name="searchBox" class="row" id="mybacxdivid" >               
                         <div id="searchBox_big" class="col-md-6 col-md-offset-2" style="margin-top:3%; padding:30px; border:1px solid #EEEEEE; border-radius:7px; background-color:#FCFCFC; display:block;">						
							<form id="auth_fo" class="form-horizontal">
							  <div class="form-group">
							    <label for="corp_code" class="col-sm-2 control-label">13位经营者代码</label>
							    <div class="col-sm-7" style="padding-left:5px;">
							      <input type="text" class="form-control" id="corp_code" name="corp_code">
							    </div>
							  </div>
							  <div class="form-group">
							    <label for="corp_name" class="col-sm-2 control-label">经营者名称</label>
							    <div class="col-sm-7" style="padding-left:5px;">
							      <input type="text" class="form-control" id="corp_name" name="corp_name">
							    </div>
							  </div>
							    <div class="form-group">
							    <label for="sc_code" class="col-sm-2 control-label">统一社会信用代码</label>
							    <div class="col-sm-7" style="padding-left:5px;">
							      <input type="text" class="form-control" id="sc_code" name="sc_code">
							    </div>
							  </div>
							  <div class="form-group">
							    <label for="codea" class="col-sm-2 control-label">验证码</label>
							    <div class="col-sm-7" style="padding-left:5px;">
							      <input type="text" class="form-control" id="codea" name="codea" style="display:inline; width:110px;">
<!-- 							      <img id="codeImage" alt=" 验证码识别中..." src=""> -->
							      <img alt="codea" name="randImage" id="randImage" src="${codeImageUrl}" width="60" height="20" border="1" align="absmiddle">
<!-- 							     <a href="javascript:loadimage();"> -->
<!-- 							      <font size="2" color="#E84E24">看不清</font> -->
<!-- 							      </a> -->
                                      <input type="hidden" id="serializedFileName" name="serializedFileName" value="${serializedFileName}">
							    </div>
							  </div>
							
							  <div class="form-group" >
							    <div class="col-sm-offset-2 col-sm-7" style="padding-left:5px;">
							      <button id="excute_btn" type="button" class="btn btn-success">&nbsp&nbsp确定&nbsp&nbsp;</button>							  
							    </div>
							  </div>
							</form>
							
							<script type="text/javascript">
								$("input[name='reportCategory']").on("click", function(){
									var reportCategory = $(this).val();
									if (reportCategory=="json") {
										$("#tradecode").attr("disabled", false);
									} else if (reportCategory=="pdf") {
										$("#tradecode").attr("disabled", true);
									}
								});
							</script>
                         </div>
                                  
                    </div>
                    
                    <!--搜索结果  -->
                    <div data-name="iecms_searchResults" class="row">
                         <div id="iecms_searchResults" class="col-md-11" data-searchKey="null" >
                         	<!-- 填充搜索结果 -->
                         </div>
          
                    </div>
				</div>
			</div>
			
			
			<!-- 返回顶部 -->
			<div class="sr-zql-totop"></div>
		</div>
	</div>
	<script src="${ctx}/static/js/common.js"></script>
    <script src="${ctx}/static/js/sweet-ajax-progress.js"></script>
    <script src="${ctx}/static/js/sweet-form-collection.js"></script>
	<script type="text/javascript">
 	//点击执行按钮
  	$("#excute_btn").on("click", function(){
  		var authFormParams = collectParam("#auth_fo");  		
  		//console.log("======================authFormParams:======================\n" + JSON.stringify(authFormParams));  	  		  			
  			var corp_code=$("#corp_code").val();
  			var corp_name=$("#corp_name").val();
  			var sc_code=$("#sc_code").val();
  			var codea=$("#codea").val();
  			var serializedFileName=$("#serializedFileName").val();
  			if(codea=="") {
  				alert("输入验证码");
  			}else {
  			$.ajax({
  				async:true,
  				type:"post",
  				url:"${ctx}/api/iecms/getcreditiecms",
  				data:{'corp_code': corp_code, 'corp_name': corp_name, 'sc_code': sc_code,'codea': codea,'serializedFileName': serializedFileName},  			
  				timeout:90000, //超时时间为90s
  				success: function(data) {  	
  					var searchData = data.tbody;
  					//console.log(searchData);
  					document.getElementById("mybacxdivid").style.display="none";//隐藏
  					var htmlStr = "";
  					for(var i=0; i < searchData.length; i++) {
  						htmlStr += "<tr>";  						
  						htmlStr += "<td>"+searchData[i].jyzzwmc+"</td>";
  						htmlStr += "<td>"+searchData[i].jyzywmc+"</td>";
  						htmlStr += "<td>"+searchData[i].zs+"</td>";
  						htmlStr += "<td>"+searchData[i].yb+"</td>";
  						htmlStr += "<td>"+searchData[i].cz+"</td>";
  						htmlStr += "</tr>";
  					}
  					$("#iecms_searchResults").append(
  						"<table border='1'>" + 
  							"<thead>"+
  							"<tr>"+
  							"<th>经营者中文名称</th><th>经营者英文名称</th><th>住所</th><th>邮编</th><th>传真</th>"+
  							"</tr>"+
  							"</thead>"+
  							"<tbody>"+htmlStr+"</tbody>"+
  						"</table>"
  					);
  				},
  				error: function() {
  					
  				}
  			});
  			}
  	});
  	
	</script>
	
<jsp:include page="${footerPath}"></jsp:include>   
</body>
</html>