<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/commons/taglibs.jsp" %>
<%@ include file="/WEB-INF/views/modules/pathVariable.jsp" %>

<ul id="left_menu" class="nav nav-tabs nav-stacked sr-zql-main-nav" style="border:0;width:90%;display:none">
	     <li id="person_menu_search" class="sr-h-optionTint sr-h-menuOption">
	          <a href="${personSearchPath}"> <i class="glyphicon glyphicon-search"></i>&nbsp;&nbsp;人物搜索</a>
	     </li>
	     <li id="sina_menu">
			  <a href="#sina_menuList" class="nav-header collapsed" data-toggle="collapse"> <i class="sr-icon-sina-12"></i> 新浪微博<span class="pull-right glyphicon glyphicon-chevron-down"></span></a>
				 <ul id="sina_menuList" class="nav nav-list collapse sr-zql-secondmenu">
					 <li id="sina_menu_personSearch" ><a  href="${sinaSearchPath}"><i class="glyphicon glyphicon-search"></i> &nbsp;搜索用户</a></li>
					 <li id="sina_menu_personInfoMenu" ><a  href="#sina_userInfo"><i class="glyphicon glyphicon-list-alt"></i>&nbsp;人物列表</a></li>
				</ul>
		 </li>
		 <li id="baidu_menu">
			  <a href="#baidu_menuList" class="nav-header collapsed" data-toggle="collapse"> <i class="sr-icon-baidu-12"></i>百度百科<span class="pull-right glyphicon glyphicon-chevron-down"></span></a>
		      <ul id="baidu_menuList" class="nav nav-list collapse sr-zql-secondmenu">
			      <li id="baidu_menu_personSearch" ><a  href="${baiduSearchPath}"><i class="glyphicon glyphicon-search"></i>&nbsp; 搜索用户</a></li>
			  </ul>
		 </li>
		 <li id="zhihu_menu">
			  <a href="#zhihu_menuList" class="nav-header collapsed" data-toggle="collapse"> <i class="sr-icon-zhihu-12"></i>知乎<span class="pull-right glyphicon glyphicon-chevron-down"></span></a>
			  <ul id="zhihu_menuList" class="nav nav-list collapse sr-zql-secondmenu">
				  <li id="zhihu_menu_personSearch"><a  href="${zhihuSearchPath}"><i class="glyphicon glyphicon-search"></i>&nbsp; 搜索用户</a></li>
			  </ul>
		 </li>
		 <li id="weixin_menu">
			  <a href="#weixin_menuList" class="nav-header collapsed" data-toggle="collapse"> <i class="sr-icon-weixin-12"></i>微信<span class="pull-right glyphicon glyphicon-chevron-down"></span></a>
			  <ul id="weixin_menuList" class="nav nav-list collapse sr-zql-secondmenu">
				  <li id="weixin_menu_personSearch" ><a  href="${weixinSearchPath}"><i class="glyphicon glyphicon-search"></i> &nbsp;搜索用户</a></li>
		      </ul>
		 </li>
		 <li id="linkedIn_menu">
			  <a href="#linkedIn_menuList" class="nav-header collapsed" data-toggle="collapse"> <i class="sr-icon-linkedin-12"></i>领英<span class="pull-right glyphicon glyphicon-chevron-down"></span></a>
				 <ul id="linkedIn_menuList" class="nav nav-list collapse sr-zql-secondmenu">
					 <li id="linkedIn_menu_personSearch"><a  href="${linkedInSearchPath}"><i class="glyphicon glyphicon-search"></i> &nbsp;搜索用户</a></li>
				</ul>
		 </li>
		 <li id="pbccrc_menu">
			  <a href="#pbccrc_menuList" class="nav-header collapsed" data-toggle="collapse"> <i class="glyphicon glyphicon-bookmark"></i> 人行征信<span class="pull-right glyphicon glyphicon-chevron-down"></span></a>
				 <ul id="pbccrc_menuList" class="nav nav-list collapse sr-zql-secondmenu">
					 <li id="pbccrc_menu_pbccrcAuth"><a  href="${pbccrcAuthPath}"><i class="glyphicon glyphicon-log-in"></i> &nbsp;身份验证</a></li>
					 <li id="pbccrc_menu_getCredit"><a  href="${pbccrcGetCreditPath}"><i class="glyphicon glyphicon-info-sign"></i> &nbsp;获取报告</a></li>
					 <li id="pbccrc_menu_getPDFCredit"><a  href="${pbccrcGetPDFCreditPath}"><i class="glyphicon glyphicon-open-file"></i> &nbsp;生成PDF</a></li>
				</ul>
		 </li>	 
		 
		  <li id="gsxt_menu">
			  <a href="#gsxt_menuList" class="nav-header collapsed" data-toggle="collapse"> <i class="glyphicon glyphicon-bookmark"></i> 工商网<span class="pull-right glyphicon glyphicon-chevron-down"></span></a>
				 <ul id="gsxt_menuList" class="nav nav-list collapse sr-zql-secondmenu">
					 <li id="gsxt_menu_companySearch" ><a  href="${gsxtSearchPath}"><i class="glyphicon glyphicon-search"></i> &nbsp;搜索企业</a></li>					 
				</ul>
		  </li>
		 
		 <li id="renfawang_menu">
			  <a href="#renfawang_menuList" class="nav-header collapsed" data-toggle="collapse"> <i class="glyphicon glyphicon-bookmark"></i> 人法网<span class="pull-right glyphicon glyphicon-chevron-down"></span></a>
			  <ul id="renfawang_menuList" class="nav nav-list collapse sr-zql-secondmenu">
				  <li id="renfawang_menu_personSearch" ><a  href="${renfawangSearchPath}"><i class="glyphicon glyphicon-search"></i> &nbsp;搜索个人和企业</a></li>
		      </ul>
		 </li>
		 
		 
		 <li id="iecms_menu">
			  <a href="#iecms_menuList" class="nav-header collapsed" data-toggle="collapse"> <i class="glyphicon glyphicon-bookmark"></i> 贸易备案<span class="pull-right glyphicon glyphicon-chevron-down"></span></a>
				 <ul id="iecms_menuList" class="nav nav-list collapse sr-zql-secondmenu">
					 <li id="iecms_menu_pbccrcAuth"><a  href="${iecmsAuthPath}"><i class="glyphicon glyphicon-log-in"></i> &nbsp;贸易备案查询</a></li>
				</ul>
		 </li>
		 
		 <li id="dailianmeng_menu">
			  <a href="#dailianmeng_menuList" class="nav-header collapsed" data-toggle="collapse"> <i class="glyphicon glyphicon-bookmark"></i> 贷联盟<span class="pull-right glyphicon glyphicon-chevron-down"></span></a>
			  <ul id="dailianmeng_menuList" class="nav nav-list collapse sr-zql-secondmenu">
				  <li id="dailianmeng_menu_personSearch" ><a  href="${dailianmengSearchPath}"><i class="glyphicon glyphicon-search"></i> &nbsp;搜索用户</a></li>
		      </ul>
		 </li>
		 
		 			 
		 
		  <li id="zjsfgkw_menu">
			  <a href="#zjsfgkw_menuList" class="nav-header collapsed" data-toggle="collapse"> <i class="glyphicon glyphicon-bookmark"></i>浙法网<span class="pull-right glyphicon glyphicon-chevron-down"></span></a>
			  <ul id="zjsfgkw_menuList" class="nav nav-list collapse sr-zql-secondmenu">
				  <li id="zjsfgkw_menu_personSearch" ><a  href="${zjsfgkwSearchPath}"><i class="glyphicon glyphicon-search"></i> &nbsp;搜索</a></li>
		      </ul>
		 </li>
		 
		   <li id="shixin_menu">
			  <a href="#shixin_menuList" class="nav-header collapsed" data-toggle="collapse"> <i class="glyphicon glyphicon-bookmark"></i>失信网<span class="pull-right glyphicon glyphicon-chevron-down"></span></a>
			  <ul id="shixin_menuList" class="nav nav-list collapse sr-zql-secondmenu">
				  <li id="shixin_menu_personSearch" ><a  href="${shixinSearchPath}"><i class="glyphicon glyphicon-search"></i> &nbsp;搜索</a></li>
		      </ul>
		 </li>
		 
		 <!-- 信用中国 -->
		 <li id="creditchina_menu">
			  <a href="#creditchina_menuList" class="nav-header collapsed" data-toggle="collapse"> <i class="glyphicon glyphicon-bookmark"></i>信用中国<span class="pull-right glyphicon glyphicon-chevron-down"></span></a>
		      <ul id="creditchina_menuList" class="nav nav-list collapse sr-zql-secondmenu">
			      <li id="creditchina_menu_getResult" ><a href="${creditchinaSearchPath}"><i class="glyphicon glyphicon-search"></i>&nbsp;搜索信息</a></li>
			  </ul>
		 </li>
		 
		   <li id="fang_menu">
			  <a href="#fang_menuList" class="nav-header collapsed" data-toggle="collapse"> <i class="glyphicon glyphicon-bookmark"></i> 搜房网<span class="pull-right glyphicon glyphicon-chevron-down"></span></a>
				 <ul id="fang_menuList" class="nav nav-list collapse sr-zql-secondmenu">
					 <li id="fang_menu_companySearch" ><a  href="${fangSearchPath}"><i class="glyphicon glyphicon-search"></i> &nbsp;房价搜索</a></li>					 
				</ul>
		 </li>
		   <li id="sxjlcxpt_menu">
			  <a href="#sxjlcxpt_menuList" class="nav-header collapsed" data-toggle="collapse"> <i class="glyphicon glyphicon-bookmark"></i>失信记录查询<span class="pull-right glyphicon glyphicon-chevron-down"></span></a>
				 <ul id="sxjlcxpt_menuList" class="nav nav-list collapse sr-zql-secondmenu">
					 <li id="sxjlcxpt_menu_companySearch" ><a  href="${sxjlcxptSearchPath}"><i class="glyphicon glyphicon-search"></i> &nbsp;失信记录搜索</a></li>					 
				</ul>
		 </li>
		   <li id="iautos_menu">
			  <a href="#iautos_menuList" class="nav-header collapsed" data-toggle="collapse"> <i class="glyphicon glyphicon-bookmark"></i> 第一车网<span class="pull-right glyphicon glyphicon-chevron-down"></span></a>
				 <ul id="iautos_menuList" class="nav nav-list collapse sr-zql-secondmenu">
					 <li id="iautos_menu_companySearch" ><a  href="${iautosSearchPath}"><i class="glyphicon glyphicon-search"></i> &nbsp;搜索汽车名称类型</a></li>					 
				</ul>
		 </li>
		 
		 <li id="customs_menu">
			  <a href="#customs_menuList" class="nav-header collapsed" data-toggle="collapse"> <i class="glyphicon glyphicon-bookmark"></i>海关网<span class="pull-right glyphicon glyphicon-chevron-down"></span></a>
		      <ul id="customs_menuList" class="nav nav-list collapse sr-zql-secondmenu">
			      <li id="customs_menu_getResult" ><a href="${customsSearchPath}"><i class="glyphicon glyphicon-search"></i>&nbsp;搜索企业</a></li>
			  </ul>
		 </li>
	     <li id="sipo_menu">
			  <a href="#sipo_menuList" class="nav-header collapsed" data-toggle="collapse"> <i class="glyphicon glyphicon-bookmark"></i> 专利网<span class="pull-right glyphicon glyphicon-chevron-down"></span></a>
				 <ul id="sipo_menuList" class="nav nav-list collapse sr-zql-secondmenu">
					 <li id="sipo_menu_companySearch" ><a  href="${sipoSearchPath}"><i class="glyphicon glyphicon-search"></i> &nbsp;搜索企业</a></li>					 
				</ul>                                                
		 </li>
		 <li id="cnca_menu">
			  <a href="#cnca_menuList" class="nav-header collapsed" data-toggle="collapse"> <i class="glyphicon glyphicon-bookmark"></i> 认证网<span class="pull-right glyphicon glyphicon-chevron-down"></span></a>
		      <ul id="cnca_menuList" class="nav nav-list collapse sr-zql-secondmenu">
			      <li id="cnca_menu_getResult" ><a href="${cncaSearchPath}"><i class="glyphicon glyphicon-search"></i>&nbsp;认证信息查询</a></li>
			  </ul>
		 </li>
		<li id="fahaicc_menu">
		<a href="#fahaicc_menuList" class="nav-header collapsed" data-toggle="collapse"> <i class="glyphicon glyphicon-bookmark"></i> 法海风控<span class="pull-right glyphicon glyphicon-chevron-down"></span></a>
		<ul id="fahaicc_menuList" class="nav nav-list collapse sr-zql-secondmenu">
			<li id="fahaicc_menu_companySearch" ><a  href="${fahaiccSearchPath}"><i class="glyphicon glyphicon-search"></i> &nbsp;搜索</a></li>
		</ul>
		</li>
		 <li id="five_search_combine_menu">
			 <a href="#five_search_combine_menuList" class="nav-header collapsed" data-toggle="collapse"> <i class="glyphicon glyphicon-bookmark"></i>五网联查<span class="pull-right glyphicon glyphicon-chevron-down"></span></a>
			 <ul id="five_search_combine_menuList" class="nav nav-list collapse sr-zql-secondmenu">
				 <li id="baidu_menu_getResult" ><a href="${fUnionSearchPath}"><i class="glyphicon glyphicon-search"></i>&nbsp;五网联查</a></li>
			 </ul>
		 </li>

		<%--  <li id="taobao_menu">
			  <a href="#taobao_menuList" class="nav-header collapsed" data-toggle="collapse"> <i class="sr-icon-linkedin-12"></i>淘宝<span class="pull-right glyphicon glyphicon-chevron-down"></span></a>
				 <ul id="taobao_menuList" class="nav nav-list collapse sr-zql-secondmenu">
					 <li id="taobao_menu_personSearch"><a  href="${taobaoSearchPath}"><i class="glyphicon glyphicon-search"></i> &nbsp;搜索用户</a></li>
				</ul>
		 </li>
		 <li id="youku_menu">
			  <a href="#youku_menuList" class="nav-header collapsed" data-toggle="collapse"> <i class="sr-icon-linkedin-12"></i>优酷<span class="pull-right glyphicon glyphicon-chevron-down"></span></a>
				 <ul id="youku_menuList" class="nav nav-list collapse sr-zql-secondmenu">
					 <li id="youku_menu_personSearch"><a  href="${youkuSearchPath}"><i class="glyphicon glyphicon-search"></i> &nbsp;搜索用户</a></li>
				</ul>
		 </li>
		 <li id="youtube_menu">
			  <a href="#youtube_menuList" class="nav-header collapsed" data-toggle="collapse"> <i class="sr-icon-linkedin-12"></i>You TuBe<span class="pull-right glyphicon glyphicon-chevron-down"></span></a>
				 <ul id="youtube_menuList" class="nav nav-list collapse sr-zql-secondmenu">
					 <li id="youtube_menu_personSearch"><a  href="${youtubeSearchPath}"><i class="glyphicon glyphicon-search"></i> &nbsp;搜索用户</a></li>
				</ul>
		 </li>
		 <li id="facebook_menu">
			  <a href="#facebook_menuList" class="nav-header collapsed" data-toggle="collapse"> <i class="sr-icon-linkedin-12"></i>Facebook<span class="pull-right glyphicon glyphicon-chevron-down"></span></a>
				 <ul id="facebook_menuList" class="nav nav-list collapse sr-zql-secondmenu">
					 <li id="facebook_menu_personSearch"><a  href="${facebookSearchPath}"><i class="glyphicon glyphicon-search"></i> &nbsp;搜索用户</a></li>
				</ul>
		 </li> --%>
		 
		 
		 
</ul>

<input id="ctx" value="${ctx}" style="display:none">