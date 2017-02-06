<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/commons/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<jsp:include page="header.jsp"></jsp:include>
<body>
	<jsp:include page="nav.jsp"></jsp:include>
	
	
	<div class="container" style="padding:0;margin:0;">
		<div style="margin-top: 10%; margin-left: 21%;">
			<div class="row">
				<div class="col-sm-2 col-md-2">
					<div class="thumbnail">
						<img src="${ctx}/static/imgs/logo/sinaWeibo01.png" alt="新浪微博">
						<div class="caption">
							<h3><strong>新浪微博</strong></h3>
							<p>极具人气的微博分享平台</p>
							<p>
								<a href="#" class="btn btn-primary" role="button">进入 >></a>
							</p>
						</div>
					</div>
				</div>
				
				<div class="col-sm-2 col-md-2">
					<div class="thumbnail"> <!-- style="border-color:#F2F2F2;background-color:#F2F2F2;" -->
						<img src="${ctx}/static/imgs/logo/zhihu01.png" alt="知乎">
						<div class="caption">
							<h3>知乎</h3>
							<p>一个真实的网络问答社区</p>
							<p>
								<a href="#" class="btn btn-primary" role="button">进入 >></a>
							</p>
						</div>
					</div>
				</div>
				
				<div class="col-sm-2 col-md-2">
					<div class="thumbnail">
						<img src="${ctx}/static/imgs/logo/baike01.png" alt="百度百科">
						<div class="caption">
							<h3>百度百科</h3>
							<p>全球最大中文百科全书</p>
							<p>
								<a href="#" class="btn btn-primary" role="button">进入 >></a>
							</p>
						</div>
					</div>
				</div>
				
				<div class="col-sm-2 col-md-2">
					<div class="thumbnail">
						<img src="${ctx}/static/imgs/logo/weixin01.png" alt="微信">
						<div class="caption">
							<h3>微信</h3>
							<p>腾讯移动即时通讯平台</p>
							<p>
								<a href="#" class="btn btn-primary" role="button">进入 >></a>
							</p>
						</div>
					</div>
				</div>
				
				<div class="col-sm-2 col-md-2">
					<div class="thumbnail">
						<img src="${ctx}/static/imgs/logo/linkedIn01.png" alt="linkedIn">
						<div class="caption">
							<h3>领英</h3>
							<p>全球知名职场社交平台</p>
							<p>
								<a href="#" class="btn btn-primary" role="button">进入 >></a>
							</p>
						</div>
					</div>
				</div>
				
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
