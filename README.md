# StudentManagerSysApi
StudentManagerSystem - JavaApi-backend

#### 介绍

该项目是基于**Spring Boot+mybatis**的学生成绩管理系统-后端

整体项目名称为学生成绩管理系统，技术选型为前后端分离，前端基于Vue.js，后端基于Java语言开发，使用了SpringBoot和MyBatis框架提高开发效率和质量。主要面向高校教育中学生管理、课程管理、教师管理、成绩管理、成绩统计等需求。

对应前端请看[StudentManagerSystem - Vue - frontend (github.com)](https://github.com/Patrickming/StudentManagerSys-Vue)

#### 安装教程

1. 数据库配置
   数据库配置：版本8.0.29，
   数据库名称：student_manager_system，
   字符集：utf8mb4 排序规则：utf8mb4_bin，
   导入数据：运行`student_manager_system.sql`，


2. 修改端口

application.properties

```properties
server.port=8080

jdbc.driver=com.mysql.cj.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/student_manager_system?characterEncoding=utf8&allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC
jdbc.username=root
jdbc.password=123456

mybatis_config_file=mybatis-config.xml
mapper_path=/mapper/*/**.xml
entity_package=com.rabbiter.sms.dto

```

3. 运行项目

StudentMisApplication.java

