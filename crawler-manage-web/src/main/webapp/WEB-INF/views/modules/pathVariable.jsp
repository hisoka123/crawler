<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	 
    String jspPath="/WEB-INF/views";    
    
    /*Jsp Path 网页插入路径*/
    /*Jsp Path 网页插入路径*/
    String headerPath=jspPath+"/header.jsp";
    String navPath=jspPath+"/nav.jsp";
    String footerPath=jspPath+"/footer.jsp";
    String panel=jspPath+"/admin/commons/panel.jsp";
    String loadPath=jspPath+"/modules/i-load.jsp";
    
    
    //modules jsp
    String leftMenuPath=jspPath+"/modules/i-leftMenu.jsp";
    String breadcrumbPath=jspPath+"/modules/i-breadcrumb.jsp";
    String searchBoxBigPath=jspPath+"/modules/i-searchBoxBig.jsp";
    String searchBoxSmallPath=jspPath+"/modules/i-searchBoxSmall.jsp";

    
    //左侧菜单导航栏对应的页面插入见common.js
    //综合人物搜索页
    
    
    
    
    /*Spring MVC  网页跳转路径*/
    String rootPath = request.getContextPath(); 
    
    //主页
    String modulesIndexPath=rootPath+"/index";
    
    //综合人物搜索
    String personSearchPath=rootPath+"/modules/personSearch";
    String personDetailsPath=rootPath+"/modules/personDetails";
    
    //新浪
    String sinaIndexPath=rootPath+"/modules/sinaweibo";
    String sinaSearchPath=rootPath+"/modules/sinaweibo/sinaSearch";
    
    
    //百度百科
    String baiduIndexPath=rootPath+"/modules/baidubaike";
    String baiduSearchPath=rootPath+"/modules/baidubaike/baiduSearch";
    
    
    //知乎
    String zhihuIndexPath=rootPath+"/modules/zhihu";
    String zhihuSearchPath=rootPath+"/modules/zhihu/zhihuSearch";
    
   
    //微信
    String weixinIndexPath=rootPath+"/modules/weixin";
    String weixinSearchPath=rootPath+"/modules/weixin/weixinSearch";
    
    
    //linkedIn
    String linkedInIndexPath=rootPath+"/modules/linkedIn";
    String linkedInSearchPath=rootPath+"/modules/linkedIn/linkedInSearch";
    String linkedInDetailPath=rootPath+"/modules/linkedIn/linkedInDetail";
    
    
    
    //淘宝
    String taobaoIndexPath=rootPath+"/modules/taobao";
    String taobaoSearchPath=rootPath+"/modules/taobao/taobaoSearch";
    
    
    //优酷
    String youkuIndexPath=rootPath+"/modules/youku";
    String youkuSearchPath=rootPath+"/modules/youku/youkuSearch";
    
    
    //You ToBe
    String youtubeIndexPath=rootPath+"/modules/youtube";
    String  youtubeSearchPath=rootPath+"/modules/youtube/youtubeSearch";
    
    
    //Facebook
    String facebookIndexPath=rootPath+"/modules/facebook";
    String facebookSearchPath=rootPath+"/modules/facebook/facebookSearch";
    
    
    //pbccrc
    String pbccrcIndexPath=rootPath+"/modules/pbccrc";
    String pbccrcAuthPath=rootPath+"/modules/pbccrc/pbccrcAuth";
    
    
    //工商网
    String gsxtIndexPath=rootPath+"/modules/gsxt";
    String gsxtSearchPath=rootPath+"/modules/gsxt/gsxtSearch";
    
    //人法网
    String renfawangIndexPath=rootPath+"/modules/renfawang";
    String renfawangSearchPath=rootPath+"/modules/renfawang/renfawangSearch";
    
    
    //失信网
    String shixinIndexPath=rootPath+"/modules/shixin";
    String shixinSearchPath=rootPath+"/modules/shixin/shixinSearch";
    
    //法海网
    String fahaiccIndexPath=rootPath+"/modules/fahaicc";
    String fahaiccSearchPath=rootPath+"/modules/fahaicc/fahaiccSearch";
    
    //海关网
    String customsIndexPath = rootPath + "/modules/customs";
    String customsSearchPath = rootPath + "/modules/customs/customsSearch";
    
    //dailianmeng
    String dailianmengIndexPath=rootPath+"/modules/dailianmeng";
    String dailianmengSearchPath=rootPath+"/modules/dailianmeng/dailianmengSearch";
    
    //iecms
    String iecmsIndexPath=rootPath+"/modules/iecms";
    String iecmsAuthPath=rootPath+"/modules/iecms/iecmsSearch";
    

    //浙法网
    String zjsfgkwIndexPath=rootPath+"/modules/zjsfgkw";
    String zjsfgkwSearchPath=rootPath+"/modules/zjsfgkw/zjsfgkwExecuteCaseSearch";
    
    // 信用中国
    String creditchinaIndexPath=rootPath + "/modules/creditchina";
    String creditchinaSearchPath=rootPath + "/modules/creditchina/creditchinaSearch";

    
    String fangIndexPath=rootPath+"/modules/fang";
    String fangSearchPath=rootPath+"/modules/fang/fangSearch";
    
    //失信记录查询平台
    String sxjlcxptPath=rootPath+"/modules/sxjlcxpt";
    String sxjlcxptSearchPath=rootPath+"/modules/sxjlcxpt/sxjlcxptSearch";
    
    //第一车网
    String iautosIndexPath=rootPath+"/modules/iautos";
    String iautosSearchPath=rootPath+"/modules/iautos/iautosSearch";
    
    //专利网
    String sipoIndexPath=rootPath+"/modules/sipo";
    String sipoSearchPath=rootPath+"/modules/sipo/sipoSearch";
    
    //11315企业征信
    String zhengxinIndexPath=rootPath+"/modules/zhengxin";
    String zhengxinSearchPath=rootPath+"/modules/zhengxin/zhengxinSearch";
    
  	//认证认可业务信息统一查询平台
    String cncaIndexPath = rootPath + "/modules/cncaController";
    String cncaSearchPath = rootPath + "/modules/cncaController/cncaSearch";

    String fSearchPath = rootPath + "/modules/fSearch";
    String fUnionSearchPath = rootPath + "/modules/fSearch/fUnionSearch/engine";
    String unionSearchAbsPath = rootPath + "/modules/fSearch/fUnionSearch/";
    
