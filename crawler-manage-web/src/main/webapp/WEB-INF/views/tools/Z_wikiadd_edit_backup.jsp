<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<h3 class="page-header" style="margin-top:0;" contenteditable="false">说明</h3>
<p onclick="contenteditable(this);">请填写接口说明</p>
<h3 class="page-header" contenteditable="false">URL</h3>
<p id="path"><span>http://&lt;Project&gt;</span>&nbsp;<span onclick="contenteditable(this);">请填写正确的请求路径</span></p>
<h3 class="page-header" contenteditable="false">格式</h3>
<p onclick="contenteditable(this);">请填写数据格式</p>
<h3 class="page-header" contenteditable="false">HTTP请求方式</h3>
<p onclick="contenteditable(this);">请填写请求方式</p>
<h3 class="page-header" contenteditable="false">请求参数</h3>
<div style="padding-right: 30%;">
	<div onclick="contenteditable(this);">
	    <table class="table table-bordered table-hover" style="margin-bottom:0" id="paramsTable">
	    	<caption>请求参数对照表：</caption>
	        <thead>
	        <tr>
	            <th>#</th>
	            <th>必选（T/F）</th>
	            <th>类型及范围</th>
	            <th>说明</th>
	            <th>操作</th>
	        </tr>
	        </thead>
	        <tbody>
	        <tr>
	            <td></td>
	            <td></td>
	            <td></td>
	            <td></td>
	            <td><a style="cursor:pointer">删除</a></td>
	        </tr>
	        </tbody>
	    </table>
	    <i class="glyphicon glyphicon-triangle-bottom" style="display:none"></i>
    </div>
    <div style="margin-top:8px;" onclick="contenteditable(this);">
    	<p style="font-size:12px;">附注：</p>
    </div>
</div>
<h3 class="page-header" contenteditable="false">注意事项</h3>
<p onclick="contenteditable(this);">
	请输入注意事项的内容
</p>
<h3 class="page-header" contenteditable="false">例子</h3>
<p onclick="contenteditable(this);">说明：</p>
<p onclick="contenteditable(this);">请求：</p>
<p onclick="contenteditable(this);">返回：</p>
<p>示例：</p>
<pre class="prettyprint sr-zql-pre-without-border" onclick="contenteditable(this);">
请输入示例数据：
</pre>
<h3 class="page-header" contenteditable="false">返回字段说明</h3>
<div style="padding-right: 30%;">
	<div onclick="contenteditable(this);">
	    <table class="table table-bordered table-hover" style="margin-bottom:0">
	    	<caption>返回字段对照表：</caption>
	        <thead>
	        <tr>
	            <th>#</th>
	            <th>类型及范围</th>
	            <th>说明</th>
	            <th>操作</th>
	        </tr>
	        </thead>
	        <tbody>
	        <tr>
	            <td></td>
	            <td></td>
	            <td></td>
	            <td><a style="cursor:pointer">删除</a></td>
	        </tr>
	        </tbody>
	    </table>
	    <i class="glyphicon glyphicon-triangle-bottom" style="display:none"></i>
    </div>
    <div style="margin-top:8px;" onclick="contenteditable(this);">
    	<p style="font-size:12px;">附注：</p>
    </div>
</div>
