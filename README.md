# crawler
a common crawler
爬虫项目
--------------------------工程介绍------------------------------------
common-crawler-comb :各站点爬虫规则包

common-module-aws ：调用亚马逊动态IP接口

common-module-dao ：数据库底层包

common-module-drpc ：storm调用爬虫中间包

common-module-htmlunit ：htmlunit的接口封装

common-module-log ：日志设置包

common-module-mail ：邮件服务包

common-module-ocr ：图片识别接口包（超级鹰）

common-module-redis ：redis封装

common-module-rss ：新闻接口封装

common-module-security-shiro ：shiro权限认证包

common-module-service ：业务逻辑处理包

common-module-timetask ：定时任务封装包

common-module-unit ：工具包

common-sns-oauth ：社交网站认证包

common-sns-weibo ：微博数据接口封装

common-storm    :storm工作节点执行包

cookies-extension ：cookies 的js插件（手动将浏览器端的cookies注入后台程序）

crawler-manage-web ：爬虫项目管理的web工程

data-service-web ：爬虫内容展示工程

ocr-service-web ：超级鹰这种ocr做成了restful接口服务

parent ：maven主pom管理包

-----------------------下载代码后需修改成自己的服务------------------------
1.	修改数据库：parent项目中pom.xml文件中 jdbc.url

2.	修改邮件服务：parent项目中pom.xml文件中mail.host，mail.username，mail.password

3.	修改redis服务：parent项目中pom.xml文件中：redis.host，redis.port

4.	修改storm服务：parent项目中pom.xml文件中storm.drpc.host, storm.drpc.port, storm.topology.workernum

5.	修改nfs的nginx配置：parent项目中pom.xml文件中nfs.nginx.server, nfs.filepath

6.	修改ocr的配置：parent项目中pom.xml文件中ocr.server和AbstractChaoJiYingHandler.java

7.	修改aws：parent项目中pom.xml文件中

<aws.endpoint>xxx</aws.endpoint>//aws服务器在那个区（中国区）的那个机房里

<aws.accessKey>xxx</aws.accessKey>//账户id(aws s3的用户验证方式是一种对称加密方式)

<aws.secretKey>xxx</aws.secretKey>//账户密钥
<aws.eipalloc>eipalloc-fd930fc7</aws.eipalloc>//aws 弹性IP容器
<aws.crawlers>xxx,xxx</aws.crawlers>//需要改变ip工作节点id
<aws.securityGroup.name>JavaSecurityGroup</aws.securityGroup.name>//安全组名
------------------------------------编译--------------------------------------
1.	进入parent项目 编译pom文件

mvn clean install;

2.将得到的data.war;ocr-service.war 部署到

tomcatdata.war: 爬虫项目管理的web工程

ocr-service.war: 超级鹰图片识别的接口服务

3.将common-storm-0.0.1-SNAPSHOT.jar发布到storm的nimbus节点