%>

<!-- jsp -->
<c:set var="panel" value="<%=panel%>" />
<c:set var="loadPath" value="<%=loadPath %>" />

<c:set var="leftMenuPath" value="<%=leftMenuPath%>" />
<c:set var="breadcrumbPath" value="<%=breadcrumbPath%>" />
<c:set var="searchBoxBigPath" value="<%=searchBoxBigPath%>" />
<c:set var="searchBoxSmallPath" value="<%=searchBoxSmallPath%>" />


<!--springmvc path -->
<c:set var="headerPath" value="<%=headerPath %>" />
<c:set var="navPath" value="<%=navPath %>" />
<c:set var="footerPath" value="<%=footerPath%>" />
<c:set var="modulesIndexPath" value="<%=modulesIndexPath %>" />

<c:set var="personSearchPath" value="<%=personSearchPath %>" />
<c:set var="personDetailsPath" value="<%=personDetailsPath %>" />

<c:set var="sinaIndexPath" value="<%=sinaIndexPath%>" />
<c:set var="sinaSearchPath" value="<%=sinaSearchPath%>" />

<c:set var="baiduIndexPath" value="<%=baiduIndexPath%>" />
<c:set var="baiduSearchPath" value="<%=baiduSearchPath%>" />

<c:set var="zhihuIndexPath" value="<%=zhihuIndexPath%>" />
<c:set var="zhihuSearchPath" value="<%=zhihuSearchPath%>" />

<c:set var="weixinIndexPath" value="<%=weixinIndexPath%>" />
<c:set var="weixinSearchPath" value="<%=weixinSearchPath%>" />

<c:set var="linkedInIndexPath" value="<%=linkedInIndexPath%>" />
<c:set var="linkedInSearchPath" value="<%=linkedInSearchPath%>" />
<c:set var="linkedInDetailPath" value="<%=linkedInDetailPath %>" />

