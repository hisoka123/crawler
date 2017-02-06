<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!-- 按钮触发模态框 -->
<button data-toggle="modal" data-target="#toolsModel" style="display:none;"></button>
<!-- 模态框（Modal） -->
<div class="modal fade" id="toolsModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h5 class="modal-title" id="myModalLabel">编码转换工具：</h5>
         </div>
         <div class="modal-body">
         		<div class="row">
		         	<div class="col-md-5">
						<textarea name="precode_text" style="border-radius:4px;resize:none;" rows="10" cols="27" placeholder="请输入..."></textarea>
					</div>
					<div class="col-md-2" style="padding-left:0;">
						<div style="margin:60px auto;">
							<div class="dropdown">
							  <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">编码/解码<span class="caret"></span></button>
							  <ul class="dropdown-menu" aria-labelledby="dropdownMenu2">
							    <li><a href="javascript:void(0);" onclick="codeOpt('encodeURI')"><i class="glyphicon glyphicon-triangle-right"></i> encodeURI</a></li>
							    <li><a href="javascript:void(0);" onclick="codeOpt('encodeURIComponent')"><i class="glyphicon glyphicon-triangle-right"></i> encodeURIComponent</a></li>
							    <li><a href="javascript:void(0);" onclick="codeOpt('unicode')"><i class="glyphicon glyphicon-triangle-right"></i> unicode</a></li>
							    <li><a href="javascript:void(0);" onclick="codeOpt('decodeURI')"><i class="glyphicon glyphicon-triangle-left"></i> decodeURI</a></li>
							    <li><a href="javascript:void(0);" onclick="codeOpt('decodeURIComponent')"><i class="glyphicon glyphicon-triangle-left"></i> decodeURIComponent</a></li>
							    <li><a href="javascript:void(0);" onclick="codeOpt('deunicode')"><i class="glyphicon glyphicon-triangle-left"></i> deunicode</a></li>
							  </ul>
							</div>
						</div>
					</div>
					<div class="col-md-5">
						<textarea name="aftercode_text" style="border-radius:4px;resize:none;" rows="10" cols="27"></textarea>
					</div>
				</div>
		 </div>
         <div class="modal-footer"></div>
      </div>
	</div>
</div>

<script>
	var codeOpt = function(codeOpt) {
		var precode_text = $("textarea[name='precode_text']").val();
		var aftercode_text = "";
		
		if (precode_text=="") {
			return;
		} else if (codeOpt=="encodeURI") {
			aftercode_text = encodeURI(precode_text);
		} else if (codeOpt=="encodeURIComponent") {
			aftercode_text = encodeURIComponent(precode_text);
		} else if (codeOpt=="unicode") {
			var res=[];
		    for(var i=0;i < precode_text.length;i++)
		        res[i]=("00"+precode_text.charCodeAt(i).toString(16)).slice(-4);
		    aftercode_text = "\\u"+res.join("\\u");
		} else if (codeOpt=="decodeURI") {
			aftercode_text = decodeURI(precode_text);
		} else if (codeOpt=="decodeURIComponent") {
			aftercode_text = decodeURIComponent(precode_text);
		} else if (codeOpt=="deunicode") {
			aftercode_text = unescape(precode_text.replace(/\\/g,"%"));
		}
		$("textarea[name='aftercode_text']").val(aftercode_text);
	}
</script>

