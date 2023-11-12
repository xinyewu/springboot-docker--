[TOC]

# windows上安装docker

#### docker的几个基本知识

1. Docker是一个开源的应用容器引擎；是一个轻量级容器技术； Docker支持将软件编译成一个镜像；然后在镜像中各种软件做好配置，将镜像发布出去，其他使用者可以直接使 用这个镜像； 运行中的这个镜像称为容器，容器启动是非常快速的

2. docker主机(Host)：安装了Docker程序的机器（Docker直接安装在操作系统之上）

3. docker客户端(Client)：连接docker主机进行操作

4. docker仓库(Registry)：用来保存各种打包好的软件镜像

5. docker镜像(Images)：软件打包好的镜像；放在docker仓库中

6. docker容器(Container)：镜像启动后的实例称为一个容器；容器是独立运行的一个或一组应用

   ![image-20231111150234262](C:\Users\xinye\AppData\Roaming\Typora\typora-user-images\image-20231111150234262.png)

#### Windows上docker的安装步骤

1. ##### 下载安装

   官方下载地址：[Docker Desktop Installer.exe 下载地址](https://desktop.docker.com/win/stable/amd64/Docker Desktop Installer.exe)

   国内镜像：[Windows安装包下载地址](https://smartidedl.blob.core.chinacloudapi.cn/docker/20210926/Docker-win.exe)（建议直接安装这个）

   Windows 的 Docker 桌面说明：[https://hub.docker.com/editions](https://link.zhihu.com/?target=https%3A//hub.docker.com/editions/community/docker-ce-desktop-windows)

2. ##### （可以先跳过这一步，第三步如果提示启用WSL2，可以不需要这一步）启用Hyper-V：[详情参考微软官方教程](https://link.zhihu.com/?target=https%3A//docs.microsoft.com/zh-cn/virtualization/hyper-v-on-windows/quick-start/enable-hyper-v)

   可以通过多种方式启用 Hyper-V，包括使用 Windows 10 控制面板、PowerShell（Hyper-V 作为可选功能内置于 Windows -- 无需下载 Hyper-V）。

   方法一：使用 PowerShell 启用 Hyper-V

   ​       以管理员身份打开 PowerShell 控制台，运行以下命令：

   ```powershell
   Enable-WindowsOptionalFeature -Online -FeatureName Microsoft-Hyper-V -All
   ```

   ![Console window showing Hyper-V being enabled.](https://learn.microsoft.com/zh-cn/virtualization/hyper-v-on-windows/quick-start/media/dism_upd.png)

    方法二：通过控制面板“设置”启用 Hyper-V 角色

   ​     1.右键单击 Windows 按钮并选择“应用和功能”。

   ​     2.选择相关设置下右侧的“程序和功能”。

   ​     3.选择“打开或关闭 Windows 功能”。

   ​     4.选择“Hyper-V”，然后单击“确定”。

   ![Windows programs and features dialogue box](https://learn.microsoft.com/zh-cn/virtualization/hyper-v-on-windows/quick-start/media/enable_role_upd.png)

   **注意：win10家庭版中，是没有`hype-v`的。复制下面的代码保存到记事本中，并改名为`Hyper-V.cmd`**

```
pushd “%~dp0”
dir /b %SystemRoot%\servicing\Packages*Hyper-V*.mum >hyper-v.txt
for /f %%i in (‘findstr /i . hyper-v.txt 2^>nul’) do dism /online /norestart /add-package:"%SystemRoot%\servicing\Packages%%i"
del hyper-v.txt
Dism /online /enable-feature /featurename:Microsoft-Hyper-V-All /LimitAccess /ALL
```

运行代码，安装`Hype-v`，**安装完成后，系统会提示你重新启动计算机**，重启后，点击任务管理器

![image-20231111160911449](C:\Users\xinye\AppData\Roaming\Typora\typora-user-images\image-20231111160911449.png)

##### 3.安装Docker Desktop： 双击下载成功的Docker Desktop Installer.exe应用或Docker-win.exe应用

![](C:\Users\xinye\Desktop\新建文件夹\43.png)

![](C:\Users\xinye\Desktop\新建文件夹\41.png)

![image-20231111162434505](C:\Users\xinye\AppData\Roaming\Typora\typora-user-images\image-20231111162434505.png)

（在第一次开启时，可能报如下图错误，要你启用WSL2，点击链接安装 WSL2https://wslstorestorage.blob.core.windows.net/wslblob/wsl_update_x64.msi，然后按步骤启动）

![image-20231111204928114](C:\Users\xinye\AppData\Roaming\Typora\typora-user-images\image-20231111204928114.png)

![image-20231111162503973](C:\Users\xinye\AppData\Roaming\Typora\typora-user-images\image-20231111162503973.png)

##### 4.docker的配置

1. 配置阿里云镜像加速地址：

   进入管理控制台获取镜像加速器地址：[https://cr.console.aliyun.com/cn-hangzhou/instances/mirrors](https://link.zhihu.com/?target=https%3A//cr.console.aliyun.com/cn-hangzhou/instances/mirrors)

   ![image-20231111165332447](C:\Users\xinye\AppData\Roaming\Typora\typora-user-images\image-20231111165332447.png)

2. 在Docker Desktop设置中配置阿里云镜像源：

   在系统右下角托盘图标内右键菜单选择 Settings，打开配置窗口后左侧导航菜单选择 Docker Desktop。编辑窗口内的JSON串，填写从阿里云复制的加速器地址：

   { "registry-mirrors": ["[https://xxxx.mirror.aliyuncs.com](https://link.zhihu.com/?target=https%3A//xxxx.mirror.aliyuncs.com)"] }

   | 镜像加速器          | 镜像加速器地址                       |
   | ------------------- | ------------------------------------ |
   | Docker 中国官方镜像 | https://registry.docker-cn.com       |
   | DaoCloud 镜像站     | http://f1361db2.m.daocloud.io        |
   | Azure 中国镜像      | https://dockerhub.azk8s.cn           |
   | 科大镜像站          | https://docker.mirrors.ustc.edu.cn   |
   | 阿里云              | https://ud6340vz.mirror.aliyuncs.com |
   | 七牛云              | https://reg-mirror.qiniu.com         |
   | 网易云              | https://hub-mirror.c.163.com         |
   | 腾讯云              | https://mirror.ccs.tencentyun.com    |
   
   ![image-20231111170311315](C:\Users\xinye\AppData\Roaming\Typora\typora-user-images\image-20231111170311315.png)
   
   3.查看docker版本  ：
   
   ```powershell
   docker version
   ```
   
   ![image-20231111170444082](C:\Users\xinye\AppData\Roaming\Typora\typora-user-images\image-20231111170444082.png)

##### 5.验证Docker桌面版是否可以正常使用：

```powershell
docker run hello-world
```

看到以下输出则表示安装成功，且能正常工作：

![image-20231111204029391](C:\Users\xinye\AppData\Roaming\Typora\typora-user-images\image-20231111204029391.png)

##### 6.docker相关使用教程：[Docker 容器使用 | 菜鸟教程 (runoob.com)](https://www.runoob.com/docker/docker-container-usage.html)

# 发布项目

##### 1.docker下载相关镜像

######  1.docker安装mysql：8

```powershell
--拉取mysql8的镜像
docker pull mysql:8
--查看
docker images
-- 利用镜像创建mysql8的容器   本机端口为映射为３３０８，                                   解决编码问题
docker run  -p 3308:3306 --name ycmysql8 -e MYSQL_ROOT_PASSWORD=a -d  mysql:8 --character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci
--查看容器是否启动
docker ps -a
--进入容器     -it 以交互式终端进入容器 
docker exec -it ycmysql8 /bin/bash
--登录mysql
mysql -uroot -pa
--进入 mysql 修改密码加密规则 ,如果要允许远程用户也可以联接(比如通过windows主机上的navicat联接), 则不要加 ＠'localhost'
alter user 'root'@'localhost' identified with mysql_native_password by 'a';
alter user 'root' identified with mysql_native_password by 'a';
--使修改生效
flush privileges;
--退出
quit或exit  (ctrl+z也行)
```

######  2.docker 安装redis （注意路径要根据你自己的来定）

​     1.编写或获取一个redis配置文件 redis.conf 放到你创建的 redis目录下（D:\dockercontainers\redis）, 注意开启 远程访问及登录密码

​        开启远程登录  #bind 127.0.0.1 -::1     

​                                  requirepass a

![image-20231112151351442](C:\Users\xinye\AppData\Roaming\Typora\typora-user-images\image-20231112151351442.png)

![image-20231112151413608](C:\Users\xinye\AppData\Roaming\Typora\typora-user-images\image-20231112151413608.png)

![image-20231112152204189](C:\Users\xinye\AppData\Roaming\Typora\typora-user-images\image-20231112152204189.png)

```powershell
--拉取redis最新的镜像
docker pullredis
--查看
docker images
-- 利用镜像创建redis的容器   本机端口为映射为6380，将本地的D:\dockercontainers\redis\data:目录映射到容器的/data目录，用于存储数据, redis.conf文件必须在D:\dockercontainers\redis 这一级目录下
docker run -v D:\dockercontainers\redis\data:/data -v D:\dockercontainers\redis\:/usr/local/etc/redis -p 6380:6379 --name redis-host -d redis redis-server /usr/local/etc/redis/redis.conf
--查看容器是否启动
docker ps -a
--进入容器     -it 以交互式终端进入容器 
docker exec -it redis-host /bin/bash
--退出
quit或exit  (ctrl+z也行)
```

###### 3.docker创建网桥

​    1.介绍网桥

​        bridge 存在的目的：隔离各个容器，使得每个容器的端口号都是隔离的。如果不隔离开来，那么容器将和宿主机，容器和容器间都会发生端口占用的情况。

​        docker的桥接网络使用虚拟网桥，bridge网络用于同一主机上的docker容器相互通信，连接到同一个网桥的docker容器可以相互通信，当我们启动docke时，会自动创建一个默认bridge网络，除非我们进行另外的配置，新创建的容器都会自动连接到这个网络，我们也可以自定义自己的bridge网络，docker文档建议使用自定义bridge网络。

​        连接到同一bridge网络的容器可以相互访问彼此任意一个端口，如果不发布端口，外界将无法访问这些容器，在创建容器时，通过-p或是--publish指令发布端口

![image-20231112152935601](C:\Users\xinye\AppData\Roaming\Typora\typora-user-images\image-20231112152935601.png)

```powershell
--创建一个名为ycnetwork的网桥  （docker network create -d bridge  网络id）
docker network create -d bridge ycnetwork
--查看所有网路，看是否创建ycnetwork成功
docker network ls
--将已经创建的容器加入网桥   docker network connect 网络id    容器名
docker network connect ycnetwork  ycmysql8
docker network connect ycnetwork  redis-host
--查看某个网络详情  docker network inspect 网络ID
docker network inspect ycnetwork
--删除某个网络
docker network remove 网络id
```

​    （没加入容器前的网桥）![image-20231112153743733](C:\Users\xinye\AppData\Roaming\Typora\typora-user-images\image-20231112153743733.png)

​       (加入容器后的网桥)

![image-20231112153915093](C:\Users\xinye\AppData\Roaming\Typora\typora-user-images\image-20231112153915093.png)

###### 4.docker 安装fastdfs（注意路径要根据你自己的来定）

   1.先准备好fastdfs目录

![image-20231112154042345](C:\Users\xinye\AppData\Roaming\Typora\typora-user-images\image-20231112154042345.png)

​    2.docker安装

![image-20231112154439036](C:\Users\xinye\AppData\Roaming\Typora\typora-user-images\image-20231112154439036.png)

注意：保证 storage目录的磁盘大小 至少有空闲空间  10%

```powershell
--查看fastdfs镜像
docker search fastdfs
--拉取delron/fastdfs最新的镜像
docker pull delron/fastdfs 
--tracker安装                     加入网桥                         映射路径
docker run -d  --name tracker   --network=ycnetwork     -v D:\dockercontainers\fastdfs\tracker:/var/fdfs delron/fastdfs tracker
--storage安装      本机暴露端口                              加入网桥              tracker在网桥中的地址（22122是默认端口，因为上一步没有暴露本机端口，使用默认的）
docker run -d  -p 8888:8888           --name storage --network=ycnetwork  -e TRACKER_SERVER=172.18.0.4:22122    -v D:\dockercontainers\fastdfs\storage:/var/fdfs   -e GROUP_NAME=group1 delron/fastdfs storage
```

​    3.在storage容器中测试使用命令上传图片

​        在storage目录下放一张图片

![image-20231112155636996](C:\Users\xinye\AppData\Roaming\Typora\typora-user-images\image-20231112155636996.png)

```powershell
--运行storage
docker exec -it storage bash
--进入fdfs目录
cd /var/fdfs
--上传图片
/usr/bin/fdfs_upload_file /etc/fdfs/client.conf xxx.jpg    
group1/M00/00/00/xxxxxxxxx.jpg （按回车键会返回这样一个路径给你）
```

![image-20231112155544901](C:\Users\xinye\AppData\Roaming\Typora\typora-user-images\image-20231112155544901.png)

​     4.浏览器访问   http://localhost:8888/xxxxxxxxx.jpg  （看到图片，什么fastdfs成功）

![image-20231112155525451](C:\Users\xinye\AppData\Roaming\Typora\typora-user-images\image-20231112155525451.png)

##### 2.idea打包项目发送镜像到docker（本项目已经发送到github:[https://github.com/xinyewu/springboot-docker--.git](https://github.com/xinyewu/springboot-docker--)，下面是大致步骤，看看就行）

######     1.前置准备

​       1.用mysql桌面工具远程连接docker的mysql8（网桥里面的172.18.0.3  密码a），将mysql数据脚本添加到docker的mysql8中

​       2.yml文件的配置

​       3.创建Dockerfile文件到项目目录下

​           ![image-20231112162013886](C:\Users\xinye\AppData\Roaming\Typora\typora-user-images\image-20231112162013886.png)

​         4.docker上管理端口的暴露

![image-20231112162138821](C:\Users\xinye\AppData\Roaming\Typora\typora-user-images\image-20231112162138821.png)

​         5.pom文件添加镜像插件

![image-20231112163153365](C:\Users\xinye\AppData\Roaming\Typora\typora-user-images\image-20231112163153365.png)

###### 2.打包项目（iead自动发送项目镜像到docker）

![image-20231112163046892](C:\Users\xinye\AppData\Roaming\Typora\typora-user-images\image-20231112163046892.png)

###### 3.iead管理项目

   1.找到services面板

![image-20231112163357577](C:\Users\xinye\AppData\Roaming\Typora\typora-user-images\image-20231112163357577.png)

​       2.iead与docker建立连接

![image-20231112163535969](C:\Users\xinye\AppData\Roaming\Typora\typora-user-images\image-20231112163535969.png)

![image-20231112163826822](C:\Users\xinye\AppData\Roaming\Typora\typora-user-images\image-20231112163826822.png)

![image-20231112164120382](C:\Users\xinye\AppData\Roaming\Typora\typora-user-images\image-20231112164120382.png)

##### 3.用idea的docker功能创建容器，并加入到统一网桥中

​      1.创建容器

![image-20231112164228190](C:\Users\xinye\AppData\Roaming\Typora\typora-user-images\image-20231112164228190.png)

![image-20231112164404638](C:\Users\xinye\AppData\Roaming\Typora\typora-user-images\image-20231112164404638.png)

![image-20231112164659003](C:\Users\xinye\AppData\Roaming\Typora\typora-user-images\image-20231112164659003.png)

![image-20231112164722325](C:\Users\xinye\AppData\Roaming\Typora\typora-user-images\image-20231112164722325.png)

![image-20231112165008518](C:\Users\xinye\AppData\Roaming\Typora\typora-user-images\image-20231112165008518.png)

​      2.将创建的容器test加入ycnetwork网桥

![image-20231112164913926](C:\Users\xinye\AppData\Roaming\Typora\typora-user-images\image-20231112164913926.png)

​      3.再次运行

![image-20231112165452677](C:\Users\xinye\AppData\Roaming\Typora\typora-user-images\image-20231112165452677.png)

##### 4.运行项目(我在第一步创建容器的时候把本机映射端口改成了9111)，测试成功

![image-20231112165534212](C:\Users\xinye\AppData\Roaming\Typora\typora-user-images\image-20231112165534212.png)

##### 5.发送到github

​    通过git软件，或者直接拖到github上都行，这个需要自己去了解