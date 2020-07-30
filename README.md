# myblogs
myblogs项目是，程序员微信群：七个高富帅和一个柯弟弟，的一个Vue+SpringBoot练手项目

接口文档地址：
http://lvmz521.com:8080/myblogs/swagger-ui.html
测试账号： admin  111
其中admin下的接口允许匿名访问，其余接口需要登录
将获得的jwt 添加请求头Bearer
例如： Bearer+空格+jwt 输入到swagger的authorize中 即可进行登录访问（部分权限未放开）

实现功能：
1.整合redis  实现登录信息缓存
2.整合mongodb  实现文档存储搜索功能
3.使用springsecurity进行登录鉴权和方法粒度的权限控制
4.实现mybatis动态并基于注解的数据源切换功能
5.整合activemq进行消息发送
6.rest风格接口开发
7.采用jenkins自动化部署项目