<c:set var="taobaoIndexPath" value="<%=taobaoIndexPath%>" />
<c:set var="taobaoSearchPath" value="<%=taobaoSearchPath%>" />

<c:set var="youkuIndexPath" value="<%=youkuIndexPath%>" />
<c:set var="youkuSearchPath" value="<%=youkuSearchPath%>" />

<c:set var="youtubeIndexPath" value="<%=youtubeIndexPath%>" />
<c:set var="youtubeSearchPath" value="<%=youtubeSearchPath%>" />

<c:set var="facebookIndexPath" value="<%=facebookIndexPath%>" />
<c:set var="facebookSearchPath" value="<%=facebookSearchPath%>" />

<c:set var="pbccrcIndexPath" value="<%=pbccrcIndexPath %>"/>
<c:set var="pbccrcAuthPath" value="<%=pbccrcAuthPath %>"/>

<!-- 工商网 -->
<c:set var="gsxtIndexPath" value="<%=gsxtIndexPath %>"/>
<c:set var="gsxtSearchPath" value="<%=gsxtSearchPath %>"/>

<!-- 人法网 -->
<c:set var="renfawangIndexPath" value="<%=renfawangIndexPath %>" />
<c:set var="renfawangSearchPath" value="<%=renfawangSearchPath %>" />

<!-- 失信网 -->
<c:set var="shixinIndexPath" value="<%=shixinIndexPath %>" />
<c:set var="shixinSearchPath" value="<%=shixinSearchPath %>"></c:set>

<!-- 法海风控 -->
<c:set var="fahaiccIndexPath" value="<%=fahaiccIndexPath %>"/>
<c:set var="fahaiccSearchPath" value="<%=fahaiccSearchPath %>"/>

<!-- 海关网 -->
<c:set var="customsIndexPath" value="<%=customsIndexPath %>" />
<c:set var="customsSearchPath" value="<%=customsSearchPath %>" />

<c:set var="dailianmengIndexPath" value="<%=dailianmengIndexPath %>"/>
<c:set var="dailianmengSearchPath" value="<%=dailianmengSearchPath %>"/>

<c:set var="iecmsIndexPath" value="<%=iecmsIndexPath %>"/>
<c:set var="iecmsAuthPath" value="<%=iecmsAuthPath %>"/>

<c:set var="zjsfgkwIndexPath" value="<%=zjsfgkwIndexPath%>" />
<c:set var="zjsfgkwSearchPath" value="<%=zjsfgkwSearchPath%>" />

<!-- 信用中国 -->
<c:set var="creditchinaIndexPath" value="<%=creditchinaIndexPath %>" />
<c:set var="creditchinaSearchPath" value="<%=creditchinaSearchPath %>" />

<c:set var="fangIndexPath" value="<%=fangIndexPath %>"/>
<c:set var="fangSearchPath" value="<%=fangSearchPath %>"/>

<c:set var="sxjlcxptPath" value="<%=sxjlcxptPath %>"/>
<c:set var="sxjlcxptSearchPath" value="<%=sxjlcxptSearchPath %>"/>
 
<c:set var="iautosIndexPath" value="<%=iautosIndexPath %>"/>
<c:set var="iautosSearchPath" value="<%=iautosSearchPath %>"/>

<c:set var="sipoIndexPath" value="<%=sipoIndexPath %>" />
<c:set var="sipoSearchPath" value="<%=sipoSearchPath %>" />

<c:set var="zhengxinIndexPath" value="<%=zhengxinIndexPath %>" />
<c:set var="zhengxinSearchPath" value="<%=zhengxinSearchPath %>" />

<c:set var="cncaIndexPath" value="<%=cncaIndexPath %>" />
<c:set var="cncaSearchPath" value="<%=cncaSearchPath %>" />

<c:set var="fSearchPath" value="<%=fSearchPath %>" />
<c:set var="fUnionSearchPath" value="<%=fUnionSearchPath %>" />
<c:set var="unionSearchAbsPath" value="<%=unionSearchAbsPath %>" />