<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<div style="height:48px;border:1px solid #ddd;background-color:#f5f5f5;">
</div>
<div class="list-group" style="text-align:center">
          <a id="left_default_active" href="#" class="list-group-item">关注人物搜索</a>
          <a href="#" class="list-group-item">关注人物列表</a>
          <a href="#" class="list-group-item">左侧菜单左侧</a>
          <a href="#" class="list-group-item">左侧菜单左侧</a>
          <a href="#" class="list-group-item">左侧菜单左侧</a>
          <a href="#" class="list-group-item">左侧菜单左侧</a>
          <a href="#" class="list-group-item">左侧菜单左侧</a>
</div>
<script type="text/javascript">
      $(function(){
	
             $("#left_default_active").addClass("active");
             $("#left_menu .list-group .list-group-item").click(function(){
    	               $("#left_menu .list-group").find("a").removeClass("active");
    	               $(this).addClass("active");
             })
      })

</script>
