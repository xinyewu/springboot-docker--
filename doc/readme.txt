1 ) 总结本次项目中所用到的技术及知识点.

2) github账户注册成功（ 告知我你的gitee的地址 ).  并理解 fork, star, watch的区别.
3) 自学 git 命令. 要求学会:
    	 a) 理解git 和gitee的区别.
    	 b) git的常见命令:  学习网址: https://www.liaoxuefeng.com/wiki/896043488029600/900937935629664
                          学习如下内容:
                                  安装Git(  只需学习windows下如何安装)
                                  创建版本库
                                  时光机穿梭
                                  远程仓库
               要求: 在github上创建一个代码仓库, 将小萌神项目上传到这个仓库中.



4)扩展以下功能:
     a) 后台功能:  1. 列表所有的菜品
                         2. 订单处理
                         3. 后台首页统计汇总功能(  如显示出下单最多的前十名客户， 被下单最多的最十个菜   等等 )   以上汇总的数据由定时器完成，在每天固定时间进行统计汇总后记入redis.
                         4. 系统信息的显示,这是一个dashboard( 如CPU, 内存，服务器操作系统等信息)
    b)前台功能:  1. 用户登录后增加功能:   历史访问记录，我下过的单.   显示最受欢迎的菜品.
                       2. 新增一个  与 店小二聊天的小窗口，能与店小二线上聊天.

4）扩展学习以下知识:
      1) 后台页面最好采用  模板技术完成，比如: themleaf (   freemarker, velocity 等 ）
      2)  登录可以扩展成多种登录方式，如QQ, 微信等,   请扩展学习spring security，   (它里面的 Oauth2 认证可以实现)
      3) websocket完成实时聊天功能.

======================================================================
1.springboot profile
如何定制 配置文件名
如何在不同环境中使用不同配置文件
如何激活（几种方式）：java -jar -Dspring.active.profile=
2. 容器在docker中的布置架构问题
3. springboot 整合fastDFS的客户.
   原生客户端：
   starter端：
   依赖查找：https://mvnrepository.com/
   编码：
4.docker中数据库表及基础数据的准备
5.docker镜像文件的发布

====================分布式文件服务 fastDfs===============================
1.安装fastDfs的docker容器，在容器测试上传，测试远程访问
docker run -d --name tracker --network=ycnetwork -v /mydata/fastdfs/tracker:/var/fdfs delron/fastdfs tracker             172.18.0.4

docker run -d -p 8888:8888 --name storage --network=ycnetwork -e TRACKER_SERVER=172.18.0.4:22122 -v E:\dockercontainers\fastdfs\storage:/var/fdfs -e GROUP_NAME=group1 delron/fastdfs storage

http://localhost:8888/group1/M00/00/00/rBIABWVLayaAbqu_AAOA1dwT4AY608.jpg

2.写小萌神的后台
管理员登录 -》显示管理后台-》新菜上架
3.对小萌神进行发布容器，放到docker中
==================session管理：spring session+Redis============
问题：将应用服务器tomcat的会话session转移到Redis上去，实现发布式会话管理，并解决微服务下session管理问题
1.依赖
  <!--引入Redis依赖-->
          <dependency>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-starter-data-redis</artifactId>
          </dependency>
          <!--引入session依赖-->
          <dependency>
              <groupId>org.springframework.session</groupId>
              <artifactId>spring-session-data-redis</artifactId>
          </dependency>
2.redis连接配置
spring:
 session:
     store-type: redis   #将session保存到Redis中
   redis:
     database: 1     #Redis默认0-15 总共16个库，这里采用1库
     host: 127.0.0.1    #到docker后修改，还要加上用户名和密码，且Redis中配置访问的主机的ip，以提高安全性
     port: 6379
     lettuce:
       pool:
         max-active: 8
         max-wait: -1ms
         max-idle: 5
         min-idle: 0
     timeout: 5000
3.启用spring session
 @EnableRedisHttpSession
 Redis序列化器的配置

================dao层：mybatisPlus,druid连接池==================
问题：将数据库连接池化，以提高数据库访问性能，并可监控数据库操作日志：druid 简化dao层开发：mybatisPlus

========测试技术=========================================
1.springboot测试
2.测试用例 testcase=>测试套件 testsuit
3.断言机制
4.mock测试：测试web层   模拟web容器环境（tomcat环境  -》HttpServletRequest,HttpServletResponse,HttpSession,Cookie）
========前端技术=========================================
一，es6中的Promise对象及then(),catch(),race()的原理及使用场景：
 1.使用场景：回调函数,定时器，事件绑定，ajax
 2.解决问题：嵌套调用的问题
二，前端bootstrap框架：
 1.bootstrap.min.css与bootstrap.css文件的区别及使用场景
 2.cdn本地文件的区别及使用cdb的优点：
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/css/bootstrap.min.css">
<!--    <link href="css/bootstrap.css" rel="stylesheet"> 本地文件-->
 3.bootstrap提供了一些列页面组件的外观设置

=======容器技术：docker===================================


========================================================
1. 创建项目，项目的pom.xml设置，目录结构设置,   配置文件设置.
2.创建数据库及表，插入数据
3. dao层重新开发
4. biz层的测试
-------
3. Controller
4. 前端页面的测试
------
新增功能:
1. 使用mock测试 controller
2. 利用kafka完成邮件发送.

技术描述：
db:mysql
web:springMVC--框架
脚手架：spring-boot +spring
API：Swagger发布API
测试：junit +mock
session:spring session-redis

controller测试方式:
1. 启服务器，用浏览器测试:
     问题:  记地址
2. API web接口: 框架,
    swagger  -> springMVC
   问题:安全性
3. 客户端工具  postman
     可以写脚本. 项目形势管理API测试
4. 编码方式集成测试:  mock  ->  模拟web请求.
============================
1.swagger
2.mock
3.vue JS

============================
1.js对象可以动态添加属性
2.axios.get/post():返回promise对象
promise.then(result=>{ });   result:请求信息，响应头信息，data(结果数据json mode)
3.vue:   :src=`image/${item.fphoto}`
  胡子语法：<标签>{{属性}}<标签>
