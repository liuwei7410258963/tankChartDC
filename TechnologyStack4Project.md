#概述
>> 此文档记录当前项目所有到的所有技术栈相关介绍；目前使用到的技术栈主要有如下“表1技术栈列表”

__表1技术栈列表__

| 技术栈名 | 主要功能及作用 |  使用版本|
  |:--------:| :-------- | -----:|
  |[mybatis](https://mybatis.org/mybatis-3/zh/index.html)|主要实现持久化
  |[MyBatis-Plus](https://mp.baomidou.com/guide/)|实现mybatis的增强功能|3.2.0|
  |[fastJson](https://github.com/alibaba/fastjson)|json序列化和反序列化工具|1.2.60|
  |[swagger](https://swagger.io/)|接口管理工具，在`/swagger-ui.html`可以查看所有的接口信息，方便前后端对接|2.9.2|
  |[lombok](https://projectlombok.org/)|一个工具，不必写get、set方法，但是需要安装lombok插件才能使用||
  |[mina](http://mina.apache.org/)|实现端口的通讯，有服务端和客户端的实现方法|2.1.3|
  |[ini4j](http://ini4j.sourceforge.net/)|一个读取ini文件的工具jar|0.5.4|
  
  
# 项目的配置项
### 端口配置
```yaml
server:
  port: 9099
```

  
### spring相关配置
```yaml
spring:
  resources:
    # 静态文件位置
    static-locations: classpath:static/
  servlet:
    # 上传文件大小配置
    multipart:
      max-file-size: 300MB
      max-request-size: 300MB
  # idea中是否启用自动部署功能
  devtools:
    restart:
      enabled: true

# 使用的哪个配置文件，方便开发和部署分离
Spring:
  profiles:
# 使用application-dev.yml
    active: dev
```

### 数据库相关配置
```yaml
spring:
  datasource:
    # 数据库地址
    url: jdbc:mysql://192.168.17.141:3326/tank_chart_dc?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&serverTimezone=GMT%2B8
# 用户名
    username: root
#密码
    password: 123456
# 数据库driver
    driver-class-name: com.mysql.jdbc.Driver
```


### mybatis-plus相关配置
```yaml

mybatis-plus:
# 枚举类所在包，mybatis-plus有一套机制可以保存枚举类型数据
  typeEnumsPackage: com.oket.device,com.oket.dispenser,com.oket.common.base,com.oket.oil,com.oket.delivery,com.oket.tank4station
# mapper的文件位置
  mapper-locations: classpath*:mapper/**/*.xml
  #mybatis数据库字段内容为空时，返回该字段
  configuration:
    # null查询后也会从数据库中返回
    call-setters-on-nulls: true
    #驼峰式命名
    map-underscore-to-camel-case: true
```
### 自定义属性
```yaml
#
dit:
  emulator:
    #    是否启用dit模拟器，
#使用后会自动在ifsf增加模拟时间
    enable: true
# 不需要分页查询列表标志
noPage: 1

#上传文件的路径
uploadFilePath: ./socket_test

#可修改的配置文件的路径
modifiablePortAndIpPath: ./doc/portAndIp.properties

#dit配置文件位置
ditIniFilePath: ./doc/setup.ini
#ditIniFilePath=C:/Program Files/DIT_Agent/setup.ini
# dit的json连接端口
ditJsonPort: 9006
# dit的isfs连接的端口
ifsfPort: 9005
#前端连接后台需要有加密
# 这里是key
websocketKey: oket
# 这里是密码
websocketValue: zEcAY4dlR@XOFyb8


#INI_FILE_PATH=C:/Program Files/DIT_Agent/setup.ini
# 保存数据的位置
baseFilePath: ./biz
```

### Lombok使用介绍
1. 功能：通过注解可以在代码中不需要生成get、set、toString等方法，使代码保持相对的简洁。
2. 使用方式：
    - maven加入相关依赖
```xml
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
```
    - idea下载并安装lombok插件
    - 在类中加上相应的注解(以下是例子)
```java
    @EqualsAndHashCode(callSuper = true)
    @Data
    public class DbInventoryTraceRelNozzleOutGroup extends BaseEntity {
        /**
         * 液位轨迹的id
         */
        private Long inventoryTraceId;
    }
```

3. 注解详情

- `@Data` ： 注在类上，提供类的get、set、equals、hashCode、canEqual、toString方法
- `@AllArgsConstructor` ： 注在类上，提供类的全参构造
- `@NoArgsConstructor` ： 注在类上，提供类的无参构造
- `@Setter` ： 注在属性上，提供 set 方法;或者注解到类上，为所有属性提供set方法
- `@Getter` ： 注在属性上，提供 get 方法;或者注解到类上，为所有属性提供get方法
- `@EqualsAndHashCode` ： 注在类上，提供对应的 equals 和 hashCode 方法，callSuper = true，针对父类的属性也会生成hashcode。


### SpringBoot及Spring常用注解
1. `@Scope`--作用域
1.singleton单例模式,

　　全局有且仅有一个实例

2.prototype原型模式，
　　每次获取Bean的时候会有一个新的实例
3.request　　
    request表示该针对每一次HTTP请求都会产生一个新的bean，同时该bean仅在当前HTTP request内有效，

4.session　
    session作用域表示该针对每一次HTTP请求都会产生一个新的bean，同时该bean仅在当前HTTP session内有效

5.global session
    global session作用域类似于标准的HTTP Session作用域，不过它仅仅在基于portlet的web应用中才有意义。Portlet规范定义了全局Session的概念，它被所有构成某个 portlet web应用的各种不同的portlet所共享。在global session作用域中定义的bean被限定于全局portlet Session的生命周期范围内。如果你在web中使用global session作用域来标识bean，那么web会自动当成session类型来使用。
