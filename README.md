# 简介
SpringBoot用来简化Spring程序的创建和开发过程
以往通过SpringMVC+Spring+Mybatis进行开发，需要配置web.xml、spring、mybatis等，并整合到一起
SpringBoot依然使用了SSM这一套，但抛弃了繁琐的xml配置过程，采用大量的默认配置，化繁为简

# 特性
能够快速创建基于Spring的程序
直接使用Java main方法启动内嵌的Tomcat服务器运行SpringBoot程序，不需要部署war包
提供约定的starter POM来简化Maven配置
自动化配置，根据项目的Maven依赖配置，SpringBoot自动配置Spring、SpringMVC等
提供了程序的健康检查功能
基本可以完全不使用XML配置文件，而采用注解配置

# 四大核心
自动配置、起步依赖、Actuator、命令行界面

# 版本
SpringBoot 2.7.11
Java8

# 步骤
New - Spring Initializr
设置GAV坐标、POM配置信息
选择SpringBoot版本和依赖
设置Module名、Content Root路径、Module目录
pom.xml右键 - Mark As a Maven Project

# 分析
Application.java 程序执行的入口
    @SpringBootApplication 开启Spring自动配置
    main方法，是boot项目启动运行的入口
    其它Java文件要放在同级目录下，否则SpringBoot加载不到
pom.xml
    继承SpringBoot框架的一个父项目，所有自己开发的SpringBoot都必须继承它
    当前项目的GAV坐标
    SpringBoot框架web项目起步依赖，通过该依赖自动关联其它依赖，不需要一个个添加
    SpringBoot框架的测试起步依赖，比如：junit测试
    SpringBoot提供的打包编译等插件
application.properties

# 配置文件
application.properties 存放各种依赖模块的配置信息，比如：服务端口、数据库连接配置等

多环境配置：dev / test / production

内置Tomcat的端口配置
server.port=8082

配置项目上下文根
server.servlet.context-path=/

数据库配置
spring.database.driver-class-name / url / username / password

swagger文档
先配置spring.mvc.pathmatch.matching-strategy=ant-path-matcher
再新建一个SwaggerConfig配置文件

# 创建一个SpringMVC的Controller
类注解 @RestController @RequestMapping()
方法注解 @GetMapping()

# 自定义配置
@Value注解，读取application.properties中的配置
@ConfigurationProperties 将整个文件映射为一个对象，用于自定义配置项比较多的情况

# 集成MyBatis
添加pom依赖
配置数据库信息
使用MyBatis逆向工程，生成Model类、Mapper映射文件、Mapper接口
创建service接口、impl类，调用Mapper接口
在Controller中调用service.impl实现类

# 事务
入口类 @EnableTransactionManagement
访问数据库的service方法 @Transactional

# SpringMVC注解
@Controller 处理HTTP请求
@RestController 是@Controller和@ResponseBody的组合注解，相当于给方法加了@ResponseBody注解，返回json数据或字符串
@RequestMapping 支持GET和POST
@GetMapping
@PostMapping

# 实现Restful
(@PathVariable("id") int id)
@GetMapping
@PostMapping

# 集成Redis
先从redis缓存中查找，如果找不到，再从数据库中查找，并放到redis缓存中
